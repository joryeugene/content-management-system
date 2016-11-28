package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    private final PostDao postDao;
    private final PageDao pageDao;

    @Inject
    public BlogController(PostDao postDao, PageDao pageDao) {
        this.postDao = postDao;
        this.pageDao = pageDao;
    }

    @RequestMapping(value = {"/", "/blog"}, method = RequestMethod.GET)
    public String displayMainBlogPage(Model model) {
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("categories", postDao.getAllCategories());
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
    
    @RequestMapping(value = {"/summary"}, method = RequestMethod.GET)
    public String displayAllPosts(Model model) {
        model.addAttribute("categories", postDao.getAllCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("posts", postDao.getAllPosts()); // rev the order - get most recent first
        return "posts";
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String displayCategoryPosts(@PathVariable("id") int id, Model model) {
        model.addAttribute("categories", postDao.getAllCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("posts", postDao.getPostsByCategoryId(id)); // rev order??
        return "posts";
    }
    
    @RequestMapping(value = "/hashtag/{hashtagLink}", method = RequestMethod.GET)
    public String displayHashtagPosts(@PathVariable("hashtagLink") String hashtag, Model model) {
        model.addAttribute("categories", postDao.getAllCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("posts", postDao.getPostsByHashtag("#" + hashtag));
        return "posts";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String displayBlogPost(@PathVariable("id") int id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("categories", postDao.getAllCategories());
        return "post";
    }
    
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String displayStaticPage(@PathVariable("id") int id, Model model) {
        Page page = pageDao.getPageById(id);
        model.addAttribute("page", page);
        model.addAttribute("navs", pageDao.getAllNavs());
        return "page";
    }

}
