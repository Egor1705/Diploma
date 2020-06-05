package by.bsu.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import javax.persistence.SecondaryTable;
import javax.persistence.Table;



@Entity
@Table(name = "subjects")

public class Subj implements Serializable {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "subj_id")
	    private Long id;
	 
	 @Column(name = "subjName")
	 private String subjName;
	 
//	 @Column(name = "prefix")
//	 private String prefix;
	 
	 
	 
	 @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
	    private List<DBFile> files;
	 
//	 @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
//	 private List<DBFileStudent> dbFileStudents;
	 
	 @ManyToMany
	    @JoinTable(name="user_subject",
	       joinColumns=@JoinColumn(name="subj_id"), 
	       inverseJoinColumns=@JoinColumn(name="user_id"))
	    private List<MyUser> users;
	 

	public Subj() {
	
	}

	public Subj(String subjName) {
		this.subjName = subjName;
	//	this.prefix = prefix;
		//this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjName() {
		return subjName;
	}

	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}

//	public String getPrefix() {
//		return prefix;
//	}
//
//	public void setPrefix(String prefix) {
//		this.prefix = prefix;
//	}

	public List<DBFile> getFiles() {
		return files;
	}

	public void setFiles(List<DBFile> files) {
		this.files = files;
	}

//	public String getPrefix() {
//		return prefix;
//	}
//
//	public void setPrefix(String prefix) {
//		this.prefix = prefix;
//	}

	public List<MyUser> getUsers() {
		return users;
	}

	public void setUsers(List<MyUser> users) {
		this.users = users;
	}

	
	
	

//	public List<DBFileStudent> getDbFileStudents() {
//		return dbFileStudents;
//	}
//
//	public void setDbFileStudents(List<DBFileStudent> dbFileStudents) {
//		this.dbFileStudents = dbFileStudents;
//	}

	
}
