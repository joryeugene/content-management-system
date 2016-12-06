package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dao.PostDao;
import com.teamexcalibur.dao.UserDao;
import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Config;
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
    private Config config = new Config("CrossFit Guild", "Most Recent Posts", "#101010", "#9d9d9d", "squat.jpg");

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
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
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
        return "adminPosts";
    }

//    @RequestMapping(value = {"/pages"}, method = RequestMethod.GET)
//    @ResponseBody
//    public List<Page> getAllPages() {
//        return dao.getAllPages();
//    }
    @RequestMapping(value = {"/admin/pagenav"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Page> getAllPagesWithNav() {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        List<Page> allPages;
        if (isAdmin) {
            allPages = dao.getAllPages();
        } else {
            allPages = dao.getAllPagesByUser(userDao.getUserByEmail(name).getId());
        }

        for (Page page : allPages) {
            page.setNav(dao.getNavById(page.getId()));
        }

        return allPages;
    }

    @RequestMapping(value = {"/admin/page/edit/{id}"}, method = RequestMethod.GET)
    public String displayEditPage(@PathVariable("id") int id, Model model) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        Page page = dao.getPageById(id);
        if (!isAdmin) {
            if (page.getUser().getId() != userDao.getUserByEmail(name).getId()) {
                return "editPage";
            }
        }
        model.addAttribute("page", page);
        return "editPage";
    }

    @RequestMapping(value = {"/admin/post/add"}, method = RequestMethod.GET)
    public String displayAddPost(Model model) {
        model.addAttribute("post", new Post(now, now.plusYears(100)));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("authorName", name);
        List<Category> allCategories = postDao.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        return "addPost";
    }

    @RequestMapping(value = {"/admin/post/add"}, method = RequestMethod.POST)
    public String addPage(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        post.setAuthor(userDao.getUserByEmail(auth.getName()));
        if (result.hasErrors()) {
            List<Category> allCategories = postDao.getAllCategories();
            model.addAttribute("allCategories", allCategories);
            return "addPost";
        }

        if (!isAdmin) {
            post.setQueued(true);
        }
        postDao.addPost(post);
        model.addAttribute("addMessage", "true");
        return "adminPosts";
    }

    @RequestMapping(value = "/admin/page/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePageAndNav(@PathVariable("id") int id) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        Page page = dao.getPageById(id);
        if (!isAdmin) {
            if (page.getUser().getId() != userDao.getUserByEmail(name).getId()) {
                return;
            }
        }
        dao.deletePage(id);
    }

    @RequestMapping(value = "/admin/post/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") int id) {
        postDao.deletePost(id);
    }

    @RequestMapping(value = "/admin/post/approve/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String approvePost(@PathVariable("id") int id) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            postDao.updateQueuedByPostId(postDao.getPostById(id), false);
        }
        return "admin";
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
    public String submitEditPage(@Valid @ModelAttribute("page") Page page, BindingResult result, Model model) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        Page orig = dao.getPageById(page.getId());
        if (!isAdmin) {
            if (orig.getUser().getId() != userDao.getUserByEmail(name).getId()) {
                model.addAttribute("successMessage", "false");
                return "adminPages";
            }
        }

        if (result.hasErrors()) {
            return "editPage";
        }

        page.setUser(dao.getPageById(page.getId()).getUser());
        dao.updatePage(page);
        model.addAttribute("successMessage", "true");
        return "adminPages";
    }

    @RequestMapping(value = {"/admin/page/add"}, method = RequestMethod.POST)
    public String addPage(@Valid @ModelAttribute("page") Page page, BindingResult result, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        page.setUser(userDao.getUserByEmail(auth.getName()));
        if (result.hasErrors()) {
            return "addPage";
        }

        dao.addPage(page);
        model.addAttribute("addMessage", "true");
        return "adminPages";
    }

    @RequestMapping(value = {"/admin/navs/update"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNavs(@RequestBody List<Nav> navs) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            return;
        }
        for (Nav nav : navs) {
            dao.updateNav(nav);
        }
    }

    @RequestMapping(value = {"/edit/post/{id}"}, method = RequestMethod.GET)
    public String displayEditPost(@PathVariable("id") int id, Model model) {
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        Post post = postDao.getPostById(id);
        List<Category> allCategories = postDao.getAllCategories();
        model.addAttribute("allCategories", allCategories);

        if (!isAdmin) {
            if (post.getAuthor().getId() != userDao.getUserByEmail(name).getId()) {
                return "editPost";
            }
        }
        model.addAttribute("post", post);
        return "editPost";
    }

    @RequestMapping(value = {"/edit/post/{id}"}, method = RequestMethod.POST)
    public String submitEditPost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model) {
        List<Post> queuedPosts, viewsListRecent, viewsListAllTime;
        int numPosts;
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            if (postDao.getPostById(post.getId()).getAuthor().getId() != userDao.getUserByEmail(name).getId()) {
                return "redirect:/admin";
            }
        }
        if (result.hasErrors()) {
            List<Category> allCategories = postDao.getAllCategories();
            model.addAttribute("allCategories", allCategories);
            return "editPost";
        }

        post.setAuthor(userDao.getUserByEmail(name));
        post.setCategory(postDao.getCategoryById(post.getCategory().getId()));
        if (!isAdmin) {
            post.setQueued(true);
        }
        postDao.updatePost(post);

        return "redirect:/admin";
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
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            allPages = dao.getAllPages();
        } else {
            allPages = dao.getAllPagesByUser(userDao.getUserByEmail(name).getId());
        }

        count = (max > allPages.size()) ? allPages.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPages.get(i));
        }

        return mostRecent;
    }

    @RequestMapping(value = "/admin/user/curposts/{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getMostRecentPosts(@PathVariable("max") int max) {
        int count;
        List<Post> allPosts;
        List<Post> mostRecent = new ArrayList<>();
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            allPosts = postDao.getCurrentPosts();
        } else {
            allPosts = postDao.getCurrentPostsByUser(userDao.getUserByEmail(name).getId());
        }

        count = (max > allPosts.size()) ? allPosts.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPosts.get(i));
        }

        return mostRecent;
    }

    @RequestMapping(value = "/admin/user/allposts/{max}", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getMostRecentAllPosts(@PathVariable("max") int max) {
        int count;
        List<Post> allPosts;
        List<Post> mostRecent = new ArrayList<>();
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        for (GrantedAuthority grant : auth.getAuthorities()) {
            if (grant.getAuthority().equals(ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        if (isAdmin) {
            allPosts = postDao.getAllPosts();
        } else {
            allPosts = postDao.getAllPostsByUser(userDao.getUserByEmail(name).getId());
        }

        count = (max > allPosts.size()) ? allPosts.size() : max;

        for (int i = 0; i < count; i++) {
            mostRecent.add(allPosts.get(i));
        }

        return mostRecent;
    }

    //Categories
    @RequestMapping(value = {"/admin/add/category"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category addCategory(@Valid @RequestBody Category category) {
        postDao.addCategory(category);
        return category;
    }

    //Hashtags
    @RequestMapping(value = {"/admin/hashtags"}, method = RequestMethod.GET)
    @ResponseBody
    public List<String> allHashtags() {
        List<String> allHashtags = postDao.getAllHashtags();
        return allHashtags;
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
    public void updateUser(@Valid @RequestBody User user) {
        // set the value of the PathVariable id on the incoming user object
        // to ensure that a) the user id is set on the object and b) that
        // the value of the PathVariable id and the user object id are the
        // same.
        String origPw = userDao.getUserById(user.getId()).getPassword();
        if (user.getPassword() != null && user.getPassword().replaceAll("[ \t\n]", "").isEmpty()
                && !origPw.equals(user.getPassword())) { // password changed
            String hashPw = encoder.encode(user.getPassword());
            user.setPassword(hashPw);
        }

        // update the user
        userDao.updateUser(user);
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id, Model model) {
        // remove the user associated with the given id from the data layer
        User user = userDao.getUserById(id);
        if (user != null && user.getAuthority().equals(ADMIN)) {
            int nAdmin = 0;
            List<User> allUsers = userDao.getAllUsers();
            for (User u : allUsers) {
                if (u.getAuthority().equals(ADMIN))
                    nAdmin++;
            }
            if (nAdmin == 1) {
                // don't delete only admin
                model.addAttribute("successMessage", "false");
                return;
            }
        }
        userDao.deleteUser(id);
        return;
    }

    // Start of writer specific code
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

    @RequestMapping(value = {"/admin/settings"}, method = RequestMethod.GET)
    public String displaySettings() {
        return "settings";
    }
}
