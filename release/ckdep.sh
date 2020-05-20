#!/usr/bin/env mksh
# -*- mode: sh -*-
#-
# Copyright © 2016, 2017, 2018, 2019, 2020
#	mirabilos <t.glaser@tarent.de>
#
# Provided that these terms and disclaimer and all copyright notices
# are retained or reproduced in an accompanying document, permission
# is granted to deal in this work without restriction, including un‐
# limited rights to use, publicly perform, distribute, sell, modify,
# merge, give away, or sublicence.
#
# This work is provided “AS IS” and WITHOUT WARRANTY of any kind, to
# the utmost extent permitted by applicable law, neither express nor
# implied; without malicious intent or gross negligence. In no event
# may a licensor, author or contributor be held liable for indirect,
# direct, other damage, loss, or other issues arising in any way out
# of dealing in the work, even if advised of the possibility of such
# damage or existence of a defect, except proven that it results out
# of said person’s immediate fault when using the work as intended.
#-
# Script to check dependencies

# initialisation
export LC_ALL=C
unset LANGUAGE
PS4='++ '
set -e
set -o pipefail
parentpompath=..
cd "$(dirname "$0")"
saveIFS=$' \t\n'
print -ru2 -- '[INFO] ckdep.sh starting'
abend=0

x=$(sed --posix 's/u\+/x/g' <<<'fubar fuu' 2>&1) && alias 'sed=sed --posix'
x=$(sed -e 's/u\+/x/g' -e 's/u/X/' <<<'fubar fuu' 2>&1)
case $?:$x {
(0:fXbar\ fuu) ;;
(*)
	print -ru2 -- '[ERROR] your sed is not POSIX compliant'
	exit 1 ;;
}

# get project metadata
<"$parentpompath/pom.xml" xmlstarlet sel \
    -N pom=http://maven.apache.org/POM/4.0.0 -T -t \
    -c /pom:project/pom:groupId -n \
    -c /pom:project/pom:artifactId -n \
    -c /pom:project/pom:version -n \
    |&
IFS= read -pr pgID
IFS= read -pr paID
IFS= read -pr pVSN
# check old file is sorted
sort -uo ckdep.tmp ckdep.lst
if ! cmp -s ckdep.lst ckdep.tmp; then
	print -ru2 -- '[WARNING] list of dependencies was not sorted!'
	cat ckdep.tmp >ckdep.lst
	(( abend |= 1 ))
fi
# analyse Maven dependencies
function domvn {
	mvn -B "$@" dependency:list 2>&1 | scanmvn
}
function scanmvn {
	tee /dev/stderr | sed -n \
	    -e 's/ -- module .*$//' \
	    -e '/^\[INFO]    org.evolvis.veraweb:/d' \
	    -e '/^\[INFO]    org.evolvis.veraweb.middleware:/d' \
	    -e '/^\[INFO]    \([^:]*\):\([^:]*\):jar:\([^:]*\):\([^:]*\)$/s//\1:\2 \3 \4/p'
}
function doscopes {
	local lastgav lastscope ga v scope rest

	sort -u | while read ga v scope rest; do
		# compile scope supersets provided scope, either supersets test
		case "$lastgav $lastscope:$scope" {
		("$ga:$v "compile:provided|"$ga:$v "@(compile|provided):test) ;;
		(*) print -r -- $ga $v $scope $rest ;;
		}
		lastgav=$ga:$v lastscope=$scope
	done
}
(cd "$parentpompath" && domvn) | doscopes | sort -u >ckdep.mvn.tmp
# deal with embedded copies
function dopom {
	local scope=$1; shift
	cat >ckdep.pom <<-EOF
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<parent>
			<groupId>$pgID</groupId>
			<artifactId>$paID</artifactId>
			<version>$pVSN</version>
			<relativePath>$parentpompath/</relativePath>
		</parent>
		<artifactId>embedded-code-copy-insert-$1</artifactId>
		<packaging>jar</packaging>
		<dependencies>
	EOF
	shift
	set -o noglob
	for gav in "$@"; do
		IFS=:
		set -- $gav
		IFS=$saveIFS
		cat <<-EOF
			<dependency>
				<groupId>$1</groupId>
				<artifactId>$2</artifactId>
				<version>$3</version>
				<scope>$scope</scope>
			</dependency>
		EOF
	done >>ckdep.pom
	set +o noglob
	cat >>ckdep.pom <<-EOF
		</dependencies>
	</project>
	EOF
	# OWASP to consider: https://github.com/jeremylong/DependencyCheck/issues/2349
	domvn -f ckdep.pom -Dwithout-implicit-dependencies >>ckdep.pom.tmp
}
function dodoc_! {
	local scope=$1; shift
	print -ru2 -- "[INFO] resolving embedded-code-copy-document-$1 [$scope]"
	shift
	set -o noglob
	for gav in "$@"; do
		IFS=:
		set -- $gav
		IFS=$saveIFS
		print -r -- "[INFO]    $1:$2:jar:$3:doc-only"
	done | scanmvn >>ckdep.pom.tmp
	set +o noglob
}
function dodoc_+ {
	local scope=$1; shift
	print -ru2 -- "[INFO] resolving embedded-code-copy-insert-$1 [$scope]"
	shift
	set -o noglob
	for gav in "$@"; do
		IFS=:
		set -- $gav
		IFS=$saveIFS
		print -r -- "[INFO]    $1:$2:jar:$3:unreleased"
	done | scanmvn >>ckdep.pom.tmp
	set +o noglob
}
set -A cc_found
set -A cc_where
set -A cc_which
set -A cc_nomvn
ncc=0
[[ ! -s ckdep.ins ]] || while read first rest; do
	[[ $first != ?('#'*) ]] || continue
	if [[ $first = [+!]* ]]; then
		cc_nomvn[ncc]=${first::1}
		first=${first#?}
	fi
	cc_where[ncc]=$first
	cc_which[ncc++]=$rest
done <ckdep.ins
function depround {
	local first=$1 v=$2 scope=$3 rest=$1:$2
	unset vf
	:>ckdep.pom.tmp
	i=-1
	while (( ++i < ncc )); do
		[[ ${cc_where[i]} = "$first":* ]] || continue
		if [[ ${cc_where[i]} = "$rest" ]]; then
			# insert embedded dependencies
			if [[ -n ${cc_found[i]} ]]; then
				print -ru2 -- "[ERROR]" matched \
				    ${cc_where[i]} ${cc_which[i]} \
				    multiple times
				(( abend |= 2 ))
			fi
			cc_found[i]=x
			if [[ -n ${cc_nomvn[i]} ]]; then
				print -ru2 -- "[INFO]" documentation-only \
				    dependencies for ${cc_where[i]}
				dodoc_"${cc_nomvn[i]}" $scope $i ${cc_which[i]}
			else
				print -ru2 -- "[INFO]" analysing embedded \
				    code copies found inside ${cc_where[i]}
				dopom $scope $i ${cc_which[i]}
			fi
			vf=
		elif [[ -z ${vf+x} ]]; then
			vf="$rest (wanted ${cc_where[i]})"
		fi
	done
	if [[ -n $vf ]]; then
		print -ru2 -- "[ERROR]" version mismatch: $vf
		(( abend |= 2 ))
	fi
	if [[ -s ckdep.pom.tmp ]]; then
		local recurse
		set -A recurse
		doscopes <ckdep.pom.tmp |&
		while read -p ga v x; do
			if [[ $x != @(doc-only|unreleased|"$scope") ]]; then
				print -ru2 -- "[ERROR]" unexpected scope \
				    $ga $v "${x@Q}" for $rest $scope
				(( abend |= 2 ))
			fi
			[[ $x = doc-only ]] || print -ru4 -- $ga $v $scope
			print -ru5 -- inside::$rest::$ga $v embedded ok
			recurse+=("$ga $v $scope")
		done
		for x in "${recurse[@]}"; do
			depround $x
		done
	fi
}
while read first v scope; do
	print -ru4 -- $first $v $scope
	print -ru5 -- $first $v $scope ok
	depround $first $v $scope
done <ckdep.mvn.tmp 4>ckdep.mvp.tmp 5>ckdep.audit.tmp
i=-1
while (( ++i < ncc )); do
	if [[ -z ${cc_found[i]} ]]; then
		print -ru2 -- "[ERROR]" did not match \
		    ${cc_where[i]} ${cc_which[i]}
		(( abend |= 2 ))
	fi
done
# ship source only for some scopes
doscopes <ckdep.mvp.tmp | while read ga v scope rest; do
	[[ $scope != @(compile|runtime) ]] || print -r -- ${ga/:/ } $v
done | sort -u >ckdep.mvn.tmp
# add static dependencies from embedded files, for SecurityWatch
[[ ! -s ckdep.inc ]] || cat ckdep.inc >>ckdep.audit.tmp
# generate file with changed dependencies set to be a to-do item
# except we don’t licence-analyse test-only dependencies
doscopes <ckdep.audit.tmp | sort -u >ckdep.tmp
{
	comm -13 ckdep.lst ckdep.tmp | sed 's/ ok$/ TO''DO/'
	comm -12 ckdep.lst ckdep.tmp
} | sed 's/ test TO''DO$/ test ok/' | sort -uo ckdep.tmp

# check if the list changed
if cmp -s ckdep.lst ckdep.tmp && cmp -s ckdep.mvn ckdep.mvn.tmp; then
	print -ru2 -- '[INFO] list of dependencies did not change'
else
	(diff -u ckdep.lst ckdep.tmp || :)
	# make the new list active
	mv -f ckdep.mvn.tmp ckdep.mvn
	mv -f ckdep.tmp ckdep.lst
	# inform the user
	print -ru2 -- '[WARNING] list of dependencies changed!'
	(( abend |= 1 ))
fi
rm -f ckdep.pom ckdep.tmp ckdep.*.tmp
# check if anything needs to be committed
if (( abend & 1 )); then
	print -ru2 -- '[ERROR] please commit the changed ckdep.{lst,mvn} files!'
fi

# fail a release build if dependency licence review has a to-do item
[[ $IS_M2RELEASEBUILD != true ]] || \
    if grep -e ' TO''DO$' -e ' FA''IL$' ckdep.lst; then
	print -ru2 -- '[ERROR] licence review incomplete'
	(( abend |= 4 ))
fi

print -ru2 -- '[INFO] ckdep.sh finished with errorlevel' $((#abend))
exit $abend
