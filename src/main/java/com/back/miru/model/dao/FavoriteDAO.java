package com.back.miru.model.dao;

import com.back.miru.model.dto.FavoritePicture;
import com.back.miru.model.dto.FavoriteUser;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FavoriteDAO {
	void registFavoriteUser(Map<String, String> map) throws SQLException;

	void deleteFavoriteUser(Map<String, String> map) throws SQLException;

	List<FavoriteUser> infoFavoriteUser(String id) throws SQLException;

	void registFavoritePicture(Map<String, String> map) throws SQLException;

	void deleteFavoritePicture(Map<String, String> map) throws SQLException;

	List<FavoritePicture> infoFavoritePicture(String id) throws SQLException;

}