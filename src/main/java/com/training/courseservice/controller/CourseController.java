package com.training.courseservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.courseservice.Repository.CourseRepository;
import com.training.courseservice.intercomm.StudentClient;
import com.training.courseservice.model.Course;
import com.training.courseservice.model.Student;

@RestController
@RequestMapping("/courseapi")
public class CourseController {
	@Autowired
	CourseRepository repository;
	
	@Autowired
	StudentClient studClient;
	
	
	@GetMapping("/course")
	public ResponseEntity<List<Course>> getCourse() {
		List<Course> courseList=repository.findAll();
		return new ResponseEntity<>(courseList,HttpStatus.OK);
		
	}
	@PostMapping("/post")
	public void post(@RequestBody Course course) {
		repository.save(course);
	}
	@GetMapping("/course/{id}")
	public Course getCourseById(@PathVariable("id") long id) {
		Optional<Course> course=repository.findById(id);
		if(course.isPresent()) {
			return course.get();
		}
		return null;
	}
	
	@PutMapping("/course/{id}")
	public void updateUser(@PathVariable("id") long id, @RequestBody Course course) {
		Optional<Course> oldData=repository.findById(id);
		if(oldData.isPresent()) {
			Course crs=oldData.get();
			crs.setCourseName(course.getCourseName());
			crs.setDuration(course.getDuration());
			crs.setFees(course.getFees());
			repository.save(crs);
		}
		else {
			System.out.println("No data found");
		}
	}
	@DeleteMapping("/course/{id}")
	public void deleteId(@PathVariable("id") long id) {
		Optional<Course> oldData=repository.findById(id);
		if(oldData.isPresent()) {
			repository.deleteById(id);
		}
		else {
			System.out.println("ID not found...");
		}
	}
	
	@GetMapping("service/stud/{studid}")
	public Student getStud(@PathVariable("studid") int studId) {
		return studClient.getStudent(studId);
	}
	
	
	
}
