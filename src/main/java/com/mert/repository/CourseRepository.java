package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.Course;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
