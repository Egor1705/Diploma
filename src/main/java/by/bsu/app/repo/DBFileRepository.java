package by.bsu.app.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.Subj;



public interface DBFileRepository extends CrudRepository<DBFile, Long> {

//	Optional<DBFile> findBySubjectId(Long id);
	
	//Optional<DBFile> findByIdAndSubjectId(Long id,Long subject_id);
	
	 Optional<DBFile> findByfileName(String name);
	 Optional<DBFile> findById(Long id);
	// Optional<DBFile> findByDeadLine(Date deadLine); 
}
