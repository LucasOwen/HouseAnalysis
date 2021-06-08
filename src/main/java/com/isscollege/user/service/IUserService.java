/**
 * 
 */
package com.isscollege.user.service;

import java.util.List;

import com.isscollege.user.entity.Users;

/**
 * 作者: 杜丹东 D.D.D 日期: 2020年10月16日上午10:20:27
 */
public interface IUserService {
	public int retist(Users user);

	Users login(Users user);

	List<Users> getAllUsers(Integer PageNum, Integer maxPage);

	public Integer modifyUserPassById(Integer userid, String password);

	Integer removeUserById(Integer userid);
}
