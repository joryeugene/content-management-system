package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
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

    @RequestMapping(value = "/posts/recent", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getSixMostRecentPosts() {
        List<Post> allPosts = postDao.getAllPosts();
        int numOfPosts = allPosts.size();

        List<Post> mostRecent = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < numOfPosts; i++) {
            if (count < 6) {
                mostRecent.add(allPosts.get(numOfPosts - (i + 1)));
                count++;
            }
        }

        return mostRecent;
    }

}
