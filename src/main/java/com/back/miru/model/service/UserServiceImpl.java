package com.back.miru.model.service;

import com.back.miru.model.dao.UserDAO;
import com.back.miru.model.dto.Interest;
import com.back.miru.model.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    private UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public int checkId(String id) throws Exception {
        return userDao.checkId(id);
    }

    @Override
    public void registerUser(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.registerUser(map);
    }

    @Override
    public void updateUser(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.updateUser(map);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        userDao.deleteUser(id);
    }

    @Override
    public User infoUser(String id) throws Exception {
        return userDao.infoUser(id);
    }

    @Override
    public User loginUser(String id, String password) throws Exception {
        return userDao.loginUser(id, password);
    }

    @Override
    public int checkPasswordFind(User user) throws Exception {
        return userDao.checkPasswordFind(user);
    }

    @Override
    public int checkPassword(Map<String, String> map) throws Exception {
        return userDao.checkPassword(map);
    }

    @Override
    public int updatePw(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.updatePw(map);
        return 1;
    }

    @Override
    public boolean resisterInterest(Map<String, String> map) throws Exception {
        return userDao.registerInterest(map) == 1;
    }

    @Override
    public boolean deleteInterest(Map<String, String> map) throws Exception {
        return userDao.deleteInterest(map) >= 1;
    }

    @Override
    public List<Interest> getInterestList(String id) throws Exception {
        return userDao.getInterestList(id);
    }

    public String randomGenerateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }
}
