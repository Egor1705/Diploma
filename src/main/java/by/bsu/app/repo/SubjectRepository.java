package by.bsu.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import by.bsu.app.entity.Subj;



public interface SubjectRepository extends CrudRepository<Subj, Long> {

	//Subj findByPrefix(String prefix);
	Optional <Subj> findById(Long id);
	//Subj findById(Long id);
	
	void deleteBySubjName(String subjName);
	
	//Subj findByid(Long id);
//	@Query("SELECT s FROM Subj s where s.subjName = :subjName")
//	Subj findSubj();
	Optional <Subj> findBySubjName(String subjName);
	
	
}
