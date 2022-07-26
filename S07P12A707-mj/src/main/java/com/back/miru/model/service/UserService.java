package com.back.miru.model.service;

import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int idCheck(String id) throws Exception;

    void registerUser(Map<String, String> map) throws Exception;

    User login(String id, String password) throws Exception;

    int passwordFindCheck(User user) throws Exception;

    int passwordCheck(Map<String, String> map) throws Exception;

    void updateUser(Map<String, String> map) throws Exception;

    int pwUpdate(Map<String, String> map) throws Exception;

    void deleteUser(String id) throws Exception;

    User infoUser(String id) throws Exception;

    List<Interest> interestList(String id) throws Exception;

    boolean addInterest(Map<String, String> map) throws Exception;

    boolean removeInterest(Map<String, String> map) throws Exception;

}
