package by.bsu.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.bsu.app.entity.Subject;
import by.bsu.app.entity.TeacherTask;
import by.bsu.app.repo.SubjectRepo;
import by.bsu.app.repo.TeacherTaskRepo;


@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	SubjectRepo subjectRepo;
	
	@Autowired
	TeacherTaskRepo teacherTaskRepo;

	@Override
	public Iterable<Subject> findAll() {
		// TODO Auto-generated method stub
		return subjectRepo.findAll();
	}

	@Transactional
	@Override
	public Subject save(Subject subject) {
		// TODO Auto-generated method stub
//		Subject subject = new Subject();
//		List<TeacherTask> list = collectTasksByIds(teacherTaskIds);
//		subject.setTasks(list);
		return subjectRepo.save(subject);
	}

	@Transactional
	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		subjectRepo.deleteById(id);
	}

	@Override
	public Optional<Subject> findById(long id) {
		// TODO Auto-generated method stub
		
		return subjectRepo.findById(id);
	}

	@Transactional
	@Override
	public void addTeacherTasks(Subject subject, List<Long> teacherTaskIds) {
		// TODO Auto-generated method stub
		List<TeacherTask> tasks = subject.getTasks();
		List<TeacherTask> newTasks = tasks;
		if(tasks == null) {
			tasks = new ArrayList<>();
		}
		else {
			tasks = new ArrayList<>(tasks);
		}
		newTasks.addAll(collectTasksByIds(teacherTaskIds));
		subject.setTasks(newTasks);
		subjectRepo.save(subject);
	}
	
	private List<TeacherTask> collectTasksByIds(List<Long> teacherTaskIds){
		return teacherTaskIds.stream().map(teacherTaskRepo::getReferenceById).collect(Collectors.toList());
	}

	@Override
	public Subject findBySubjName(String subjName) {
		// TODO Auto-generated method stub
		return subjectRepo.findBySubjName(subjName);
	}

}
