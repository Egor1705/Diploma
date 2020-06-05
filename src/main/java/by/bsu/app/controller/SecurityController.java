package by.bsu.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/index")
    public String index() {
        return "/index";
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
