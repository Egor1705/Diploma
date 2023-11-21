package by.bsu.app.controller;

import java.sql.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import by.bsu.app.entity.Subject;
import by.bsu.app.entity.TeacherTask;
import by.bsu.app.service.SubjectService;
import by.bsu.app.service.TeacherTaskService;

@Controller
public class TeacherTaskController {

	@Autowired
	private TeacherTaskService teacherTaskService;
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("/tasks")
	public String getAllTasks(Model model) {
		model.addAttribute("tasks",teacherTaskService.findAll());
		return "subjectPage";
	}
	
	@GetMapping("/upload/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
//	    Subject subject = subjectService.findById(id)
//	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

	    model.addAttribute("subject", subjectService.findById(id).orElseThrow());
	    return "upload-par";
	}
	    
	@PostMapping("/uploadFile/{id}")
	public String uploadFile(@RequestParam(value = "deadL") Date deadL, Model model, 
			@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file) throws Exception {
		 
		Subject subject = subjectService.findById(id).orElseThrow();
		teacherTaskService.addToSubject(id, subject.getSubjName());
		//Iterable<Subj> subjects = subjectRepo.findAll();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);

		
		String downloadURL = "http://localhost:8080/downloadFile/" + fileName;
		 //model.addAttribute("subjects", subjects);
		TeacherTask teacherTask = new TeacherTask(fileName, file.getContentType(),file.getBytes(), deadL, downloadURL);

		teacherTaskService.save(teacherTask);
		
		model.addAttribute("files",teacherTaskService.findAll());
	
		return "subjectPage";

	}

	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		// Load file from database
		TeacherTask teacherTask = teacherTaskService.findByfileName(fileName).orElseThrow();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(teacherTask.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + teacherTask.getFileName() + "\"")
				.body(new ByteArrayResource(teacherTask.getData()));
	}
	
}
