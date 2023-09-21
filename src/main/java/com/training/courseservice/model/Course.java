package com.training.courseservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import com.training.studentservice.model.Student;

import lombok.Data;
import lombok.NoArgsConstructor;


	@Data
	@NoArgsConstructor
	//@AllArgsConstructor
	@Entity
	//@Table(name="STUD")
	public class Course {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long courseId;
		private String courseName;
		private int duration;
		private double fees;
		
		public Course(String courseName, int duration, double fees) {
			
			this.courseName = courseName;
			this.duration = duration;
			this.fees = fees;
		}
		
		

	}

