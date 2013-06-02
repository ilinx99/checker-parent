package com.karus.exam;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.karus.custom.LabelWithValue;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class StatsPanel extends VerticalLayout{
	private static final String HUNDRED_STRING = "100";
	private static final String ZERO_STRING = "0";
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static final long serialVersionUID = 1L;
	private LabelWithValue correctAnswers;
	private LabelWithValue incorrectAnswers;
	private LabelWithValue percentage;
	private LabelWithValue totalAnswers;
	private LabelWithValue leftAnswers;
	
	@PostConstruct
	public void build(){
		this.setStyleName(ChameleonTheme.PANEL_BUBBLE);
		this.setWidth("100%");
		
		initializeLabelsWithValues();
		
		this.addComponent(totalAnswers);
		this.addComponent(leftAnswers);
		this.addComponent(correctAnswers);
		this.addComponent(incorrectAnswers);
		this.addComponent(percentage);
	}

	private void initializeLabelsWithValues() {
		totalAnswers = new LabelWithValue("Total:", ZERO_STRING); 
		leftAnswers = new LabelWithValue("Left:", ZERO_STRING); 
		correctAnswers = new LabelWithValue("Correct:", ZERO_STRING);
		incorrectAnswers = new LabelWithValue("Incorrect:", ZERO_STRING);
		percentage = new LabelWithValue("Percentage:", HUNDRED_STRING);
	}

	public void setTotalCount(int totalCount) {
		String totalCountStr = Integer.valueOf(totalCount).toString();
		totalAnswers.setValue(totalCountStr);
		leftAnswers.setValue(totalCountStr);
	}

	public void doAnswer(LastAnswerModel model) {
		leftAnswers.decrement();
		if (model.isCorrect()){
			correctAnswers.increment();
		} else {
			incorrectAnswers.increment();
		}
		updatePercentage();
	}

	private void updatePercentage() {
		BigDecimal correctCount = new BigDecimal(correctAnswers.getIntValue());
		BigDecimal incorrectCount = new BigDecimal(incorrectAnswers.getIntValue());
		BigDecimal totalCount = incorrectCount.add(correctCount);
		
		BigDecimal percent = correctCount.multiply(ONE_HUNDRED).divide(totalCount, 2, RoundingMode.HALF_UP);
		percentage.setValue(percent.toString());
	}

	public void reset() {
		leftAnswers.setValue(totalAnswers.getValue());
		correctAnswers.setValue(ZERO_STRING);
		incorrectAnswers.setValue(ZERO_STRING);
		percentage.setValue(HUNDRED_STRING);
	}

	public String getPercentage() {
		return percentage.getValue();
	}
}
