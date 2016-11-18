package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Post;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    private final PostDao postDao;

    @Inject
    public BlogController(PostDao postDao) {
        this.postDao = postDao;
    }
    
    @RequestMapping(value = {"/", "/blog"}, method = RequestMethod.GET)
    public String displayMainBlogPage() {
        return "blog";
    }
    
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

}
