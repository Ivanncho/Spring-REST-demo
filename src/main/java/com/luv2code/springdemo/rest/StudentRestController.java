package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@Controller
@RequestMapping("/api")
public class StudentRestController {

	@GetMapping("/students")
	public List<Student> getStudents() {
		
		List<Student> theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Michael","Bucks"));
		theStudents.add(new Student("John", "Doe"));
		theStudents.add(new Student("Mario", "Rosi"));
		
		return theStudents;		
	}
	
}
