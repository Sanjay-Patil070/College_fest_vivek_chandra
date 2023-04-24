package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.entity.Student;
import com.gl.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String showStudentList(Model model) {
		System.out.println("list page of controller");
		List<Student> theStudent = studentService.findAll();
		model.addAttribute("students", theStudent);
		
		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String ShowFormForAdd(Model model) {
		Student theStudent = new Student();
		model.addAttribute("student", theStudent);
		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student theStudent = studentService.findById(id);
		model.addAttribute("student", theStudent);

		return "student-form";

	}

	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		studentService.save(theStudent);
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/students/list";
	}

}
