package com.karus.services.exam;

import java.util.List;

import javax.jws.WebService;

import com.karus.services.exam.dto.DictionaryEntriesModel;

@WebService
public interface ExamService {

	void save(DictionaryEntriesModel model);

	List<String> getExamNames();

	DictionaryEntriesModel getDictEntriesByName(String examName);
}
