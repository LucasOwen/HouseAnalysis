/**
 * 
 */
package com.isscollege.user.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 作者: 杜丹东 D.D.D 日期: 2020年10月16日上午10:17:13
 */
@Data
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String username;
	private String password;
	private Integer sex;

}
