package by.bsu.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.app.entity.DBFile;
import by.bsu.app.entity.DBFileStudent;
import by.bsu.app.entity.Subj;
import by.bsu.app.repo.DBFileRepository;
import by.bsu.app.repo.DBFileStudRepo;
import by.bsu.app.repo.SubjectRepository;
import by.bsu.app.repo.UserRepository;

@Controller
public class SecurityController {

	
	@Autowired
	private SubjectRepository subjectRepository;
	
	
	@Autowired
	private DBFileRepository dbFileRepo;
	
	@Autowired
	private DBFileStudRepo dbFileStudRepo;
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
//	@GetMapping("/index")
//    public ModelAndView index() {
//		
//		
//	}
		
		@GetMapping("/index")
		public ModelAndView list(Map<String,Object> model) {
			Iterable<Subj> subjects = subjectRepository.findAll();
		//	Iterable<DBFileStudent> files = dbFileStudRepo.findAll();
			List<DBFileStudent>listTasks = (List<DBFileStudent>) dbFileStudRepo.findAll();
			List<DBFile>list = (List<DBFile>) dbFileRepo.findAll();
		//	System.out.println(list.size());
			model.put("subjects",subjects);
			//model.put("studFiles",files);
			System.out.println(list);
			if (list.size()!=0) {
			model.put("dateSoon", dateSoon());
			model.put("countLabs", countLabs());
			}
			if(listTasks.size()!=0) {
		         model.put("countTasks", countTasks());
				}
			
			return new ModelAndView("index",model);
    }
		
		
		public long countTasks() {
			return dbFileStudRepo.count();
		}
	
		public long countLabs() {
		
			return dbFileRepo.countFiles();
		}
		
		public Date dateSoon() {
			Iterable<DBFile> list  = dbFileRepo.findAll();
			List<Date> listDate = new ArrayList<>();
			for(DBFile dbFile: list) {
			listDate.add(dbFile.getDeadL());	
			}
			Collections.sort(listDate);
		System.out.println(listDate.get(0));
	//	System.out.println(listDate.get(1));
		return listDate.get(0);	
		}
		
		
	@GetMapping("/start")
    public String start() {
        return "/start";
    }
	
	
	
//	@GetMapping("/subject")
//    public String subject() {
//        return "/subject";
//    }
	
}
