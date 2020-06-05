package by.bsu.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.DBFileStudent;
import by.bsu.app.entity.Subj;
import by.bsu.app.entity.MyUser;

public interface DBFileStudRepo extends CrudRepository<DBFileStudent, Long> {

	
	Optional<DBFileStudent> findByfileName(String fileName);
	
	 @Query("SELECT d FROM DBFileStudent d LEFT JOIN d.user u where d.id = u.id")
		List<DBFileStudent> findStudFiles();
	
	 @Query("SELECT d FROM DBFileStudent d LEFT JOIN d.user u where u.course = '2 course 5 group'")
		List<DBFileStudent> findStudFilesBy2Course();
	 
	 @Query("SELECT d FROM DBFileStudent d LEFT JOIN d.user u where u.course = '3 course'")
		List<DBFileStudent> findStudFilesBy3Course();
	
	 @Query("SELECT COUNT(fileName) FROM DBFileStudent")
		long count();
	 
	// DBFileStudent findTasks(String fileName);
}
