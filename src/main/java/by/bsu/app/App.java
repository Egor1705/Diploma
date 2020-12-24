package by.bsu.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootApplication
public class App {

	
	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(App.class, args);
   
            		
                     
	}
}
