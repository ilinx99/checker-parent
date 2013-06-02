package com.karus.navigation;

import com.vaadin.ui.VerticalLayout;

public class CheckerLayout extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	
	private TopBar topBar;
	
	public CheckerLayout() {
		topBar = new TopBar();
		this.addComponent(topBar);
	}
	
}
