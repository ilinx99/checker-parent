package com.karus.exam;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class ExamIntroductionPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private Button startButton;
	
	@PostConstruct
	public void build() {
		this.setCaption("Exam");
		this.setStyleName(ChameleonTheme.PANEL_BORDERLESS);
		this.setWidth("100%");
		
		startButton = new Button("Go!");
		this.addComponent(new Label("Press Go! to start the exam"));
		this.addComponent(startButton);
	}
	
	public void addStartListener(ClickListener listener){
		startButton.addClickListener(listener);
	}
}
