package com.karus.services.exam;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Function;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "exam")
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "examName";
	
	@Id
	@Column(name = COLUMN_ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = COLUMN_NAME)
	private String name;
	
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="examId")
	private Set<ExamEntry> entries;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExamEntry> getEntries() {
		return entries;
	}

	public void setEntries(Set<ExamEntry> entries) {
		this.entries = entries;
	}
	
	public static class ExamNameFunction implements Function<Exam, String> {
		@Override
		public String apply(Exam exam) {
			return exam.getName();
		}
	}
	
}
