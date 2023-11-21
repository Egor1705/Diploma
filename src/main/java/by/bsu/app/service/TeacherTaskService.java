package by.bsu.app.service;

import java.util.Optional;


import by.bsu.app.entity.TeacherTask;

public interface TeacherTaskService {
	
public Iterable<TeacherTask> findAll();
	
	public TeacherTask save(TeacherTask teacherTask);
	
	public void deleteById(long id);
	
	public Optional<TeacherTask> findById(long id);
	
	public Optional<TeacherTask> findByfileName(String fileName);
	
	public void addToSubject(long id,String name);

}
