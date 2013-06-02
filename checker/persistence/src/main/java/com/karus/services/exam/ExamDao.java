package com.karus.services.exam;

import java.util.List;

public interface ExamDao {
	void save(Exam exam);

	List<Exam> getExams();

	Exam findExamByName(String examName);
}
