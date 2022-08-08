package com.back.miru.model.service;

import com.back.miru.model.dto.FavoritePicture;
import com.back.miru.model.dto.FavoriteUser;

import java.util.List;
import java.util.Map;

public interface FavoriteService {
    void registFavoriteUser(Map<String, String> map) throws Exception;

    void deleteFavoriteUser(Map<String, String> map) throws Exception;

    List<FavoriteUser> infoFavoriteUser(String id) throws Exception;

    void registFavoritePicture(Map<String, String> map) throws Exception;

    void deleteFavoritePicture(Map<String, String> map) throws Exception;

    List<FavoritePicture> infoFavoritePicture(String id) throws Exception;
}
