package com.min.fashion.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class HomeController {
  @GetMapping("/")
  public String index(){
    return "index";
  }
  @GetMapping("/user") //user만 들어갈수 있음
  public String user(){
    return "user";
  }

  @GetMapping("login/{oauth2")
  public String loginGoogle(@PathVariable String oauth2, HttpServletResponse httpServletResponse){
    return  "redirect:/oauth2/authorization/" + oauth2;
  }
  //접근권한 없을때
  @RequestMapping("/accessDenied")
  public String accessDenied(){return "accessDenied";}
}
