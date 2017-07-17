package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/14.
 */
public interface MovieMapper {
    List<Movie> findAll();

    void delById(Integer id);

    void save(Movie movie);
}
