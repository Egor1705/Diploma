package by.bsu.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.DBFileStudent;
import by.bsu.app.entity.MyUser;
import by.bsu.app.entity.Subj;
import by.bsu.app.exep.MyFileNotFoundException;
import by.bsu.app.exep.MySubjNotFoundException;
import by.bsu.app.repo.DBFileRepository;
import by.bsu.app.repo.DBFileStudRepo;
import by.bsu.app.repo.SubjectRepository;
import by.bsu.app.repo.UserRepository;

@Controller
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private DBFileStudRepo dbFileStudRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DBFileRepository dbFileRepo;
	
	@GetMapping
	public ModelAndView list(Map<String,Object> model) {
		Iterable<Subj> subjects = subjectRepository.findAll();
	//	Iterable<DBFileStudent> files = dbFileStudRepo.findAll();
		List<DBFile>list = (List<DBFile>) dbFileRepo.findAll();
		List<DBFileStudent>listTasks = (List<DBFileStudent>) dbFileStudRepo.findAll();
	//	System.out.println(list.size());
		model.put("subjects",subjects);
		//model.put("tasks",list);
		//model.put("studFiles",files);
		System.out.println(list);
		if (list.size()!=0) {
		model.put("dateSoon", dateSoon());
		model.put("countLabs", countLabs());
		//model.put("countLabsPerSubj",countLabsPerSubj());
		}
		if(listTasks.size()!=0) {
         model.put("countTasks", countTasks());
		}
		
		
		
		return new ModelAndView("index",model);
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Subj sub = subjectRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

	    model.addAttribute("subject", sub);
	    return "update-sub";
	}
	
	
	
	@PostMapping("/update/{id}")
	public String showEditProductPage(@PathVariable(name = "id") long id,@Valid Subj subject,
			BindingResult result, Model model) {
		
		 if (result.hasErrors()) {
		        subject.setId(id);
		        return "update-sub";
		    }
		
	  //  ModelAndView mav = new ModelAndView("edit");
	    //Subj s = subjectRepository.findById(id).get();
	    subjectRepository.save(subject);
	   // mav.addObject("subjects", subject);
	    model.addAttribute("subjects",subjectRepository.findAll()); 
	    return "index";
	}
	
	
//	@PostMapping("/save")
//	public String saveProduct(@ModelAttribute("s") Subj s) {
//	    subjectRepository.save(s);
//	     
//	    return "redirect:/";
//	}
	
	
	public long countLabs() {
		return dbFileRepo.countFiles();
	}
	
	public List<Long> countLabsPerSubj() {
		return dbFileRepo.countFilesPerSubj();
	}
	
	public long countTasks() {
		return dbFileStudRepo.count();
	}
	
	public Date dateSoon() {
		Iterable<DBFile> list  = dbFileRepo.findAll();
		List<Date> listDate = new ArrayList<>();
		for(DBFile dbFile: list) {
		listDate.add(dbFile.getDeadL());	
		}
		Collections.sort(listDate);
	System.out.println(listDate.get(0));
	//System.out.println(listDate.get(1));
	return listDate.get(0);	
	}
	
	
	
	//long sum = dbFileStudRepo.count();
			//model.put("studFiles",files);
	
//	@GetMapping("/listSub")
//	public String listSub(Map<String,Object> model) {
//		Iterable<Subj> subjects = subjectRepository.findAll();
//		Iterable<DBFileStudent> files = dbFileStudRepo.findAll();
//		model.put("subjects",subjects);
////		long sum = dbFileStudRepo.count();
//		model.put("studFiles",files);
//		return "index";
//	}
	
//	@PostMapping("/subject")
//	public String subj(Map<String,Object> model,@RequestParam("subjName") String subjName) {
//		//Iterable<Subj> subjects = subjectRepository.findById(id);
//		Subj subject = getSubjByName(subjName);
//		
//		model.put("subjects",subject);
//		
//		return "subjectPage";
//	}
	
	
	@RequestMapping("/del/{id}")
	public String subjDel(@PathVariable("id") Long id,Model model) {
		//Iterable<Subj> subjects = subjectRepository.findById(id);
		
		subjectRepository.deleteById(id);
		model.addAttribute("subjects", subjectRepository.findAll());
		
		return "index";
	}
	
	@RequestMapping("/jump/{id}")
	public String subjJump(Model model,@PathVariable("id") Long id) {
		//Iterable<Subj> subjects = subjectRepository.findById(id);
		
Subj subject = getSubj(id);
		
		//model.addAttribute("page_title",subject.getSubjName());
model.addAttribute("subject",subject);
model.addAttribute("files",dbFileRepo.findAll());
		System.out.println("the number of id " + id);
		return "subjectPage";
	}
	
	//WARNING!!!
//	@RequestMapping("/jump/{id}/{task_id}")
//	public String subjJumpStud(Model model,@PathVariable("id") Long id,@PathVariable("task_id") Long task_id) {
//		//Iterable<Subj> subjects = subjectRepository.findById(id);
//		
//Subj subject = getSubj(id);
//		DBFile dbFile = getFile(task_id);
//		//model.addAttribute("page_title",subject.getSubjName());
//model.addAttribute("subject",subject);
//model.addAttribute("file",dbFile);
//model.addAttribute("files",dbFileRepo.findAll());
//		System.out.println("the number of id " + id);
//		return "subjectPage";
//	}
	
	public DBFile getFile(Long fileId) {
		return dbFileRepo.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
	
	public Subj getSubjByName(String subjName) {
		return subjectRepository.findBySubjName(subjName)
				.orElseThrow(() -> new MySubjNotFoundException("File not found with fileName " + subjName));
	}
	
	public Subj getSubj(Long id) {
		return subjectRepository.findById(id)
				.orElseThrow(() -> new MySubjNotFoundException("File not found with fileName "));
	}
	
	
	@PostMapping("/s_add")
	public String add(@RequestParam String subjName,Map<String,Object> model) {
		Subj subject = new Subj(subjName);
		subjectRepository.save(subject);
		Iterable<Subj> subjects = subjectRepository.findAll();
		model.put("subjects", subjects);
		return "redirect:/";
	}
	
//	@PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//    	Iterable<Subj>subjects;
//    	Subj subject;
//    	if(!filter.isEmpty() && filter!=null){
//    		subject=subjectRepository.findByPrefix(filter);
//    	}
//    	else {
//    		subjects=subjectRepository.findAll();
//    	}
//    	model.put("subjects", subjectRepository.findAll());
//
//        return "index";
//    	
//    }
	
//	@PostMapping("filter_id")
//    public String filterID(@RequestParam Long id, Map<String, Object> model) {
//    	Iterable<Subj>subjects;
//    	Subj subject;
//    	if(id!=0){
//    		subject=subjectRepository.findByid(id);
//    	}
//    	else {
//    		subjects=subjectRepository.findAll();
//    	}
//    	model.put("subjects", subjectRepository.findAll());
//
//        return "index";
//    	
//    }

	
}
