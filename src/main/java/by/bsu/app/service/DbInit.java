package by.bsu.app.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import by.bsu.app.entity.MyUser;
import by.bsu.app.repo.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;


	public DbInit(UserRepository userRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	
		
	}

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.deleteAll();
		
		// TODO Auto-generated method stub

		 MyUser kirill = new MyUser("Kirill",passwordEncoder.encode("k"),"USER","2 course 5 group");
		 MyUser maxim = new MyUser("Maxim",passwordEncoder.encode("m"),"USER","2 course 5 group");
		 MyUser danil = new MyUser("Danil",passwordEncoder.encode("d"),"USER","3 course 5 group");
		 MyUser artem = new MyUser("Artem",passwordEncoder.encode("a"),"ADMIN","3 course 5 group");
		 MyUser Kate = new MyUser("Kate",passwordEncoder.encode("k"),"USER","3 course 5 group");
		 MyUser VITALIY = new MyUser("Vitaliy",passwordEncoder.encode("v"),"USER","3 course 5 group");
		 MyUser Masha = new MyUser("Masha",passwordEncoder.encode("m"),"USER","3 course 5 group");
		 MyUser Tanya = new MyUser("Tanya",passwordEncoder.encode("t"),"USER","2 course 5 group");
	        

	        List<MyUser> users = Arrays.asList(kirill,maxim,danil,artem,Kate,VITALIY,Masha,Tanya);

	        // Save to db
	       this.userRepository.saveAll(users);
		
	}

}
