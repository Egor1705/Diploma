package by.bsu.app.entity;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studfiles")
public class DBFileStudent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fileName")
	private String fileName;

	@Column(name = "fileType")
	private String fileType;

	@Lob
	private byte[] data;

	@Column(name = "downloadURL")
	private String downloadURL;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_task")
	private DBFile dbFile;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private MyUser user;

	public DBFileStudent() {

	}

	public DBFileStudent(String fileName, String fileType, byte[] data, String downloadURL, MyUser user,
			DBFile dbFile) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.downloadURL = downloadURL;
		this.user = user;
		this.dbFile = dbFile;
	}

	// getter setter

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

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public DBFile getDbFile() {
		return dbFile;
	}

	public void setDbFile(DBFile dbFile) {
		this.dbFile = dbFile;
	}

	// public Subj getSubj() {
	// return subject;
	// }
	//
	//
	//
	//
	// public void setSubj(Subj subj) {
	// this.subject = subj;
	// }
	//

}
