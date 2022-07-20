package com.back.miru.model.dao;

import com.back.miru.model.dto.User;

import java.sql.SQLException;
import java.util.Map;


public interface UserDAO {

	public User login(String id, String password) throws SQLException;

	public int idCheck(String id) throws SQLException;

	public int passwordFindCheck(User user) throws SQLException;
	
	public int passwordCheck(Map<String, String> map) throws SQLException;

	public void registerUser(Map<String, String> map) throws SQLException;

	public void updateUser(Map<String, String> map) throws SQLException;

	public void pwUpdate(Map<String, String> map);
	
	public void deleteUser(String id) throws SQLException;

	public User infoUser(String id) throws SQLException;

}