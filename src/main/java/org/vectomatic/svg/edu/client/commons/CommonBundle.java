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

import org.vectomatic.dom.svg.ui.SVGResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

/**
 * Bundle class for resources which are common to
 * all lig-gwt-svg-edu games
 * @author laaglu
 */
public interface CommonBundle extends ClientBundle {
	public static CommonBundle INSTANCE = GWT.create(CommonBundle.class);
	@Source("pulsante_03_architetto_f_01a.svg")
	public SVGResource next();
	@Source("pulsante_04_architetto_f_01a.svg")
	public SVGResource previous();
	@Source("tasto_9_architetto_franc_01.svg")
	public SVGResource about();
	@Source("common.css")
	public CommonCss css();
}
