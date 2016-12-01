package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class AdminController {

    PageDao dao;
    PostDao postDao;

    @Inject
    public AdminController(PageDao dao, PostDao postDao) {
        this.dao = dao;
        this.postDao = postDao;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String displayAdminPage(Model model) {
        int numPosts = postDao.getQueuedPosts().size();
        model.addAttribute("numPosts", numPosts);
        return "admin";
    }
    
    @RequestMapping(value = {"/admin/pages"}, method = RequestMethod.GET)
    public String displayAdminPages(Model model) {
        return "adminPages";
    }

    @RequestMapping(value = {"/admin/allPosts"}, method = RequestMethod.GET)
    public String displayAdminPostTable(Model model) {
        List<Post> allPosts = postDao.getAllPosts();
        model.addAttribute("allPosts", allPosts);
        return "postTable";
    }

    @RequestMapping(value = {"/pages"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPages() {
        return dao.getAllPages();
    }
    
    @RequestMapping(value = {"/pagenav"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPagesWithNav() {
        
        List<Page> allPages = dao.getAllPages();
        
        for (Page page : allPages) {
            page.setNav(dao.getNavById(page.getId()));
        }
        
        return allPages;
    }

    @RequestMapping(value = {"/admin/page/edit/{id}"}, method = RequestMethod.GET)
    public String displayEditPage(@PathVariable("id") int id, Model model) {
        Page page = dao.getPageById(id);
        model.addAttribute("page", page);
        return "editPage";
    }

    @RequestMapping(value = {"/admin/page/edit/{id}"}, method = RequestMethod.POST)
    public String submitEditPage(@ModelAttribute("page") Page page) {
        page.setUser(dao.getPageById(page.getId()).getUser());
        dao.updatePage(page);
        return "adminPages";
    }
    
    @RequestMapping(value = {"/admin/navs/update"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNavs(@RequestBody List<Nav> navs) {
        for (Nav nav : navs) {
            dao.updateNav(nav);
        }
    }

    @RequestMapping(value = {"/edit/post/{id}"}, method = RequestMethod.GET)
    public String displayEditPost(@PathVariable("id") int id, Model model) {
        Post post = postDao.getPostById(id);
        model.addAttribute("post", post);
        List<Category> allCategories = postDao.getAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "editPost";
    }

    @RequestMapping(value = {"/edit/post/{id}"}, method = RequestMethod.POST)
    public String submitEditPost(@ModelAttribute("post") Post post, BindingResult result) {
        postDao.updatePost(post);
        return "redirect:admin";
    }

    @RequestMapping(value = "/pages/recent", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getSixMostRecentPages() {
        List<Page> allPages = dao.getAllPages();
        int numOfPages = allPages.size();

        List<Page> mostRecent = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < numOfPages; i++) {
            if (count < 6) {
                mostRecent.add(allPages.get(numOfPages - (i + 1)));
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
        int count = (max>allPosts.size())?allPosts.size():max;

        for (int i = 0; i < count; i++) {
                mostRecent.add(allPosts.get(i));
        }

        return mostRecent;
    }
}
