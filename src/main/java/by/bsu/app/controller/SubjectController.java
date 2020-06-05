package by.bsu.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.app.entity.DBFileStudent;
import by.bsu.app.entity.MyUser;
import by.bsu.app.entity.Subj;
import by.bsu.app.exep.MyFileNotFoundException;
import by.bsu.app.exep.MySubjNotFoundException;
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
	
	@GetMapping
	public String list(Map<String,Object> model) {
		Iterable<Subj> subjects = subjectRepository.findAll();
		Iterable<DBFileStudent> files = dbFileStudRepo.findAll();
		model.put("subjects",subjects);
		model.put("studFiles",files);
		return "index";
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
	
	@PostMapping("/subject")
	public String subj(Map<String,Object> model,@RequestParam("subjName") String subjName) {
		//Iterable<Subj> subjects = subjectRepository.findById(id);
		Subj subject = getSubjByName(subjName);
		
		model.put("subjects",subject);
		
		return "subjectPage";
	}
	
	
	@RequestMapping("/del/{id}")
	public String subjDel(@PathVariable("id") Long id) {
		//Iterable<Subj> subjects = subjectRepository.findById(id);
		
		subjectRepository.deleteById(id);
		
		
		return "index";
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
