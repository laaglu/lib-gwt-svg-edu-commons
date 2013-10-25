package org.vectomatic.svg.edu.client.commons;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ClassName;

public interface MediaQueriesCss extends CssResource {
	@ClassName("game")
	public String game();
	@ClassName("nav-panel")
	public String navigationPanel();
	@ClassName("nav-panel-menu-btn")
	public String navigationPanelMenuButton();
	@ClassName("nav-panel-prev-btn")
	public String navigationPanelPrevButton();
	@ClassName("nav-panel-next-btn")
	public String navigationPanelNextButton();
	@ClassName("nav-panel-difficulty-picker")
	public String navigationPanelDifficultyPicker();
	@ClassName("difficulty-picker-selected")
	public String difficultyPickerSelected();
}
