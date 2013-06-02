package com.karus.home;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.karus.navigation.CheckerLayout;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

@Component
@Scope("singleton")
@VaadinView(HomeView.NAME)
public class HomeViewImpl extends CheckerLayout implements HomeView {
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void build() {
		this.addComponent(new Label("This is home page"));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
