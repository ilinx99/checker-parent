package com.karus.exam;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.karus.custom.LabelWithValue;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class LastAnswerPanel extends HorizontalLayout{
	private static final long serialVersionUID = 1L;
	
	private LabelWithValue yourLastAnswer;
	private LabelWithValue correctLastAnswer;
	
	@PostConstruct
	public void build() {
		this.setStyleName(ChameleonTheme.PANEL_BUBBLE);
		this.setWidth("100%");
		this.setHeight(20.0f, Unit.PIXELS);
		
		initializeLabelsWithValues();
		
		this.addComponent(yourLastAnswer);
		this.addComponent(correctLastAnswer);
	}

	private void initializeLabelsWithValues() {
		yourLastAnswer = new LabelWithValue("Your last answer:");
		correctLastAnswer = new LabelWithValue("Correct answer:");
	}

	public void doAnswer(LastAnswerModel model) {
		yourLastAnswer.setValue(model.getUserAnswer());
		correctLastAnswer.setValue(model.getCorrectAnswersString());
	}

	public void reset() {
		yourLastAnswer.setValue(StringUtils.EMPTY);
		correctLastAnswer.setValue(StringUtils.EMPTY);
	}
}
