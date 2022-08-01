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
    private UserDAO userDao;

    private UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public int idCheck(String id) throws Exception {
        return userDao.idCheck(id);
    }

    @Override
    public int passwordFindCheck(User user) throws Exception {
        return userDao.passwordFindCheck(user);
    }

    @Override
    public int passwordCheck(Map<String, String> map) throws Exception {
        return userDao.passwordCheck(map);
    }

    @Override
    public void registerUser(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.registerUser(map);
    }

    @Override
    public User login(String id, String password) throws Exception {
        return userDao.login(id, password);
    }

    @Override
    public void updateUser(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.updateUser(map);
    }

    @Override
    public int pwUpdate(Map<String, String> map) throws Exception {
        map.put("salt", randomGenerateString());
        userDao.pwUpdate(map);
        return 1;
    }

    @Override
    public void deleteUser(String id) throws Exception {
        userDao.deleteUser(id);
    }

    @Override
    public User infoUser(String id) throws Exception {
        return userDao.infoUser(id);
    }

    public String randomGenerateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 16;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }

    @Override
    public List<Interest> interestList(String id) throws Exception {
        return userDao.interestList(id);
    }

    @Override
    public boolean addInterest(Map<String, String> map) throws Exception {
        return userDao.addInterest(map) == 1;
    }

    @Override
    public boolean removeInterest(Map<String, String> map) throws Exception {
        return userDao.removeInterest(map) >= 1;
    }
}
