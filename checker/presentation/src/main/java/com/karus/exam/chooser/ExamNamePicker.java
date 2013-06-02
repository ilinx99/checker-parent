package com.karus.exam.chooser;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
@Scope("singleton")
public class ExamNamePicker extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private ComboBox examNames;
	private Button selectName;

	@PostConstruct
	public void build() {
		examNames = new ComboBox("Select exam name");
		examNames.setInvalidAllowed(false);
		examNames.setNullSelectionAllowed(false);
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(examNames);
		selectName = new Button("Ok");

		selectName.setStyleName(ChameleonTheme.BUTTON_BORDERLESS);
		selectName.setStyleName(ChameleonTheme.BUTTON_SMALL);

		this.addComponent(formLayout);
		this.addComponent(selectName);
	}

	public void setExamNames(List<String> names) {
		for (String name : names) {
			examNames.addItem(name);
		}
	}

	public void addOkButtonListener(ClickListener listener) {
		selectName.addClickListener(listener);

	}

	public String getSelectedExamName() {
		return (String) examNames.getValue();
	}

}
