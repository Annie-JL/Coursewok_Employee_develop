package com.mert.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="user_course")
public class UserCourse implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "COURSE_ID", referencedColumnName = "id")
    private Course course;

	public UserCourse(User user, Course course) {
		this.user = user;
		this.course = course;
	}

	public UserCourse() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


    
}
