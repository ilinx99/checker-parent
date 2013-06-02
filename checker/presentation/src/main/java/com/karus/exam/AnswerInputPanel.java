package com.karus.exam;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.karus.custom.LabelWithValue;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class AnswerInputPanel extends VerticalLayout{
	private static final long serialVersionUID = 1L;

	private LabelWithValue initialQuestion;
	private TextField answerInput;
	private HorizontalLayout buttonsLayaout;
	private Button answerButton;
	private Button resetButton;
	private Button changeWordsButton;
	
	@PostConstruct
	public void build(){
		this.setCaption("Exam");
		this.setStyleName(ChameleonTheme.PANEL_LIGHT);
		this.setWidth("100%");
		
		buttonsLayaout = new HorizontalLayout();
		buttonsLayaout.setSpacing(true);
		initialQuestion = new LabelWithValue("Translate following word(s) to english:");
		answerInput = new TextField();
		answerButton = new Button("Ok");
		resetButton = new Button("Reset");
		changeWordsButton = new Button("Change exam");
		
		answerButton.setStyleName(ChameleonTheme.BUTTON_BORDERLESS);
		answerButton.setStyleName(ChameleonTheme.BUTTON_SMALL);
		buttonsLayaout.addComponent(answerButton);
		
		resetButton.setStyleName(ChameleonTheme.BUTTON_BORDERLESS);
		resetButton.setStyleName(ChameleonTheme.BUTTON_SMALL);
		buttonsLayaout.addComponent(resetButton);
		
		changeWordsButton.setStyleName(ChameleonTheme.BUTTON_BORDERLESS);
		changeWordsButton.setStyleName(ChameleonTheme.BUTTON_SMALL);
		buttonsLayaout.addComponent(changeWordsButton);
		
		addComponent(initialQuestion);
		addComponent(answerInput);
		addComponent(buttonsLayaout);
	}
	
	public void setWordsToTranslate(List<String> wordsToTranslate) {
		String words = StringUtils.join(wordsToTranslate, ",");
		initialQuestion.setValue(words);
		answerInput.setValue(StringUtils.EMPTY);
	}

	public void addNextAnswerClickListener(ClickListener listener) {
		answerButton.addClickListener(listener);
	}

	public String getAnswer() {
		return answerInput.getValue();
	}

	public void endExam() {
		answerButton.setEnabled(false);
	}

	public void reset() {
		answerButton.setEnabled(true);
	}

	public void addResetClickListener(ClickListener resetClickListener) {
		resetButton.addClickListener(resetClickListener);
	}

	public void addChangeExamClickListener(ClickListener changeExamClickListener) {
		changeWordsButton.addClickListener(changeExamClickListener);
	}
}
