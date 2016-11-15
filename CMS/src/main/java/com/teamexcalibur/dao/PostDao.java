package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Hashtag;
import com.teamexcalibur.dto.Post;
import java.util.List;

public interface PostDao {
    
    Post addPost(Post post);
    void deletePost(int id);
    void updatePost(int id);
    Post getPostById(int id);
    List<Post> getAllPosts();
    
    Category addCategory(Category category);
    void deleteCategory(int id);
    void updateCategory(int id);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
    
    Category addHashtag(Hashtag hashtag);
    void deleteHashtag(int id);
    void updateHashtag(int id);
    Hashtag getHashtagById(int id);
    List<Hashtag> getAllHashtags();

}
