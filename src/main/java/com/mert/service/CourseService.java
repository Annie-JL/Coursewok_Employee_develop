package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.mert.model.Course;
import org.springframework.stereotype.Service;

import com.mert.repository.CourseRepository;

@Service
@Transactional
public class CourseService {

	private final CourseRepository courseRepository;
	

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll(){
		List<Course> courses = new ArrayList<>();
		courses = courseRepository.findAll();
		return courses;
	}
	
	public Course findCourse(int id){
		return courseRepository.findOne(id);
	}
	
	public void save(Course course){
		courseRepository.save(course);
	}
	
	public void delete(int id){
		courseRepository.delete(id);

	}
}
