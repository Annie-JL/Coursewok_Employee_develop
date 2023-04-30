package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.mert.model.Course;
import com.mert.model.User;
import com.mert.repository.CourseRepository;
import com.mert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.repository.UserCourseRepository;
import com.mert.model.UserCourse;

@Service
@Transactional
public class UserCourseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	private final UserCourseRepository userCourseRepository;

	public UserCourseService(UserCourseRepository userCourseRepository) {
		this.userCourseRepository = userCourseRepository;
	}
	
	public List<UserCourse> findAll(){
		List<UserCourse> user_courses = new ArrayList<>();
		user_courses = userCourseRepository.findAll();
		return user_courses;
	}
	
	public UserCourse findUserCourse(int id){
		return userCourseRepository.findOne(id);
	}
	
	public void save(UserCourse user_course){
		userCourseRepository.save(user_course);

	}
	
	public void delete(int id){
		userCourseRepository.delete(id);

	}



	public void complete(User user, Course course) {
		int userPoints = user.getPoints();
		int coursePoints = course.getPoints();
		user.setPoints(userPoints + coursePoints);
		userRepository.save(user);
		UserCourse userCourse = new UserCourse(user, course);
		userCourseRepository.save(userCourse);
	}



	public boolean existsByUserAndCourse(User user, Course course) { return  userCourseRepository.existsByUserAndCourse(user, course);}

	public List<UserCourse> findByCourse(Course course) {
		return userCourseRepository.findByCourse(course);
	}
	public List<UserCourse> findByUser(User user) {
		return userCourseRepository.findByUser(user);
	}
}
