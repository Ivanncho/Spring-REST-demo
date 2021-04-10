package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@Controller
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	@PostConstruct
	public void loadData () {

		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Michael","Bucks"));
		theStudents.add(new Student("John", "Doe"));
		theStudents.add(new Student("Mario", "Rosi"));
		
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return theStudents;		
	}
	
	//define endpoing for /students/{studentId}
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if( (studentId>= theStudents.size()) || (studentId <0) ) {
			throw new StudentNotFoundException("Student id-" + studentId + " not found!" );
		}
		
		return theStudents.get(studentId);
	}
	
	//add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e){
		
		//create a StudentErrorResponse 
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		//return ResponseEntity
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception e){
		
				StudentErrorResponse error = new StudentErrorResponse();
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage(e.getMessage());
				error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
