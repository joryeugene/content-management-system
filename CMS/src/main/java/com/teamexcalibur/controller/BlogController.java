package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Config;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class BlogController {

    private final PostDao postDao;
    private final PageDao pageDao;
    private Config config = new Config("CrossFit Guild", "Most Recent Posts", "#101010", "#9d9d9d", "#337ab7", "squat.jpg");

    @Inject
    public BlogController(PostDao postDao, PageDao pageDao) {
        this.postDao = postDao;
        this.pageDao = pageDao;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }
    
    @RequestMapping(value = "/currentposts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getCurrentPosts() {
        return postDao.getCurrentPosts();
    }
    
    @RequestMapping(value = "/queuedposts", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getQueuedPosts() {
        return postDao.getQueuedPosts();
    }

    @RequestMapping(value = "/posts/recent", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getSixMostRecentPosts() {
        List<Post> allPosts = postDao.getCurrentPosts();
        int numOfPosts = allPosts.size();

        List<Post> mostRecent = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < numOfPosts; i++) {
            if (count < 6) {
                mostRecent.add(allPosts.get(i));
                count++;
            }
        }

        return mostRecent;
    }
    
    @RequestMapping(value = "/posts/recent/{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getMostRecentPosts(@PathVariable("max") int max) {
        List<Post> allPosts = postDao.getCurrentPosts();
        List<Post> mostRecent = new ArrayList<>();
        int count = (max > allPosts.size()) ? allPosts.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPosts.get(i));
        }

        return mostRecent;
    }
    
    @RequestMapping(value = {"/", "/blog"}, method = RequestMethod.GET)
    public String displayMainBlogPage(Model model) {
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        model.addAttribute("categories", postDao.getUsedCategories());
        model.addAttribute("hashtags", postDao.getUsedHashtags());
        return "blog";
    }
    
    @RequestMapping(value = {"/summary"}, method = RequestMethod.GET)
    public String displayAllPosts(Model model) {
        model.addAttribute("title", "All Posts");
        model.addAttribute("categories", postDao.getUsedCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        model.addAttribute("posts", postDao.getCurrentPosts());
        model.addAttribute("hashtags", postDao.getUsedHashtags());
        return "posts";
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String displayCategoryPosts(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", postDao.getCategoryById(id).getName());
        model.addAttribute("categories", postDao.getUsedCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        model.addAttribute("posts", postDao.getPostsByCategoryId(id));
        model.addAttribute("hashtags", postDao.getUsedHashtags());
        return "posts";
    }
    
    @RequestMapping(value = "/hashtag/{hashtagLink}", method = RequestMethod.GET)
    public String displayHashtagPosts(@PathVariable("hashtagLink") String hashtag, Model model) {
        model.addAttribute("title", "#" + hashtag);
        model.addAttribute("categories", postDao.getUsedCategories());
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        model.addAttribute("posts", postDao.getPostsByHashtag("#" + hashtag));
        model.addAttribute("hashtags", postDao.getUsedHashtags());
        return "posts";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String displayBlogPost(@PathVariable("id") int id, Model model) {
        Post post = postDao.getPostById(id);
        postDao.addPostView(post);
        model.addAttribute("post", post);
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        model.addAttribute("categories", postDao.getUsedCategories());
        model.addAttribute("hashtags", postDao.getUsedHashtags());
        return "post";
    }
    
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String displayStaticPage(@PathVariable("id") int id, Model model) {
        Page page = pageDao.getPageById(id);
        model.addAttribute("page", page);
        model.addAttribute("navs", pageDao.getAllNavs());
        model.addAttribute("config", config);
        return "page";
    }
    
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public Config getConfigFile() {
        return config;
    }

    @RequestMapping(value = {"/config"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Config updateConfigFile(@RequestBody Config newConfig) {
        this.config = newConfig;
        return config;
    }

}
