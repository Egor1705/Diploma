package by.bsu.app.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsu.app.entity.Subject;
import by.bsu.app.entity.TeacherTask;
import by.bsu.app.repo.SubjectRepo;
import by.bsu.app.repo.TeacherTaskRepo;
import jakarta.transaction.Transactional;

@Service
public class TeacherTaskServiceImpl implements TeacherTaskService {

	@Autowired 
	TeacherTaskRepo teacherTaskRepo;
	
	@Autowired 
	SubjectService subjectService;
	
	@Override
	public Iterable<TeacherTask> findAll() {
		// TODO Auto-generated method stub
		return teacherTaskRepo.findAll();
	}

	@Transactional
	@Override
	public TeacherTask save(TeacherTask teacherTask) {
		// TODO Auto-generated method stub
		return teacherTaskRepo.save(teacherTask);
	}

	@Transactional
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		teacherTaskRepo.deleteById(id);
	}

	@Override
	public Optional<TeacherTask> findById(long id) {
		// TODO Auto-generated method stub
		return teacherTaskRepo.findById(id);
	}

	@Override
	public Optional<TeacherTask> findByfileName(String fileName) {
		// TODO Auto-generated method stub
		return teacherTaskRepo.findByfileName(fileName);
	}

	@Override
	@Transactional
	public void addToSubject(long id, String name) {
		// TODO Auto-generated method stub
		Subject subject = subjectService.findBySubjName(name);
		if(subject==null) {
			throw new RuntimeException("Subject not found "+name);
		}
		subjectService.addTeacherTasks(subject, Collections.singletonList(id));
	}

}
