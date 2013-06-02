package com.karus.exam.chooser;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button.ClickListener;

public interface ExamChooserView extends View {
	String NAME = "examChooser";

	void addOkButtonListener(ClickListener listener);
	String getSelectedExamName();
}
