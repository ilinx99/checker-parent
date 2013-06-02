package com.karus.exam;

import com.karus.services.exam.dto.DictionaryEntry;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickListener;

public interface ExamView extends View {
	String NAME = "exam";
	
	void addStartListener(ClickListener listener);

	void startExam();

	void addNextAnswerClickListener(ClickListener listener);

	String getAnswer();

	void doAnswer(LastAnswerModel model);

	void updateNextQuestion(DictionaryEntry lastEntry);

	void endExam();

	void addResetClickListener(ClickListener resetAnswerClickListener);

	void reset();

	void setFirstQuestion(DictionaryEntry dictEntry, int totalElementCount);

	void addChangeExamClickListener(ClickListener changeExamClickListener);
	
	String getExamName();
}

