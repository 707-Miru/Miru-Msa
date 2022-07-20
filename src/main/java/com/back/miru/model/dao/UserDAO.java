package com.back.miru.model.dao;

import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {

    public User login(String id, String password) throws SQLException;

    public User infoUser(String id) throws SQLException;


    public int idCheck(String id) throws SQLException;

    public int passwordFindCheck(User user) throws SQLException;

    public int passwordCheck(Map<String, String> map) throws SQLException;

    public void registerUser(Map<String, String> map) throws SQLException;

    public void updateUser(Map<String, String> map) throws SQLException;

    public void pwUpdate(Map<String, String> map);

    public void deleteUser(String id) throws SQLException;

    List<Interest> interestList(String id) throws Exception;

    int addInterest(Map<String, String> map) throws Exception;

    int removeInterest(Map<String, String> map) throws Exception;
}