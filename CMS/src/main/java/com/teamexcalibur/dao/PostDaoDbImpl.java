package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apprentice
 */
public class PostDaoDbImpl implements PostDao {

    private UserDao uDao;

    private static final String SQL_INSERT_POST
            = "insert into Post (UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_POST
            = "delete from Post where PostId = ?";
    private static final String SQL_INSERT_ARCHIVE_POST
            = "insert into ArchivedPost (PostId, UserId, Title, Content, NumOfViews, StartDate, EndDate, CategoryId, Queued)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_POST
            = "update Post set UserId = ?, Title = ?, Content = ?, NumOfViews = ?,"
            + " StartDate = ?, EndDate = ?, CategoryId = ?, Queued = ? where PostId = ?";
    private static final String SQL_UPDATE_POST_VIEWS
            = "update Post set NumOfViews = ? where PostId = ?";
    private static final String SQL_UPDATE_POST_QUEUED
            = "update Post set Queued = ? where PostId = ?";
    private static final String SQL_SELECT_POST_BYID
            = "select * from Post where PostId = ?";
    private static final String SQL_SELECT_POSTS_BY_CATEGORY_ID
            = "select * from Post where CategoryId = ? and Queued = false order by StartDate desc";
    private static final String SQL_SELECT_POSTS_BY_HASHTAG
            = "select * from Post join PostHashtag on Post.PostId=PostHashtag.PostId where Hashtag = ? and Queued = false order by StartDate desc";
    private static final String SQL_SELECT_ALL_POSTS
            = "select * from Post order by StartDate desc";
    private static final String SQL_SELECT_MOST_VIEWED_POSTS
            = "select * from Post order by NumOfViews desc limit ?";
    private static final String SQL_SELECT_CURRENT_POSTS
            = "select * from Post where StartDate <= CURDATE() and EndDate >= CURDATE() and Queued = false order by StartDate desc";
    private static final String SQL_SELECT_QUEUED_POSTS
            = "select * from Post where Queued = true order by StartDate desc";
    private static final String SQL_SELECT_POSTS_BY_CATEGORY_AND_USER_ID
            = "select * from Post where CategoryId = ? and Queued = false and UserId = ? order by StartDate desc";
    private static final String SQL_SELECT_POSTS_BY_HASHTAG_USER
            = "select * from Post join PostHashtag on Post.PostId=PostHashtag.PostId where Hashtag = ? and Queued = false and UserId = ? order by StartDate desc";
    private static final String SQL_SELECT_ALL_POSTS_BY_USER
            = "select * from Post where UserId = ? order by StartDate desc";
    private static final String SQL_SELECT_MOST_VIEWED_POSTS_BY_USER
            = "select * from Post where UserId = ? order by NumOfViews desc limit ?";
    private static final String SQL_SELECT_CURRENT_POSTS_BY_USER
            = "select * from Post where StartDate <= CURDATE() and EndDate >= CURDATE() and Queued = false and UserId = ? order by StartDate desc";
    private static final String SQL_SELECT_QUEUED_POSTS_BY_USER
            = "select * from Post where Queued = true and UserId = ? order by StartDate desc";


    private static final String SQL_INSERT_CATEGORY
            = "insert into Category (CategoryName) values (?)";
    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where CategoryId = ?";
    private static final String SQL_UPDATE_CATEGORY
            = "update Category set CategoryName = ? where CategoryId = ?";
    private static final String SQL_SELECT_CATEGORY_BYID
            = "select * from Category where CategoryId = ?";
    private static final String SQL_SELECT_USED_CATEGORIES
            = "select distinct Post.CategoryId, Category.CategoryName from Post join Category on Post.CategoryId=Category.CategoryId;";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Category";

    private static final String SQL_INSERT_HASHTAG
            = "insert into PostHashtag (PostId, Hashtag) values (?, ?)";
    private static final String SQL_DELETE_HASHTAG_BY_POSTID
            = "delete from PostHashtag where PostId = ?";
    private static final String SQL_SELECT_HASHTAG_BY_POSTID
            = "select Hashtag from PostHashtag where PostId = ?";
    private static final String SQL_SELECT_USED_HASHTAGS
            = "select distinct Hashtag from PostHashtag INNER JOIN Post ON PostHashtag.PostId = Post.PostId WHERE Queued = 0"; // from posts that are not queued
    private static final String SQL_SELECT_ALL_HASHTAGS
            = "select distinct Hashtag from PostHashtag";

    // #2a - Declare JdbcTemplate reference - the instance will be handed to us by Spring
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PostDaoDbImpl() {
        this.uDao = new UserDaoDbImpl();
    }

    public PostDaoDbImpl(UserDao uDao) {
        this.uDao = uDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {
        jdbcTemplate.update(SQL_INSERT_POST,
                post.getAuthor().getId(), post.getTitle(), post.getContent(),
                post.getNumViews(), post.getStringStartDate(), post.getStringEndDate(),
                post.getCategory().getId(), post.isQueued());
        post.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        for (String tag : new HashSet<String>(post.getHashtags())) {
            jdbcTemplate.update(SQL_INSERT_HASHTAG, post.getId(), tag);
        }
        return post;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePost(int id) {
        jdbcTemplate.update(SQL_DELETE_HASHTAG_BY_POSTID, id);
        jdbcTemplate.update(SQL_DELETE_POST, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post archivePost(Post post) {
        jdbcTemplate.update(SQL_INSERT_ARCHIVE_POST,
                post.getId(), post.getAuthor().getId(), post.getTitle(), post.getContent(),
                post.getNumViews(), post.getStringStartDate(), post.getStringEndDate(),
                post.getCategory().getId(), post.isQueued());
        jdbcTemplate.update(SQL_DELETE_POST, post.getId());
        return post;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST, post.getAuthor().getId(), post.getTitle(),
                post.getContent(), post.getNumViews(), post.getStringStartDate(), post.getStringEndDate(),
                post.getCategory().getId(), post.isQueued(), post.getId());
        jdbcTemplate.update(SQL_DELETE_HASHTAG_BY_POSTID, post.getId());
        for (String tag : new HashSet<String>(post.getHashtags())) {
            jdbcTemplate.update(SQL_INSERT_HASHTAG, post.getId(), tag);
        }
    }

    @Override
    public Post getPostById(int id) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_SELECT_POST_BYID,
                    new PostMapper(), id);
            List<String> tags = getHashtagsByPostId(id);
            post.setHashtags(tags);
            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_ALL_POSTS, new PostMapper());
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY, category.getName());
        category.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        return category;
    }

    @Override
    public void deleteCategory(int id) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, id);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY, category.getName(), category.getId());
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            Category category = jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY_BYID,
                    new CategoryMapper(), id);
            return category;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES, new CategoryMapper());
    }

    @Override
    public List<String> getAllHashtags() {
        List<String> result = jdbcTemplate.query(SQL_SELECT_ALL_HASHTAGS, new StringMapper());
        return result;
    }

    @Override
    public List<Category> getUsedCategories() {
        List<Category> result = jdbcTemplate.query(SQL_SELECT_USED_CATEGORIES, new CategoryMapper());
        return result;
    }

    @Override
    public List<String> getUsedHashtags() {
        List<String> result = jdbcTemplate.query(SQL_SELECT_USED_HASHTAGS, new StringMapper());
        return result;
    }

    @Override
    public List<Post> getPostsByCategoryId(int id) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_POSTS_BY_CATEGORY_ID, new PostMapper(), id);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public String addHashtag(int postId, String hashtag) {
        jdbcTemplate.update(SQL_INSERT_HASHTAG, postId, hashtag);
        return hashtag;
    }

    @Override
    public List<Post> getPostsByHashtag(String hashtag) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_POSTS_BY_HASHTAG, new PostMapper(), hashtag);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<String> getHashtagsByPostId(int postId) {
        return jdbcTemplate.query(SQL_SELECT_HASHTAG_BY_POSTID, new StringMapper(), postId);
    }

    @Override
    public List<Post> getCurrentPosts() {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_CURRENT_POSTS, new PostMapper());
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getQueuedPosts() {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_QUEUED_POSTS, new PostMapper());
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getMostViewedPosts(int max) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_MOST_VIEWED_POSTS, new PostMapper(), max);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public void addPostView(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST_VIEWS, post.getNumViews() + 1, post.getId());
    }

    @Override
    public void updateQueuedByPostId(Post post, boolean isQueued) {
        jdbcTemplate.update(SQL_UPDATE_POST_QUEUED, isQueued, post.getId());
    }

    @Override
    public boolean deletePostByUser(int postId, int userId) {
        if (this.getPostByIdByUser(postId, userId) == null) // relies on null if belongs to different user
            return false;
        this.deletePost(postId);
        return true;
    }

    @Override
    public boolean updatePostByUser(Post post, int userId) {
        if (post.getAuthor().getId() != userId)
            return false;
        this.updatePost(post);
        return true;
    }

    @Override
    public Post getPostByIdByUser(int postId, int userId) {
        Post post = getPostById(postId);
        if (post == null || post.getAuthor().getId() != userId)
            return null;
        return post;
    }

    @Override
    public List<Post> getPostsByCategoryIdByUser(int id, int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_POSTS_BY_CATEGORY_AND_USER_ID, new PostMapper(), id, userId);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getPostsByHashtagByUser(String hashtag, int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_POSTS_BY_HASHTAG_USER, new PostMapper(), hashtag, userId);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getAllPostsByUser(int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_ALL_POSTS_BY_USER, new PostMapper(), userId);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getCurrentPostsByUser(int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_CURRENT_POSTS_BY_USER, new PostMapper(), userId);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getMostViewedPostsByUser(int max, int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_MOST_VIEWED_POSTS_BY_USER, new PostMapper(), userId, max);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    @Override
    public List<Post> getQueuedPostsByUser(int userId) {
        List<Post> result = jdbcTemplate.query(SQL_SELECT_QUEUED_POSTS_BY_USER, new PostMapper(), userId);
        for (Post post : result) {
            post.setHashtags(getHashtagsByPostId(post.getId()));
        }
        return result;
    }

    private final class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setId(rs.getInt("PostId"));
            post.setAuthor(uDao.getUserById(rs.getInt("UserId")));
            post.setTitle(rs.getString("Title"));
            post.setContent(rs.getString("Content"));
            post.setNumViews(rs.getInt("NumOfViews"));
            post.setStringStartDate(rs.getString("StartDate"));
            post.setStringEndDate(rs.getString("EndDate"));
            post.setCategory(getCategoryById(rs.getInt("CategoryId")));
            post.setQueued(rs.getBoolean("Queued"));
            return post;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category cat = new Category();
            cat.setId(rs.getInt("CategoryId"));
            cat.setName(rs.getString("CategoryName"));
            return cat;
        }
    }

    private static final class StringMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("Hashtag");
        }
    }
}
