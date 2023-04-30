package com.mert.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.mert.model.Course;
import com.mert.model.User;
import com.mert.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mert.model.UserCourse;
import com.mert.service.CourseService;
import com.mert.service.UserService;
import com.mert.service.UserCourseService;

@Controller
@RequestMapping("/user-course")
public class UserCourseController {


	@Autowired
	private UserService userService;

	@Autowired
	private UserCourseService userCourseService;

	@Autowired
	private CourseService courseService;




	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUserCourse() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user_course", new UserCourse());
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("courses", courseService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("user_course");
		return modelAndView;
	}



	@PostMapping
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView newUserCourseAndSave(@RequestParam int courseId) {
		Course course = courseService.findCourse(courseId);
		User user = getUser();
		UserCourse userCourse = new UserCourse(user, course);
		userCourseService.save(userCourse);
		userCourseService.complete(user,course);
		ModelAndView modelAndView = new ModelAndView("redirect:/courses/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateUserCourse(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new UserCourse());
		modelAndView.addObject("user_course", userCourseService.findUserCourse(id));
		modelAndView.addObject("users", userService.findAll());
		modelAndView.addObject("courses", courseService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("user_course");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserCourse(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/user-course/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		userCourseService.delete(id);
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}

}
