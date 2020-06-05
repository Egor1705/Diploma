package by.bsu.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class DBFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_task")
	private Long id;

	@Column(name = "fileName")
	private String fileName;
	
	@Column(name = "fileType")
	private String fileType;

	@Lob
	private byte[] data;
	
	@Column(name = "deadL")
	private Date deadL;
	
	@Column(name = "downloadURL")
	private String downloadURL;





	@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "subject_id")
		private Subj subject;
	
	@OneToMany(mappedBy = "dbFile",cascade = CascadeType.ALL)
    private List<DBFileStudent> dbFileStudents;
	
	
	public DBFile() {

	}

	public DBFile(String fileName, String fileType, byte[] data,Date deadL,String downloadURL,Subj subject) {
	
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.deadL=deadL;
	    this.downloadURL = downloadURL;
	    this.subject = subject;
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Date getDeadL() {
		return deadL;
	}

	public void setDeadL(Date deadL) {
		this.deadL = deadL;
	}

	public Subj getSubject() {
		return subject;
	}

	public void setSubject(Subj subject) {
		this.subject = subject;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public List<DBFileStudent> getDbFileStudents() {
		return dbFileStudents;
	}

	public void setDbFileStudents(List<DBFileStudent> dbFileStudents) {
		this.dbFileStudents = dbFileStudents;
	}

	

	

	
	

}
