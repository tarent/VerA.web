#!/usr/bin/env mksh

set -ex
cd "$(dirname "$0")"
tag=$1
set -A ours

(docker system prune -f --volumes || docker system prune -f || exit 1)

doone() {
	cd "$1"
	docker build --no-cache -t veraweb-tools.lan.tarent.de:5000/"$2" .
	cd -
	docker push veraweb-tools.lan.tarent.de:5000/"$2"
	[[ $tag = latest ]] && return 0
	docker tag veraweb-tools.lan.tarent.de:5000/"$2" \
	    veraweb-tools.lan.tarent.de:5000/"$2:$tag"
	docker push veraweb-tools.lan.tarent.de:5000/"$2:$tag"
	set -A ours -- "${ours[@]}" veraweb-tools.lan.tarent.de:5000/"$2:$tag"
}

if [[ $tag = latest ]]; then
	ln ~/jenkins-tmp/latest/veraweb.war core/
	ln ~/jenkins-tmp/latest/vwor.war vwor/
	ln ~/jenkins-tmp/latest/veraweb-core-*-files.tgz files.tgz
else
	wget -O core/veraweb.war \
	    "https://repo-bn-01.lan.tarent.de/repository/maven-releases/org/evolvis/veraweb/veraweb-core/$tag/veraweb-core-$tag.war"
	wget -O vwor/vwor.war \
	    "https://repo-bn-01.lan.tarent.de/repository/maven-releases/org/evolvis/veraweb/rest-api/$tag/rest-api-$tag.war"
	wget -O files.tgz \
	    "https://repo-bn-01.lan.tarent.de/repository/maven-releases/org/evolvis/veraweb/veraweb-core/$tag/veraweb-core-$tag-files.tgz"
fi

ln -f freexian-archive-keyring.tar.gz httpd/
ln -f freexian-archive-keyring.tar.gz ldap/

set -A baseimages -- \
    debian:jessie \
    tomcat:8
set -A externals -- \
    postgres:9.3

for x in "${baseimages[@]}" "${externals[@]}"; do
	docker pull "$x"
done

doone core veraweb-core
doone vwor veraweb-rest-api
# extra
doone httpd veraweb-httpd
doone ldap veraweb-ldap

# release export
[[ $tag = latest ]] && exit 0

rm -rf "/var/www/html/$tag~"
mkdir "/var/www/html/$tag~"
for x in "${ours[@]}" "${externals[@]}"; do
	img=${x##*/}
	img=${img//:/-}
	docker save -o "/var/www/html/$tag~/$img.img" "$x"
	gzip -n9 "/var/www/html/$tag~/$img.img" &
done
wait
rm -rf "/var/www/html/$tag"
mv "/var/www/html/$tag~" "/var/www/html/$tag"
chmod 444 "/var/www/html/$tag/"*
chmod 555 "/var/www/html/$tag"
ls -la "/var/www/html/$tag/"
