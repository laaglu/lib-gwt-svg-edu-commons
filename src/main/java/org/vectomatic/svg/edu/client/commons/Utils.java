/**********************************************
 * Copyright (C) 2010 Lukas Laag
 * This file is part of lib-gwt-svg-edu.
 * 
 * libgwtsvg-edu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * libgwtsvg-edu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with libgwtsvg-edu.  If not, see http://www.gnu.org/licenses/
 **********************************************/
package org.vectomatic.svg.edu.client.commons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;

public class Utils {
	public static boolean isDebug() {
		String debugParam = Window.Location.getParameter("debug");
		return (!GWT.isScript() || debugParam != null);
	}
	public static void handleFatalError(Throwable throwable) {
		String text = "Uncaught exception: ";
		GWT.log(text, throwable);
		while (throwable != null) {
			StackTraceElement[] stackTraceElements = throwable
					.getStackTrace();
			text += throwable.toString() + "\n";
			for (int i = 0; i < stackTraceElements.length; i++) {
				text += "    at " + stackTraceElements[i] + "\n";
			}
			throwable = throwable.getCause();
			if (throwable != null) {
				text += "Caused by: ";
			}
		}
		DialogBox dialogBox = new DialogBox(true);
		DOM.setStyleAttribute(dialogBox.getElement(),
				"backgroundColor", "#ABCDEF");
		System.err.print(text);
		text = text.replaceAll(" ", "&nbsp;");
		dialogBox.setHTML("<pre>" + text + "</pre>");
		dialogBox.center();
	}
	public static void injectMediaQuery(String query, CssResource css) {
		StringBuilder builder = new StringBuilder();
		builder.append("@media ");
		builder.append(query);
		builder.append(" {");
		builder.append(css.getText());
		builder.append("}");
		StyleInjector.injectAtEnd(builder.toString());
	}

}
