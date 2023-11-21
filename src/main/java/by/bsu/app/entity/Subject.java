package by.bsu.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subjects")

public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subj_id")
	private Long id;

	@Column(name = "subjName")
	private String subjName;


	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<TeacherTask> tasks;


	@ManyToMany
	@JoinTable(name = "subject_student", joinColumns = @JoinColumn(name = "subj_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;
	
	
	@ManyToMany
	@JoinTable(name = "course_subject", joinColumns = @JoinColumn(name = "subj_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> courses;

	public Subject() {

	}

	public Subject(String subjName) {
		this.subjName = subjName;
		// this.prefix = prefix;
		// this.users = users;
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

	public List<TeacherTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<TeacherTask> tasks) {
		this.tasks = tasks;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}



}
