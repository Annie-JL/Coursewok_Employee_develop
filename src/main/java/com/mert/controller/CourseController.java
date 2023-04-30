package com.mert.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import com.mert.model.User;
import com.mert.repository.UserCourseRepository;
import com.mert.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.service.CourseService;
import com.mert.service.UserCourseService;
import com.mert.model.Course;

@Controller
@RequestMapping("/courses")
public class CourseController {


	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserCourseService userCourseService;

	@Autowired
	private UserCourseRepository userCourseRepository;

	@Autowired
	private UserService userService;

	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newTask(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("course", new Course());
		modelAndView.addObject("courses", courseService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("course");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveTask(@Valid Course course, BindingResult bindingResult) {
		course.setDateCreated(new Date());
		courseService.save(course);
		ModelAndView modelAndView = new ModelAndView("redirect:/courses/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		return modelAndView;
	}

	@ModelAttribute("courses")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allTasks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Course());
		List<Course> courses = courseService.findAll();
		modelAndView.addObject("courses", courses);
		modelAndView.addObject("userCourseRepository", userCourseRepository);
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("course");
		return modelAndView;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Course());
		modelAndView.addObject("course", courseService.findCourse(id));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control",getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("course");
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/courses/all");
		modelAndView.addObject("rule", new Course());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		courseService.delete(id);
		return modelAndView;
	}

	@RequestMapping(value = "/per_inf", method = RequestMethod.GET)
	public ModelAndView infTask(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Course());
		modelAndView.addObject("course", courseService.findCourse(id));
		modelAndView.addObject("usercourses", userCourseService.findByCourse(courseService.findCourse(id)));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_INF");
		modelAndView.setViewName("course");
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}
}
