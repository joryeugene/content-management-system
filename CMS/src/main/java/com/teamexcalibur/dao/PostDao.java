package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Hashtag;
import com.teamexcalibur.dto.Post;
import java.util.List;

public interface PostDao {
    
    Post addPost(Post post);
    void deletePost(int id);
    void updatePost(Post post);
    Post getPostById(int id);
    List<Post> getAllPosts();
    
    Category addCategory(Category category);
    void deleteCategory(int id);
    void updateCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getAllCategories();
    
    Hashtag addHashtag(Hashtag hashtag);
    void deleteHashtag(int id);
    void updateHashtag(Hashtag hashtag);
    Hashtag getHashtagById(int id);
    List<Hashtag> getAllHashtags();

}
