package com.karus.services.exam;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.karus.services.exam.dto.DictionaryEntriesModel;

@Service("examServiceImpl")
@WebService(endpointInterface = "com.karus.services.exam.ExamService")
public class ExamServiceImpl implements ExamService {
	@Autowired
	private EntriesModelConverter converter;
	
	@Autowired
	private ExamDao examDao;
	
	@Override
	@Transactional
	public void save(DictionaryEntriesModel model) {
		Exam exam = converter.toExam(model);
		examDao.save(exam);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getExamNames() {
		List<Exam> exams = examDao.getExams();
		List<String> examNames = Lists.transform(exams, new Exam.ExamNameFunction());
		
		return examNames;
	}

	@Override
	@Transactional(readOnly = true)
	public DictionaryEntriesModel getDictEntriesByName(String examName) {
		Exam exam = examDao.findExamByName(examName);
		DictionaryEntriesModel dictionaryEntriesModel = converter.toDictionaryEntriesModel(exam);
		
		return dictionaryEntriesModel;
	}

}
