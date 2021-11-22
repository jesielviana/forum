package com.jesielviana.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class App {

  @RequestMapping("/")
  @ResponseBody
  public String hello() {
    return "Hello Spring Boot!";
  }

}
