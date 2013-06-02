package com.karus.exam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class ExamResultPanel extends Panel{
	private static final long serialVersionUID = 1L;

	@Autowired
	private LastAnswerPanel lastAnswerPanel;
	
	@Autowired
	private StatsPanel statsPanel;
	
	@PostConstruct
	public void build(){
		this.setCaption("Results");
		this.setStyleName(ChameleonTheme.PANEL_BORDERLESS);
		this.setWidth("100%");
		
		this.addComponent(lastAnswerPanel);
		this.addComponent(statsPanel);
	}
	
	public void setTotalCount(int totalCount){
		statsPanel.setTotalCount(totalCount);
	}

	public void doAnswer(LastAnswerModel model) {
		lastAnswerPanel.doAnswer(model);
		statsPanel.doAnswer(model);
	}

	public void reset() {
		lastAnswerPanel.reset();
		statsPanel.reset();
	}

	public String getPercentage() {
		return statsPanel.getPercentage();
		
	}
}
