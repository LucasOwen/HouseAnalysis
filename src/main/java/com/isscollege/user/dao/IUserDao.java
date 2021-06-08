/**
 * 
 */
package com.isscollege.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.isscollege.user.entity.Users;

/**
 * 作者: 杜丹东 D.D.D 日期: 2020年10月16日上午10:26:43
 */
// @Mapper注解是mybatis提供的，@Autowired是spring提供的。
@Mapper
public interface IUserDao {
	@Insert("insert into users (username,password,sex) values(#{username},#{password},#{sex})")
	int regist(Users user);

	Users login(Users user);

	List<Users> getAllUsers();

	@Update("update users set password=#{password} where userid=#{userid}")
	Integer modifyUserPassById(Users user);

	@Delete("delete from users where userid=#{userid}")
	Integer removeUserById(Integer userid);
}
