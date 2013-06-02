package com.karus.services.exam;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.google.gwt.thirdparty.guava.common.collect.Sets;
import com.karus.services.exam.dto.DictionaryEntriesModel;
import com.karus.services.exam.dto.DictionaryEntry;

@Component
public class EntriesModelConverter {

	public Exam toExam(DictionaryEntriesModel model) {
		Exam exam = new Exam();
		exam.setName(model.getExamName());
		
		Set<ExamEntry> examEntries = createEntries(model.getEntries());
		exam.setEntries(examEntries);
		
		return exam;
	}

	private Set<ExamEntry> createEntries(List<DictionaryEntry> entries) {
		Set<ExamEntry> examEntries = Sets.newHashSet();
		
		for (DictionaryEntry dictEntry : entries) {
			ExamEntry examEntry = new ExamEntry();
			examEntry.setWords(toWords(dictEntry));
			
			examEntries.add(examEntry);
		}
		
		return examEntries;
	}

	private Set<Word> toWords(DictionaryEntry dictEntry) {
		Set<Word> polWords = toLangWords(dictEntry.getPolWords(), Lang.POL);
		Set<Word> engWords = toLangWords(dictEntry.getEngWords(), Lang.ENG);
		
		polWords.addAll(engWords);
		
		return polWords;
	}

	private Set<Word> toLangWords(List<String> textWords, Lang lang) {
		Set<Word> words = Sets.newHashSet();
		
		for (String text : textWords) {
			Word word = new Word();
			word.setLang(lang);
			word.setText(text);
			
			words.add(word);
		}
		
		return words;
	}

	public DictionaryEntriesModel toDictionaryEntriesModel(Exam exam) {
		DictionaryEntriesModel model = new DictionaryEntriesModel();
		model.setExamName(exam.getName());
		
		List<DictionaryEntry> dictEntries = toDictionaryEntries(exam.getEntries());
		model.setEntries(dictEntries);
		
		return model;
	}

	private List<DictionaryEntry> toDictionaryEntries(Set<ExamEntry> entries) {
		List<DictionaryEntry> dictEntries = Lists.newArrayList();
		
		for (ExamEntry examEntry : entries) {
			dictEntries.add(toDictEntry(examEntry));
		}
		
		return dictEntries;
	}

	private DictionaryEntry toDictEntry(ExamEntry examEntry) {
		DictionaryEntry dictEntry = new DictionaryEntry();
		for (Word word : examEntry.getWords()) {
			dictEntry.addWord(word);
		}

		return dictEntry;
	}



}
