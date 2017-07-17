package com.kaishengit.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/14.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String list(Model model,@RequestParam(required = false,defaultValue="1") Integer pageNo,
    @RequestParam(required = false)String title,@RequestParam(required = false)String daoyan,
                       @RequestParam(required = false)Float min,@RequestParam(required = false)Float max) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movieList",movies);
        PageInfo<Movie> pageInfo =  movieService.findByPage(pageNo);
        model.addAttribute("movieList",pageInfo);
        
        return "movie/list";
    }
    @GetMapping("/del/{id:\\d+}")
    public String delMovieById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        movieService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/movie";
    }
    @GetMapping("/new")
    public String save() {
        return "/movie/new";
    }
    @PostMapping("/new")
    public String save(Movie movie ,RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/movie";
    }
}