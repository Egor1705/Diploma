package by.bsu.app.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.DBFileStudent;
import by.bsu.app.entity.Subj;
import by.bsu.app.entity.MyUser;
import by.bsu.app.exep.MyFileNotFoundException;
import by.bsu.app.exep.MySubjNotFoundException;
import by.bsu.app.exep.MyUserNotFoundException;
import by.bsu.app.repo.DBFileRepository;
import by.bsu.app.repo.DBFileStudRepo;
import by.bsu.app.repo.SubjectRepository;
import by.bsu.app.repo.UserRepository;

@Controller
public class DBFileStudentController {

	@Autowired
	private DBFileStudRepo dbFileStudRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DBFileRepository dbFileRepo;
	@Autowired
	private SubjectRepository subjRepo;

	@GetMapping("/s_f")
	public String list(Model model) {
		Iterable<DBFileStudent> list = dbFileStudRepo.findAll();
		model.addAttribute("studfiles", list);
		return "index";
	}

	@GetMapping("/s_u")
	public String listUsers(Model model) {
		// EntityManager em = entityManagerFactory.createEntityManager();
		// Query query = em.createQuery("SELECT DBFileStudent.ID, User.username,
		// User.course,DBFileStudent.downloadurl\r\n" +
		// "FROM DBFileStudent\r\n" +
		// "INNER JOIN User ON DBFileStudent.ID=User.user_id");
		List<DBFileStudent> resultList = dbFileStudRepo.findStudFiles();
		model.addAttribute("res", resultList);
		return "subjectPage";
	}

	@GetMapping("/s_u_2Course")
	public ModelAndView listUsersBy2Course(Map<String,Object> model) {
		List<DBFileStudent> resultList = dbFileStudRepo.findStudFilesBy2Course();
		//model.addAttribute("res2", resultList);
	model.put("res2", resultList);
		model.put("dateOfComp",dateOfComp());
		return new ModelAndView("subjectPage",model);
	}
	
	

	@GetMapping("/s_u_3Course")
	public String listUsersBy3Course(Model model) {
		List<DBFileStudent> resultList = dbFileStudRepo.findStudFilesBy3Course();
		model.addAttribute("res3", resultList);
		return "subjectPage";
	}

	// @PostMapping("filter_course")
	// public String filter(@RequestParam String filter, Map<String, Object> model)
	// {
	// Iterable<DBFileStudent> files;
	// DBFileStudent file;
	// if(!filter.isEmpty() && filter!=null){
	// file=dbFileStudRepo.findTasks(filter);
	// }
	// else {
	// files=dbFileStudRepo.findAll();
	// }
	// model.put("files", dbFileStudRepo.findAll());
	//
	// return "subjectPage";
	//
	// }

	@GetMapping("/uploadStud/{id}/{id_task}")
	public String showUpdateForm(@PathVariable("id") long id,@PathVariable("id_task") long id_task, Model model) {
	    Subj sub = subjRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        DBFile dbfile = dbFileRepo.findById(id_task)
        		.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id_task));
	    model.addAttribute("subject", sub);
	    model.addAttribute("dbFile",dbfile);
	    return "upload-par";
	}
	
	
	@PostMapping("/uploadStudFile/{id}/{id_task}")
	public String uploadFile(@RequestParam("file") MultipartFile file, MyUser user,@PathVariable("id") long id,
			@RequestParam("id_task") Long id_task, @Valid DBFile dbFile,@Valid Subj subject) throws Exception {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String downloadURL = "http://localhost:8080/downloadStudFile/" + fileName;
		String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		user = userRepo.findByUsername(userName);
        subject = getSubj(id);
		dbFile = getDBFile(id_task);
		
		DBFileStudent dbFileSt = new DBFileStudent(fileName, file.getContentType(), file.getBytes(), downloadURL, user,
				dbFile);

		dbFileStudRepo.save(dbFileSt);

		if (new Date().compareTo(dbFile.getDeadL()) > 0) {
			dbFileStudRepo.delete(dbFileSt);
			return "index";
		}
		return "subjectPage";

	}
	
	public Date dateOfComp() {
		return new Date();
	}
	
	
	

	@GetMapping("/downloadStudFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		// Load file from database
		DBFileStudent dbFileStud = getFileByName(fileName);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFileStud.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFileStud.getFileName() + "\"")
				.body(new ByteArrayResource(dbFileStud.getData()));
	}

	public DBFileStudent getFileByName(String fileName) {
		return dbFileStudRepo.findByfileName(fileName)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with fileName " + fileName));
	}

	public MyUser getUser(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new MyUserNotFoundException("File not found with fileName "));
	}

	public DBFile getDBFile(Long id) {
		return dbFileRepo.findById(id).orElseThrow(() -> new MyFileNotFoundException("File not found with fileName "));
	}

	public Subj getSubj(Long id) {
		return subjRepo.findById(id).orElseThrow(() -> new MySubjNotFoundException("File not found with fileName "));
	}

	// public DBFile getDBFileByDate(Date deadLine) {
	// return dbFileRepo.findByDeadLine(deadLine)
	// .orElseThrow(() -> new MyFileNotFoundException("File not found with fileName
	// " ));
	// }

}
