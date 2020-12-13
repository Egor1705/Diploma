package by.bsu.app.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.Subj;
import by.bsu.app.exep.MyFileNotFoundException;
import by.bsu.app.exep.MySubjNotFoundException;
import by.bsu.app.repo.DBFileRepository;
import by.bsu.app.repo.SubjectRepository;

@Controller
public class DBFileController {

	@Autowired
	private DBFileRepository dbFileRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	
	

	@GetMapping("/f")
	public String list(Model model) { //id_task' cannot be found on object of type 'by.bsu.app.entity.DBFile
		Iterable<DBFile> list = dbFileRepo.findAll();
		
		model.addAttribute("files", list);
		//Subj sub = subjRepo.findById(id)
		return "subjectPage";
	}

//	@GetMapping("/flist")
//	public String flist(Map<String, Object> model) {
//		Iterable<DBFile> list = dbFileRepo.findAll();
//		model.put("files", list);
//		Iterable<Subj> subjects = subjectRepo.findAll();
//		model.put("subjects", subjects);
//		return "subjectPage";
//	}

	// @GetMapping("/files/{subject_id}/subjects")
	// public String getDBFilesBySubject(@PathVariable(value = "postId") Long
	// subject_id, Model model) {
	// List<DBFile> list = dbFileRepo.findBySubjectId(subject_id);
	// model.addAttribute("files", list);
	// return "index";
	// }

	// @PostMapping("/instructors/{instructorId}/courses")
	// public Optional<DBFile> createCourse(@PathVariable(value = "instructorId")
	// Long instructorId,
	// @Valid @RequestBody DBFile dbFile) {
	// return subjectRepo.findById(instructorId).map(subject -> {
	// dbFile.setSubject(subject);
	// return dbFileRepo.save(dbFile);
	// });
	// }
	@GetMapping("/upload/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Subj sub = subjectRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

	    model.addAttribute("subject", sub);
	    return "upload-par";
	}
	    
	@PostMapping("/uploadFile/{id}")
	public String uploadFile(@RequestParam(value = "deadL") Date deadL, Model model,
			//@RequestParam("subj_id") Long id, 
			@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file, @Valid Subj subject,BindingResult result) throws Exception {
		  subject = getSubj(id);
		// model.addAttribute("subject", subject);
		 if (result.hasErrors()) {
		        subject.setId(id);
		        return "upload-par";
		    }
		
		//Iterable<Subj> subjects = subjectRepo.findAll();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);

		//subject = getSubj(id);
		//subjectRepo.save(subject);
		
		// subjectRepo.save(subject);
		
  System.out.println("id = "+id);
		String downloadURL = "http://localhost:8080/downloadFile/" + fileName;
		 //model.addAttribute("subjects", subjects);
		DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), deadL, downloadURL, subject);

		dbFileRepo.save(dbFile);
		model.addAttribute("files",dbFileRepo.findAll());
		System.out.println(deadL);
		// model.addAttribute("subjects",subjectRepo.findAll());
		return "subjectPage";

	}

	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		// Load file from database
		DBFile dbFile = getFileByName(fileName);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	public DBFile getFile(Long fileId) {
		return dbFileRepo.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

	public DBFile getFileByName(String fileName) {
		return dbFileRepo.findByfileName(fileName)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with fileName " + fileName));
	}

	// public DBFile getFileBySubj(Long id) {
	// return dbFileRepo.findBySubjectId(id)
	// .orElseThrow(() -> new MyFileNotFoundException("File not found with fileName
	// "));
	// }
	
	public Subj getSubj(Long id) {
		return subjectRepo.findById(id).orElseThrow(() -> new MySubjNotFoundException("subject not found with id " +id));
	}

}
