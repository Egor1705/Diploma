package by.bsu.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import by.bsu.app.entity.Subject;
import by.bsu.app.exep.MyFileNotFoundException;
import by.bsu.app.exep.MySubjNotFoundException;

import by.bsu.app.service.SubjectService;
import by.bsu.app.service.TeacherTaskService;


@Controller
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private TeacherTaskService teacherTaskService;
	

	 @GetMapping("signup")
	    public String showSignUpForm(Subject subject) {
	        return "add-subject";
	    }

	 
	 @GetMapping("/add-subject")
	    public String shownUpForm(Subject subject) {
	        return "add-subject";
	    }
	
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Subject sub = subjectService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

	    model.addAttribute("subject", sub);
	    return "update-sub";
	}
	
	
	
	@PostMapping("/update/{id}")
	public String showEditProductPage(@PathVariable(name = "id") long id,Subject subject,
			BindingResult result, Model model) {
		
		 if (result.hasErrors()) {
		        subject.setId(id);
		        return "update-sub";
		    }
		
	  //  ModelAndView mav = new ModelAndView("edit");
	    //Subj s = subjectRepository.findById(id).get();
	    subjectService.save(subject);
	   // mav.addObject("subjects", subject);
	    model.addAttribute("subjects",subjectService.findAll()); 
	    return "index";
	}
	

	 @GetMapping("/subjects")
	    public String showUpdateForm(Model model) {
	        model.addAttribute("subjects", subjectService.findAll());
	        return "index";
	    }
	 @GetMapping("/deleteSubj/{id}")
	 public String deleteSubject(@PathVariable("id") long id,Model model) {
		 
		 subjectService.deleteById(id);
	 model.addAttribute("subjects", subjectService.findAll());
		 return "index";
	 }

	 @GetMapping("/subjectPage/{id}")
	 public String getSubjPage(@PathVariable("id") long id,Model model) {
	//Subject s = getSubj(id);
		 model.addAttribute("subject",subjectService.findById(id).orElseThrow());
		 model.addAttribute("files",teacherTaskService.findAll());
		 return "subjectPage";
	 }
	 
	 @GetMapping("/index")
	 public String getubjPage(Model model) {
	//Subject s = getSubj(id);
		 model.addAttribute("subjects",subjectService.findAll());
		 return "redirect:subjects";
	 }
	 
	
	@PostMapping("/add-subject")
    public String addStudent(Subject subj, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        subjectService.save(subj);
        return "redirect:subjects";
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

	public Subject getSubj(Long id) {
		return subjectService.findById(id).orElseThrow();
				//.orElseThrow(() -> new MySubjNotFoundException("File not found with fileName "));
	}
	
}
