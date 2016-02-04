package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.Student;


@Controller
@RequestMapping("student")
public class StudentController {

	private static List<Student> studentList=new ArrayList<>();
	
	static {
		for (int i = 0; i < 10; i++) {
			studentList.add(new Student(new Integer((i+1)).toString(), "name"+(i+1)));
		}
	}
	
	@RequestMapping(value="teststudent.spring",method=RequestMethod.GET)
	@ResponseBody
	public List<Student> testStudent()
	{
	
		System.out.println("In Student controller");
		return studentList;
	}
	
	@RequestMapping("getstudentname.spring")
	@ResponseBody
	public ModelMap getStudentName()
	{
		System.out.println("In getStudentName() ");
		return new ModelMap("data", studentList);
	}
	
}
