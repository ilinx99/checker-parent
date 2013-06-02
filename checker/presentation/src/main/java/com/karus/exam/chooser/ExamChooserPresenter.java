package com.karus.exam.chooser;


import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.karus.exam.ExamView;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

@Component
@Scope("singleton")
public class ExamChooserPresenter {

	@Autowired
	private ExamChooserView examChooserView;
	
	@PostConstruct
	public void build() {
		examChooserView.addOkButtonListener(new SelectNameClickListener());
	}
	
	private class SelectNameClickListener implements ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			String examName = examChooserView.getSelectedExamName();
			if (StringUtils.isBlank(examName)) {
				Notification.show("Please select exam");
			} else{
				Page.getCurrent().setFragment("!" + ExamView.NAME + "/" + examName);
			}
		}
		
	}
}
