package com.info.testmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping({"/home","/"})	
  public String getHomePage() {
	  return "home";
  }
  @GetMapping("/create-new-account")
  public String getCreateAccountPage() {
	  return "create-account";
  }
}
