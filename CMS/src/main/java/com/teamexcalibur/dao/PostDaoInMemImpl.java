/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class PostDaoInMemImpl implements PostDao {

    private Map<Integer, Post> postMap;
    private Map<Integer, Category> categoryMap;
    private static int nextPostId;
    private static int nextCategoryId;

    public PostDaoInMemImpl() {
        postMap = new HashMap<>();
        categoryMap = new HashMap<>();
        nextPostId = 0;
        nextCategoryId = 0;
        if (postMap.size() == 0)
            populate();
    }

    private void populate() {
        UserDao users = new UserDaoMemoryImpl();
        List<String> oneTag = new ArrayList<>();
        oneTag.add("#YoMama");
        List<String> twoTags = new ArrayList<>();
        twoTags.add("#YoPapa");
        twoTags.add("#YoJory");
        
        this.addCategory(new Category("Monday"));
        this.addCategory(new Category("Tuesday"));
        this.addCategory(new Category("Wednesday"));
        this.addCategory(new Category("Thursday"));
        this.addCategory(new Category("Friday"));
        
        this.addPost(new Post(users.getUserById(0), "Title 0", "Content 0", 0,
                null, null, this.getCategoryById(0), oneTag, false));
        this.addPost(new Post(users.getUserById(1), "Title 1", "Content 1", 0,
                null, null, this.getCategoryById(1), twoTags, false));
        this.addPost(new Post(users.getUserById(0), "Title 2", "Content 2", 0,
                null, null, this.getCategoryById(2), oneTag, false));
        this.addPost(new Post(users.getUserById(1), "Title 3", "Content 3", 0,
                null, null, this.getCategoryById(1), twoTags, false));
        this.addPost(new Post(users.getUserById(0), "Title 4", "Content 4", 0,
                null, null, this.getCategoryById(2), oneTag, false));
    }

    @Override
    public Post addPost(Post post) {
        post.setId(nextPostId++);
        postMap.put(post.getId(), post);
        return post;
    }

    @Override
    public void deletePost(int id) {
        postMap.remove(id);
    }

    @Override
    public void updatePost(Post post) {
        postMap.put(post.getId(), post);
    }

    @Override
    public Post getPostById(int id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getAllPosts() {
        Collection<Post> c = postMap.values();
        return new ArrayList(c);
    }

    @Override
    public Category addCategory(Category category) {
        category.setId(nextCategoryId++);
        categoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        categoryMap.remove(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMap.put(category.getId(), category);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryMap.get(id);
    }

    @Override
    public List<Category> getAllCategories() {
        Collection<Category> c = categoryMap.values();
        return new ArrayList(c);
    }

    @Override
    public List<String> getAllHashtags() {
        HashSet<String> set = new HashSet();
        postMap.values().stream().forEach((post) -> {
            post.getHashtags().stream().forEach((str) -> {
                set.add(str);
            });
        });
        return new ArrayList(set);
    }

    @Override
    public List<Post> getPostsByCategoryId(int id) {
        List<Post> result = new ArrayList<>();
        for (Post post : postMap.values())
            if (post.getCategory().getId() == id)
                result.add(post);
        
        return result;
    }

    @Override
    public List<Category> getUsedCategories() {
        List<Category> result = new ArrayList<>();
        for (Category cat : this.getAllCategories()) {
            for (Post post : this.getAllPosts())
                if (post.getCategory().getId() == cat.getId()) {
                    result.add(cat);
                    break;
                }
        }
        return result;
    }

    @Override
    public List<String> getUsedHashtags() {
        List<String> result = new ArrayList<>();
        for (String tag : this.getAllHashtags()) {
            for (Post post : this.getAllPosts())
                if (hashtagInPost(tag, post)) {
                    result.add(tag);
                    break;
                }
        }
        return result;
    }

    private boolean hashtagInPost(String tag, Post post) {
        for (String entry : post.getHashtags())
            if (entry.equals(tag))
                return true;
        return false;
    }

    @Override
    public List<Post> getPostsByHashtagId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addHashtag(int postId, String hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getHashtagsById(int postId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
