package com.back.miru.model.service;

import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MemberService {
	Member login(Map<String, String> map) throws SQLException;
	
	Member getMember(String id) throws SQLException;

	boolean signUp(Member member) throws SQLException;

	boolean update(Member member) throws SQLException;

	boolean delete(String id) throws SQLException;
	
	List<Interest> interestList(String id) throws Exception;
	
	boolean addInterest(Map<String, String> map) throws Exception;

	boolean removeInterest(Map<String, String> map) throws Exception;
}
