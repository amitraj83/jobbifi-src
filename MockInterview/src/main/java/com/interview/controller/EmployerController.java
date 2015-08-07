package com.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployerController {

	@RequestMapping(value="/employer.do")
	public String getEmployer() {
		return "employer";
	}
}
