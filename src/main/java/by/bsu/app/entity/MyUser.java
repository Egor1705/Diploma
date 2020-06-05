package by.bsu.app.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class MyUser {

	
	 @Id
	    @Column(name = "user_id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String username;
	    private String password;
	    private String role;
	    private String course;
	    
	    
	    
	    
		public MyUser() {
			
		}
		
		public MyUser(String username, String password, String role,String course) {
			this.username = username;
			this.password = password;
			this.role = role;
			this.course = course;
		
		}
		
		 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
		    private List<DBFileStudent> studfiles;
		 
		 @ManyToMany
		    @JoinTable (name="user_subject",
		       joinColumns=@JoinColumn(name="user_id"),
		       inverseJoinColumns=@JoinColumn(name="subj_id"))
		    private List<Subj> subjects;
		 
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public List<DBFileStudent> getStudfiles() {
			return studfiles;
		}

		public void setStudfiles(List<DBFileStudent> studfiles) {
			this.studfiles = studfiles;
		}

		public List<Subj> getSubjects() {
			return subjects;
		}

		public void setSubjects(List<Subj> subjects) {
			this.subjects = subjects;
		}

		

	
	    
	    
	    
	    
	
}
