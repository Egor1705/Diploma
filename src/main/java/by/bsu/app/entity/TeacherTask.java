package by.bsu.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Lob;

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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tasks")
public class TeacherTask  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_task")
	private Long id;

	@Column(name = "fileName")
	private String fileName;
	
	
	@Column(name = "fileType")
	private String fileType;
	
	@Lob
	@Column(name="data",length = 100000000)
	private byte[] data;
	
	
	@Column(name = "deadL")
	private Date deadL;
	
	@Column(name = "downloadURL")
	private String downloadURL;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subj_id")
	private Subject subject;
	
	
	public TeacherTask() {

	}

	public TeacherTask(String fileName, String fileType,byte[] data,Date deadL,String downloadURL) {
	
		this.fileName = fileName;
		this.fileType = fileType;
	    this.data = data;
		this.deadL=deadL;
	    this.downloadURL = downloadURL;

	   
	}
	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public Date getDeadL() {
		return deadL;
	}

	public void setDeadL(Date deadL) {
		this.deadL = deadL;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}



	

	

	
	

}
