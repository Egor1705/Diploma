package by.bsu.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.bsu.app.entity.Subj;
import by.bsu.app.entity.MyUser;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {
	
	MyUser findByUsername(String username);

	
	
	Optional <MyUser> findById(Long id);
	
//    @Query("SELECT u FROM User u LEFT JOIN u.studfiles s where u.id = s.id")
//	List<User> findUsers();
	Optional<MyUser> findByCourse(String course);
	
	
}
