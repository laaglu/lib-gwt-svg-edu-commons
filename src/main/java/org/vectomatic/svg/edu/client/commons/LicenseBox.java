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


import java.util.HashMap;
import java.util.Map;

import org.vectomatic.dom.svg.ui.SVGPushButton;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGFace;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGFaceName;
import org.vectomatic.dom.svg.ui.SVGButtonBase.SVGStyleChange;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Dialog box to display the license
 * @author laaglu
 */
public class LicenseBox {
	interface LicenseBoxBinder extends UiBinder<VerticalPanel, LicenseBox> {
	}
	private static LicenseBoxBinder licenseBoxBinder = GWT.create(LicenseBoxBinder.class);

	@UiField
	HTML about;
	@UiField
	Button okButton;
	
	private DialogBox box;

	public static Widget createAboutButton() {
		Map<SVGFaceName, SVGFace> faces = new HashMap<SVGFaceName, SVGFace>();
		faces.put(SVGFaceName.UP, new SVGFace(new SVGStyleChange[] { new SVGStyleChange( new String[] { CommonBundle.INSTANCE.css().navigationUp()} )}));
		faces.put(SVGFaceName.UP_HOVERING, new SVGFace(new SVGStyleChange[] { new SVGStyleChange( new String[] { CommonBundle.INSTANCE.css().navigationUpHovering()} )}));
		SVGPushButton aboutButton = new SVGPushButton(CommonBundle.INSTANCE.about().getSvg(), faces);
		aboutButton.setClassNameBaseVal(CommonBundle.INSTANCE.css().navigationPanelMenuButton());
		final LicenseBox licenseBox = new LicenseBox();
		aboutButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				licenseBox.getBox().center();
				licenseBox.getBox().show();
			}
		});
		return aboutButton;
	}

	public LicenseBox() {
		box = new DialogBox();
		box.setAnimationEnabled(true);
		box.setGlassEnabled(true);
		box.setTitle(CommonConstants.INSTANCE.license());
		box.setWidget(licenseBoxBinder.createAndBindUi(this));
		about.setHTML(CommonConstants.INSTANCE.about());
	}
	
	public DialogBox getBox() {
		return box;
	}

	@UiHandler("okButton")
	public void license(ClickEvent event) {
		box.hide();
	}
}
