package com.isscollege.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.isscollege.user.dao.IUserDao;
import com.isscollege.user.entity.Users;
import com.isscollege.user.service.IUserService;



/**
 * @author Benjamin
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired(required = false)
    private IUserDao userDao;

    @Override
    public int retist(Users user) {

        return userDao.regist(user);
    }

    @Override
    public Users login(Users user) {

        return userDao.login(user);
    }

    @Override
    public List<Users> getAllUsers(Integer PageNum, Integer maxPage) {
        if (PageNum <= 1) {
            PageNum = 1;
        } else if (PageNum >= maxPage) {
            PageNum = maxPage;
        }
        PageHelper.startPage(PageNum, 3);
        return userDao.getAllUsers();
    }

    @Override
    public Integer modifyUserPassById(Integer userid, String password) {
        Users user = new Users();
        user.setUserid(userid);
        user.setPassword(password);
        return userDao.modifyUserPassById(user);
    }

    @Override
    public Integer removeUserById(Integer userid) {
        return userDao.removeUserById(userid);
    }

}
