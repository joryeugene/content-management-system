package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dao.UserDao;
import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import com.teamexcalibur.dto.Post;
import com.teamexcalibur.dto.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private PageDao dao;
    private PostDao postDao;
    private UserDao userDao;
    private PasswordEncoder encoder;
    private LocalDate now = LocalDate.now();
    private final String ADMIN = "admin"; 

    @Inject
    public AdminController(PageDao dao, PostDao postDao, UserDao userDao, PasswordEncoder pwe) {
        this.dao = dao;
        this.postDao = postDao;
        this.userDao = userDao;
        this.encoder = pwe;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String displayAdminPage(Model model) {
        List<Post> queuedPosts, viewsListRecent, viewsListAllTime;
        int numPosts;
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        
        if (isAdmin) {
            queuedPosts = postDao.getQueuedPosts();
            numPosts = postDao.getQueuedPosts().size();
            viewsListRecent = postDao.getCurrentPosts();
            viewsListAllTime = postDao.getMostViewedPosts(5);
        } else {
            int userId = userDao.getUserByEmail(name).getId();
            queuedPosts = postDao.getQueuedPostsByUser(userId);
            numPosts = queuedPosts.size();
            viewsListRecent = postDao.getCurrentPostsByUser(userId);
            viewsListAllTime = postDao.getMostViewedPostsByUser(5, userId);
        }

        model.addAttribute("queuedPosts", queuedPosts);
        model.addAttribute("numPosts", numPosts);
        model.addAttribute("viewsListRecent", viewsListRecent);
        model.addAttribute("viewsListAllTime", viewsListAllTime);

        return "admin";
    }

    @RequestMapping(value = {"/admin/pages"}, method = RequestMethod.GET)
    public String displayAdminPages(Model model) {
        return "adminPages";
    }

    @RequestMapping(value = {"/admin/posts"}, method = RequestMethod.GET)
    public String displayAdminPostTable(Model model) {
        List<Post> allPosts;
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        
        if (isAdmin)
            allPosts = postDao.getAllPosts();
        else allPosts = postDao.getAllPostsByUser(userDao.getUserByEmail(name).getId());
        
        model.addAttribute("allPosts", allPosts);
        return "adminPosts";
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

    @RequestMapping(value = "/admin/page/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePageAndNav(@PathVariable("id") int id) {
        dao.deletePage(id);
    }

    @RequestMapping(value = {"/admin/page/add"}, method = RequestMethod.GET)
    public String displayAddPage(Model model) {
        model.addAttribute("page", new Page("Page Title", "Page Content", ""));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        model.addAttribute("username", name);
        return "addPage";
    }

    @RequestMapping(value = {"/admin/page/edit/{id}"}, method = RequestMethod.POST)
    public String submitEditPage(@ModelAttribute("page") Page page, Model model) {
        page.setUser(dao.getPageById(page.getId()).getUser());
        dao.updatePage(page);
        model.addAttribute("successMessage", "true");
        return "adminPages";
    }

    @RequestMapping(value = {"/admin/page/add"}, method = RequestMethod.POST)
    public String addPage(@ModelAttribute("page") Page page, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        page.setUser(userDao.getUserByEmail(auth.getName()));
        dao.addPage(page);
        model.addAttribute("addMessage", "true");
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
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        
        Post post = postDao.getPostById(id);
        List<Category> allCategories = postDao.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        if (!isAdmin) {
            if (post.getAuthor().getId() != userDao.getUserByEmail(name).getId())
                return "editPost";
        }
        model.addAttribute("post", post);
        return "editPost";
    }

    @RequestMapping(value = {"/edit/post/{id}"}, method = RequestMethod.POST)
    public String submitEditPost(@ModelAttribute("post") Post post, BindingResult result) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        if (!isAdmin) {
            if (post.getAuthor().getId() != userDao.getUserByEmail(name).getId())
                return "redirect:admin";
        }
        postDao.updatePost(post);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/edit/post/{id}/category"}, method = RequestMethod.POST)
    public String submitEditPostCategory(@ModelAttribute("post") Post post, BindingResult result) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        if (!isAdmin) {
            if (post.getAuthor().getId() != userDao.getUserByEmail(name).getId())
                return "redirect:admin";
        }
        postDao.updatePost(post);
        return "redirect:admin";
    }

    @RequestMapping(value = {"/admin/post/add"}, method = RequestMethod.GET)
    public String displayAddPost(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
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
    
    @RequestMapping(value = "/admin/user/pages/{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getMostRecentPages(@PathVariable("max") int max) {
        int count;
        List<Page> allPages;
        List<Page> mostRecent = new ArrayList<>();
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        
        if (isAdmin)
            allPages = dao.getAllPages();
        else allPages = dao.getAllPagesByUser(userDao.getUserByEmail(name).getId());
        
        count = (max > allPages.size()) ? allPages.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPages.get(i));
        }

        return mostRecent;
    }

    @RequestMapping(value = "/admin/user/posts/{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getMostRecentPosts(@PathVariable("max") int max) {
        int count;
        List<Post> allPosts;
        List<Post> mostRecent = new ArrayList<>();
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities())
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
        }
        
        if (isAdmin)
            allPosts = postDao.getCurrentPosts();
        else allPosts = postDao.getCurrentPostsByUser(userDao.getUserByEmail(name).getId());
        
        count = (max > allPosts.size()) ? allPosts.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPosts.get(i));
        }

        return mostRecent;
    }

    @RequestMapping(value = {"/admin/users"}, method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @RequestMapping(value = {"/admin/userTable"}, method = RequestMethod.GET)
    public String displayUserPage() {
        return "userTable";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        // Retrieve the user associated with the given id and return it
        return userDao.getUserById(id);
    }
    
    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User createUser(@Valid @RequestBody User user) {
        String hashPw = encoder.encode(user.getPassword());
        user.setPassword(hashPw);

        userDao.addUser(user);
        return user;
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") int id, @RequestBody User user) {
        // set the value of the PathVariable id on the incoming user object
        // to ensure that a) the user id is set on the object and b) that
        // the value of the PathVariable id and the user object id are the
        // same.
        String origPw = userDao.getUserById(id).getPassword();
        if (!origPw.equals(user.getPassword())) { // password changed
            String hashPw = encoder.encode(user.getPassword());
            user.setPassword(hashPw);
        }

        user.setId(id);
        // update the user
        userDao.updateUser(user);
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) {
        // remove the Dvd associated with the given id from the data layer
        userDao.deleteUser(id);
    }
    
    // Start of writer specific code
    
}
