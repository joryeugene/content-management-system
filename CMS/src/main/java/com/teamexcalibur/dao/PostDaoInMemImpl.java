/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Hashtag;
import com.teamexcalibur.dto.Post;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class PostDaoInMemImpl implements PostDao {

    private Map<Integer, Post> postMap;
    private Map<Integer, Category> categoryMap;
    private Map<Integer, Hashtag> hashtagMap;
    private static int nextPostId;
    private static int nextCategoryId;
    private static int nextHashtagId;

    public PostDaoInMemImpl() {
        postMap = new HashMap<>();
        categoryMap = new HashMap<>();
        hashtagMap = new HashMap<>();
        nextPostId = 0;
        nextCategoryId = 0;
        nextHashtagId = 0;
        if (postMap.size() == 0)
            populate();
    }

    private void populate() {
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
    public Hashtag addHashtag(Hashtag hashtag) {
        hashtag.setId(nextHashtagId++);
        hashtagMap.put(hashtag.getId(), hashtag);
        return hashtag;
    }

    @Override
    public void deleteHashtag(int id) {
        hashtagMap.remove(id);
    }

    @Override
    public void updateHashtag(Hashtag hashtag) {
        hashtagMap.put(hashtag.getId(), hashtag);
    }

    @Override
    public Hashtag getHashtagById(int id) {
        return hashtagMap.get(id);
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        Collection<Hashtag> c = hashtagMap.values();
        return new ArrayList(c);
    }

}
