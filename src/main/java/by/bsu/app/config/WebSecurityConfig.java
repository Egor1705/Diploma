package by.bsu.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import by.bsu.app.entity.MyUser;



@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

//	@Autowired
//	DataSource dataSource;

//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select username,password, role from users where username=?");
//	}

//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT PASSWORD,ROLE,USERNAME FROM USERS WHERE USERNAME=?");
//    }
	
	

	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/start").permitAll()
        .antMatchers("/admin/**").hasAnyRole("ADMIN")
        .antMatchers("/user/**").hasAnyRole("USER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 
        auth.inMemoryAuthentication()
                .withUser("Kirill").password(passwordEncoder().encode("k")).roles("USER")
                .and()
                .withUser("Maxim").password(passwordEncoder().encode("m")).roles("USER")
                .and()
                .withUser("Danil").password(passwordEncoder().encode("d")).roles("USER")
                .and()
                .withUser("Artem").password(passwordEncoder().encode("a")).roles("ADMIN")
                .and()
                .withUser("Vitaliy").password(passwordEncoder().encode("v")).roles("USER")
                .and()
                .withUser("Masha").password(passwordEncoder().encode("m")).roles("USER")
                .and()
                .withUser("Tanya").password(passwordEncoder().encode("t")).roles("USER");
                
                
                
//                MyUser Kate = new MyUser("Kate",passwordEncoder.encode("k"),"USER","3 course 5 group");
//		 MyUser VITALIY = new MyUser("Vitaliy",passwordEncoder.encode("v"),"USER","3 course 5 group");
//		 MyUser Masha = new MyUser("Masha",passwordEncoder.encode("m"),"USER","3 course 5 group");
//		 MyUser Tanya = new MyUser("Tanya",passwordEncoder.encode("t"),"USER","2 course 5 group");
    }
	
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
