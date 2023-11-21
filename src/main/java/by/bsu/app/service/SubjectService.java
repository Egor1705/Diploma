package by.bsu.app.service;

import java.util.List;
import java.util.Optional;

import by.bsu.app.entity.Subject;


public interface SubjectService {

	
	public Iterable<Subject> findAll();
	
	public Subject save(Subject subject);
	
	public void deleteById(long id);
	
	public Optional<Subject> findById(long id);
	
	public void addTeacherTasks(Subject subject,List<Long> teacherTaskIds);
	
	public Subject findBySubjName(String subjName);
}
