package com.back.miru.model.service;

import com.back.miru.model.dto.Favorite;

import java.util.List;
import java.util.Map;

public interface FavoriteService {
    void registFavorite(Map<String, String> map) throws Exception;

    void deleteFavorite(Map<String, String> map) throws Exception;

    List<Favorite> infoFavorite(Map<String, String> map) throws Exception;
}
