package by.bsu.app.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.bsu.app.entity.Subject;
import by.bsu.app.entity.TeacherTask;

@Repository
public interface SubjectRepo extends CrudRepository<Subject,Long> {

	public Subject findBySubjName(String subjName);
	
}
