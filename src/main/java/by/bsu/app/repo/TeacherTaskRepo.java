package by.bsu.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import by.bsu.app.entity.TeacherTask;

@Repository
//public interface TeacherTaskRepo extends CrudRepository<TeacherTask,Long> {
public interface TeacherTaskRepo extends JpaRepository<TeacherTask,Long> {
	
	public Optional<TeacherTask> findByfileName(String fileName);
}
