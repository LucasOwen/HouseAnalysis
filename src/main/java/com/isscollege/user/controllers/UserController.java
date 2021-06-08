/**
 * 
 */
package com.isscollege.user.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.isscollege.user.entity.Users;
import com.isscollege.user.service.impl.UserServiceImpl;

/**
 * 作者: 杜丹东 D.D.D 日期: 2020年10月16日上午10:03:20
 */
@Controller
@RequestMapping("user")
public class UserController {
	private Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return "user-test";
	}

	@RequestMapping("/regist_page")
	public String regist_page() {
		return "regist";
	}

	@RequestMapping("/login_page")
	public String login_page() {
		return "login";
	}

	@RequestMapping("/regist")
	public String regist(Users user, Map<String, Object> map) {
		log.info("regist...." + user);
		int num = userService.retist(user);
		if (num > 0) {
			return "login";
		} else {
			map.put("registDefeat", "注册失败");
			return "regist";
		}
	}

	@RequestMapping("/login")
	public String login(Users user, Map<String, Object> map, HttpSession session) {
		log.info("login...." + user);
		user = userService.login(user);
		if (user != null && user.getUserid() > 0) {
			session.setAttribute("user", user);
			List<Users> userList = userService.getAllUsers(1, 3);
			PageInfo<Users> pageInfo = new PageInfo<Users>(userList);
			map.put("pageInfo", pageInfo);
			map.put("userList", userList);
			return "success";
		} else {
			map.put("loginDefeat", "用户名称或密码不正确，请重新输入");
			return "login";
		}
	}

	@RequestMapping("/getAllUsers")
	public String getAllUsers(Integer pageNum, Integer maxPage, Map<String, Object> map) {
		log.info("getAllUsers...." + pageNum);
		List<Users> userList = userService.getAllUsers(pageNum, maxPage);
		PageInfo<Users> pageInfo = new PageInfo<Users>(userList);
		map.put("pageInfo", pageInfo);
		map.put("userList", userList);
		return "success";
	}

	@RequestMapping("/logout")
	public String logout(Users user, Map<String, Object> map, HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping("/modify")
	@ResponseBody
	public Integer modify(Integer userid, String password) throws Exception {
		System.out.println("modify....   " + userid + "   " + password);
		Integer num = userService.modifyUserPassById(userid, password);
		return num;
	}

	@RequestMapping("/del")
	@ResponseBody
	public Integer del(Integer userid) throws Exception {
		System.out.println("del....   " + userid);
		Integer num = userService.removeUserById(userid);
		return num;
	}

}
