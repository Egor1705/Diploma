package by.bsu.app.repo;




import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.Subj;



public interface DBFileRepository extends CrudRepository<DBFile, Long> {

//	Optional<DBFile> findBySubjectId(Long id);
	
	//Optional<DBFile> findByIdAndSubjectId(Long id,Long subject_id);
	
	 Optional<DBFile> findByfileName(String name);
	 Optional<DBFile> findById(Long id);
	// Optional<DBFile> findByDeadLine(Date deadLine); 
//	 @Query("SELECT COUNT(fileName) FROM DBFile")
//		long count();
	 
	 @Query("SELECT COUNT(fileName) FROM DBFile ")
	 long countFiles();
	 
	 @Query("SELECT COUNT(*)FROM DBFile GROUP BY subject_id")
	 long[] countLabs();
	 
	 @Query("Select min(deadL) From DBFile Group by subject_id")
	 Date[] firstDeadLine();
//	 @Query("SELECT COUNT(*) from DBFile LEFT JOIN Subj on subjects.subj_id=files.subject_id group by subject_id ")
//	 List<Long> countLabs();
	 
}
