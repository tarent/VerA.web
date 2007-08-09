/*
 * tarent commons,
 * a set of common components and solutions
 * Copyright (c) 2006-2007 tarent GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License,version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * tarent GmbH., hereby disclaims all copyright
 * interest in the program 'tarent commons'
 * Signature of Elmar Geese, 14 June 2007
 * Elmar Geese, CEO tarent GmbH.
 */

package de.tarent.commons.logging;

import java.io.PrintWriter;
import java.util.logging.LogRecord;

import de.tarent.commons.utils.StringTools;
import de.tarent.commons.utils.Tools;

public class LogFormatter {
	
	
	public void format(PrintWriter writer, LogRecord record) {
		//assert writer != null;
		try {
			String threadName = Thread.currentThread().getName();
			
			// Loglevel and thread information
			writer.println();
			writer.println("  " + record.getLevel() + "   (in thread: " + record.getThreadID() + ", " + threadName + ")");
			writer.println();
			
			// Logged in class/method
			writer.println("  Logged in (class and methodname):");
			writer.println();
			writer.println("    " + record.getSourceClassName() + "#" + record.getSourceMethodName());
			writer.println();
			
			// Logged by category
			writer.println("  Logger by (logger/category name):");
			writer.println();
			writer.println("    " + record.getLoggerName());
			writer.println();
			
			// Logmessage
			if ((-1 != record.getMessage().indexOf("\n")) || (-1 != record.getMessage().indexOf("\r"))) {
				writer.println();
				writer.println("  Multiline log message:");
				writer.println();
				writer.println("  >>>" + record.getMessage() + "<<<");
				writer.println();
				writer.println();
			} else {
				writer.println();
				writer.println("  Log message:");
				writer.println();
				writer.println("    " + record.getMessage());
				writer.println();
				writer.println();
			}
			
			// Threadlogger
			if (ThreadLogger.isInstanceAvailable()) {
				ThreadLogger threadLogger = ThreadLogger.getInstance();
				writer.println("  Available method calls of current threadlogger (" + threadLogger.getThreadId() + "):");
				writer.println();
				writer.println(Tools.iteratorToString(threadLogger.getMethodCalls().iterator(), "    ", true, StringTools.LINE_SEPERATOR, true));
				writer.println();
			}
			
			// Stacktrace
			if (record.getThrown() != null) {
				writer.println("  Available Stacktrace of this exception:");
				writer.println();
				format(writer, record.getThrown());
			}
			
			writer.println();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void format(PrintWriter writer, Throwable throwable) {
		format(writer, throwable, 0);
	}

	protected void format(PrintWriter writer, Throwable throwable, int ignoreLines) {
		writer.print("    ");
		if (ignoreLines != 0)
			writer.print("Caused by: ");
		writer.println(throwable);
		
		int x = 0;
		
		StackTraceElement ste[] = throwable.getStackTrace();
		for (int i = 0; i < ste.length; i++) {
			if (x < (ste.length - ignoreLines))
				writer.println("        at " + ste[i]);
			x++;
		}
		
		Throwable cause = throwable.getCause();
		if (cause != null)
			format(writer, cause, x - 1);
	}
}
