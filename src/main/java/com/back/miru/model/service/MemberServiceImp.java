package com.back.miru.model.service;

import com.back.miru.model.dao.MemberDao;
import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImp implements MemberService {
	
	@Autowired
	MemberDao memberDao;

	@Override
	public Member login(Map<String, String> map) throws SQLException {
		return memberDao.login(map);
	}

	@Override
	public boolean signUp(Member member) throws SQLException {
		return memberDao.signUp(member) == 1;
	}

	@Override
	public boolean update(Member member) throws SQLException {
		return memberDao.update(member) == 1;
	}

	@Override
	public boolean delete(String id) throws SQLException {
		return memberDao.delete(id) == 1;
	}

	@Override
	public Member getMember(String id) throws SQLException {
		return memberDao.getMember(id);
	}
	
	@Override
	public List<Interest> interestList(String id) throws Exception {
		return memberDao.interestList(id);
	}
	
	@Override
	public boolean addInterest(Map<String, String> map) throws Exception {
		return memberDao.addInterest(map) == 1;
	}

	@Override
	public boolean removeInterest(Map<String, String> map) throws Exception {
		return memberDao.removeInterest(map) >= 1;
	}
}
