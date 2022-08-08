package com.back.miru.model.service;

import com.back.miru.model.dao.FavoriteDAO;
import com.back.miru.model.dto.FavoritePicture;
import com.back.miru.model.dto.FavoriteUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDAO favoriteDAO;

    public void fallback(){
        System.out.println("fallback() 호출");
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public void registFavoriteUser(Map<String, String> map) throws Exception {
        favoriteDAO.registFavoriteUser(map);
    }


    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public void deleteFavoriteUser(Map<String, String> map) throws Exception {
        favoriteDAO.deleteFavoriteUser(map);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public List<FavoriteUser> infoFavoriteUser(String id) throws Exception {
        return favoriteDAO.infoFavoriteUser(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public void registFavoritePicture(Map<String, String> map) throws Exception {
        System.out.println(map);
        favoriteDAO.registFavoritePicture(map);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public void deleteFavoritePicture(Map<String, String> map) throws Exception {
        favoriteDAO.deleteFavoritePicture(map);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public List<FavoritePicture> infoFavoritePicture(String id) throws Exception {
        return favoriteDAO.infoFavoritePicture(id);
    }
}
