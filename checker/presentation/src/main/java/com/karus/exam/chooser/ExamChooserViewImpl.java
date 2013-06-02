package com.karus.exam.chooser;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.karus.navigation.CheckerLayout;
import com.karus.services.exam.ExamService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickListener;

@Component
@Scope("singleton")
@VaadinView(ExamChooserView.NAME)
public class ExamChooserViewImpl extends CheckerLayout implements ExamChooserView {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ExamNamePicker examNamePicker;
	
	@Autowired
	@Qualifier("examService")
	private ExamService examService;
	
	
	@PostConstruct
	public void build(){
		this.addComponent(examNamePicker);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		List<String> examNames = examService.getExamNames();
		examNamePicker.setExamNames(examNames);
		examNamePicker.setVisible(true);
	}
	
	@Override
	public void addOkButtonListener(ClickListener listener) {
		examNamePicker.addOkButtonListener(listener);
	}

	@Override
	public String getSelectedExamName() {
		return examNamePicker.getSelectedExamName();
	}

}
