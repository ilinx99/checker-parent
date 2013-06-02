package com.karus.exam;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.karus.exam.chooser.ExamChooserView;
import com.karus.services.exam.ExamService;
import com.karus.services.exam.dto.DictionaryEntriesModel;
import com.karus.services.exam.dto.DictionaryEntry;
import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

@Component
@Scope("singleton")
public class ExamPresenter{
	@Autowired
	private ExamView view;
	
	@Autowired
	@Qualifier("examService")
	private ExamService examService;
	
	private DictionaryEntriesModel dictEntries;
	
	@Autowired
	private LastAnswerModel model;

	private DictionaryEntry lastEntry;
	private ClickListener startClickListener;
	private ClickListener nextAnswerClickListener;
	private ClickListener resetAnswerClickListener;
	private ClickListener changeExamClickListener;
	
	@PostConstruct
	public void build() {
		startClickListener = new StartClickListener();
		nextAnswerClickListener = new NextAnswerClickListener();
		resetAnswerClickListener = new ResetClickListener();
		changeExamClickListener = new ChangeExamClickListener();
		
		view.addStartListener(startClickListener);
		view.addNextAnswerClickListener(nextAnswerClickListener);
		view.addResetClickListener(resetAnswerClickListener);
		view.addChangeExamClickListener(changeExamClickListener);
	}

	private class StartClickListener implements ClickListener {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void buttonClick(ClickEvent event) {
			String examName = view.getExamName();
			dictEntries = examService.getDictEntriesByName(examName);
			
			lastEntry = dictEntries.next();
			int totalElementCount = dictEntries.size();
			view.startExam();
			view.setFirstQuestion(lastEntry, totalElementCount);
		}
	}
	
	private class NextAnswerClickListener implements ClickListener {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void buttonClick(ClickEvent event) {
			String userAnswer = view.getAnswer();
			if (StringUtils.isBlank(userAnswer)){
				Notification.show("Your answer cannot be blank");
				return;
			}
			
			if (lastEntry != null){
				model.setModel(userAnswer, lastEntry.getEngWords(), lastEntry.getPolWords());
				view.doAnswer(model);
			}
			
			lastEntry = dictEntries.next();
			if (lastEntry != null){
				view.updateNextQuestion(lastEntry);
			} else {
				view.endExam();
			}
		}
	}
	
	private class ResetClickListener implements ClickListener {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void buttonClick(ClickEvent event) {
			view.reset();
			dictEntries.shuffle();
			lastEntry = dictEntries.next();
			int totalElementCount = dictEntries.size();
			view.setFirstQuestion(lastEntry, totalElementCount);
		}
	}
	
	private class ChangeExamClickListener implements ClickListener {
		private static final long serialVersionUID = 1L;
		
		@Override
		public void buttonClick(ClickEvent event) {
			dictEntries.clear();
			view.reset();
			Page.getCurrent().setFragment("!" + ExamChooserView.NAME);
		}
	}
}
