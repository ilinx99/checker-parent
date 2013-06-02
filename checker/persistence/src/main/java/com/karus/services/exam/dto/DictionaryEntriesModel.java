package com.karus.services.exam.dto;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.thirdparty.guava.common.collect.Lists;

public class DictionaryEntriesModel {
	private static final String ENTRY_SEPARATOR = ",";
	private static final String POL_ENG_SEPARATOR = "-";

	private String examName;
	private List<DictionaryEntry> entries = Lists.newArrayList();
	private Iterator<DictionaryEntry> entriesIterator;
	
	public void parseLine(String strLine) throws BadEntryParseException {
		String[] polEngEntries = strLine.split(POL_ENG_SEPARATOR);
		if (polEngEntries.length != 2) {
			throw new BadEntryParseException("Missing or too many separators");
		}

		String polEntries = polEngEntries[0].trim();
		String engEntries = polEngEntries[1].trim();

		DictionaryEntry dictEntry = new DictionaryEntry(createListOfEntries(engEntries),
				createListOfEntries(polEntries));
		entries.add(dictEntry);
	}

	private List<String> createListOfEntries(String entries) throws BadEntryParseException {
		String[] entriesTab = entries.split(ENTRY_SEPARATOR);
		if (entriesTab.length == 0) {
			throw new BadEntryParseException("No entries for specified language");
		}

		return Lists.newArrayList(entriesTab);
	}
	
	public void shuffle(){
		Collections.shuffle(entries);
		entriesIterator = entries.iterator();
	}
	
	public DictionaryEntry next(){
		if (entriesIterator == null){
			shuffle();
		}
		
		if (!entriesIterator.hasNext()) {
			return null;
		}

		return entriesIterator.next();
	}
	
	public int size(){
		return entries.size();
	}

	public void clear() {
		entries = Lists.newArrayList();
		entriesIterator = null; 
	}

	public List<DictionaryEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<DictionaryEntry> entries) {
		this.entries = entries;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
}
