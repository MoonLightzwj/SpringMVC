package com.zwj.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwj.bean.User;
import com.zwj.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserAction {
@Resource
private IUserService userService;
@RequestMapping("/show")
public String test(HttpServletRequest request,Model model){
	int userid=Integer.parseInt(request.getParameter("userid"));
	User user=userService.getUserById(userid);
	model.addAttribute(user);
	return "login";
}
}
