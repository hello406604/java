package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/14.
 */
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieMapper movieMapper;
    @Override
    public List<Movie> findAll() {
        return movieMapper.findAll();
    }

    @Override
    public void delById(Integer id) {
        movieMapper.delById(id);
    }

    @Override
    public void save(Movie movie) {
        movieMapper.save(movie);
    }

    @Override
    public PageInfo<Movie> findByPage(Integer pageNo) {
        PageHelper.startPage(pageNo,15);
        List<Movie> movies =movieMapper.findAll();
        return new PageInfo<>(movies);
    }
}
