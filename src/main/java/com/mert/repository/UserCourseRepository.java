package com.mert.repository;


import com.mert.model.Course;
import com.mert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mert.model.UserCourse;

import java.util.List;

@Repository("userCourseRepository")
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
    List<UserCourse> findByCourse(Course course);
    List<UserCourse> findByUser (User user);

    public boolean existsByUserAndCourse(User user, Course course);



}
