package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.Post;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = {"/pages"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPages() {
        return dao.getAllPages();
    }

    @RequestMapping(value = {"/edit/page/{id}"}, method = RequestMethod.GET)
    public String displayEditPage(@PathVariable("id") int id, Model model) {
        Page page = dao.getPageById(id);
        model.addAttribute("page", page);
        return "editPage";
    }

    @RequestMapping(value = {"/edit/page/{id}"}, method = RequestMethod.POST)
    public String submitEditPage(@ModelAttribute("page") Page page, BindingResult result) {
        dao.updatePage(page);
        return "editPage";

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

}
