package com.karus.services.exam.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.karus.services.exam.Lang;
import com.karus.services.exam.Word;

public class DictionaryEntry {
	private List<String> engWords;
	private List<String> polWords;
	
	public DictionaryEntry() {
		this.engWords = Lists.newArrayList();
		this.polWords = Lists.newArrayList();
	}

	public DictionaryEntry(String engWord, String polWord) {
		this.engWords = Lists.newArrayList(engWord);
		this.polWords = Lists.newArrayList(polWord);
	}
	
	public DictionaryEntry(List<String> engWords, List<String> polWords) {
		this.engWords = engWords;
		this.polWords = polWords;
	}

	public List<String> getEngWords() {
		return engWords;
	}

	public void setEngWords(List<String> engWords) {
		this.engWords = engWords;
	}

	public List<String> getPolWords() {
		return polWords;
	}

	public void setPolWords(List<String> polWords) {
		this.polWords = polWords;
	}

	public void addWord(Word word) {
		Lang lang = word.getLang();
		if (Lang.POL.equals(lang)){
			polWords.add(word.getText());
			return;
		} else if (Lang.ENG.equals(lang)) {
			engWords.add(word.getText());
			return;
		}
		
		throw new IllegalArgumentException("Unknown language type = " + lang);
	}
}
