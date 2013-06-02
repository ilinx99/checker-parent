package com.karus.exam;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@Component
public class LastAnswerModel {
	private String userAnswer;
	private List<String> correctAnswers;
	private List<String> orginalWords;
	private boolean isCorrect;
	
	public void setModel(String userAnswer, List<String> correctAnswers, List<String> orginalWords){
		this.userAnswer = userAnswer;
		this.correctAnswers = correctAnswers;
		this.orginalWords = orginalWords;
		isCorrect = Iterables.any(correctAnswers, new CorrectTranslationPredicate(userAnswer));
	}
	
	private class CorrectTranslationPredicate implements Predicate<String>{
		private String answer;
		
		public CorrectTranslationPredicate(String answer) {
			this.answer = answer;
		}

		@Override
		public boolean apply(String translation) {
			return answer.equalsIgnoreCase(translation);
		}
	}
	
	public String getUserAnswer() {
		return userAnswer;
	}

	public List<String> getCorrectAnswers() {
		return correctAnswers;
	}
	
	public String getCorrectAnswersString() {
		return StringUtils.join(getCorrectAnswers(), ",");
	}
	
	public String getOrginalWordsString() {
		return StringUtils.join(getOrginalWords(), ",");
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public List<String> getOrginalWords() {
		return orginalWords;
	}
}
