package com.mert.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="course")
public class Course implements Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private int points;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	private boolean finished;
	
	@OneToMany(mappedBy = "course")
	private Set<UserCourse> userCourse = new HashSet<UserCourse>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}



	public Set<UserCourse> getUserCourse() {
		return userCourse;
	}

	public void setUserCourse(Set<UserCourse> userTask) {
		this.userCourse = userTask;
	}
	
}
