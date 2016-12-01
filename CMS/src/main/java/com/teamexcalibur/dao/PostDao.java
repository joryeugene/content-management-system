package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Post;
import java.util.List;

public interface PostDao {
    
    Post addPost(Post post);
    void deletePost(int id);
    boolean deletePostByUser(int PostId, int userId);
    void updatePost(Post post);
    boolean updatePostByUser(Post post, int userId);
    void addPostView(Post post);
    void updateQueuedByPostId(Post post, boolean isQueued);
    Post getPostById(int id);
    Post getPostByIdByUser(int postId, int userId);
    List<Post> getPostsByCategoryId(int id);
    List<Post> getPostsByCategoryIdByUser(int id, int userId);
    List<Post> getPostsByHashtag(String hashtag);
    List<Post> getPostsByHashtagByUser(String hashtag, int userId);
    List<Post> getAllPosts();
    List<Post> getAllPostsByUser(int userId);
    List<Post> getCurrentPosts();
    List<Post> getCurrentPostsByUser(int userId);
    List<Post> getMostViewedPosts(int max);
    List<Post> getMostViewedPostsByUser(int max, int userId);
    List<Post> getQueuedPosts();
    List<Post> getQueuedPostsByUser(int userId);
    
    Category addCategory(Category category);
    void deleteCategory(int id);
    void updateCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getUsedCategories();
    List<Category> getAllCategories();
    
    String addHashtag(int postId, String hashtag);
    List<String> getHashtagsByPostId(int postId);
    List<String> getUsedHashtags();
    List<String> getAllHashtags();

}
