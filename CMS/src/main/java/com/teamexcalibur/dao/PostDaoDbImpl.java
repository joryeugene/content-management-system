package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Category;
import com.teamexcalibur.dto.Hashtag;
import com.teamexcalibur.dto.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            + " StartDate = ?, EndDate = ?, CategoryId = ?, Queued = ?  where PostId = ?";
    private static final String SQL_SELECT_POST_BYID
            = "select * from Post where PostId = ?";
    private static final String SQL_SELECT_POSTS_BY_CATEGORY_ID
            = "select * from Post where CategoryId = ?";
    private static final String SQL_SELECT_POSTS_BY_HASHTAG_ID
            = "select * from Post where PostId = PostHashtag.PostId and PostHashtag.HashtagId = ?";
    private static final String SQL_SELECT_ALL_POSTS
            = "select * from Post";

    private static final String SQL_INSERT_CATEGORY
            = "insert into Category (CategoryName) values (?)";
    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where CategoryId = ?";
    private static final String SQL_UPDATE_CATEGORY
            = "update Category set CategoryName = ? where PostId = ?";
    private static final String SQL_SELECT_CATEGORY_BYID
            = "select * from Category where CategoryId = ?";
    private static final String SQL_SELECT_USED_CATEGORIES
            = "select distinct * from Category where CategoryId = Post.CategoryId;";
    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Category";

    private static final String SQL_INSERT_HASHTAG
            = "insert into Hashtag (Hashtag) values (?)";
    private static final String SQL_DELETE_HASHTAG
            = "delete from Hashtag where HashtagId = ?";
    private static final String SQL_UPDATE_HASHTAG
            = "update Hashtag set Hashtag = ? where HashtagId = ?";
    private static final String SQL_SELECT_USED_HASHTAGS
            = "select distinct * from Hashtag where HashtagId = PostHashtag.HashtagId";
    private static final String SQL_SELECT_ALL_HASHTAGS
            = "select * from Hashtag";

    // #2a - Declare JdbcTemplate reference - the instance will be handed to us by Spring
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PostDaoDbImpl() {
//        this.uDao = new UserDaoDbImpl();
    }

    public PostDaoDbImpl(UserDao uDao) {
        this.uDao = uDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post addPost(Post post) {
        jdbcTemplate.update(SQL_INSERT_POST,
                post.getAuthor().getId(), post.getTitle(), post.getContent(),
                post.getNumViews(), post.getStartDate(), post.getEndDate(),
                post.getCategory().getId(), post.isQueued());
        post.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        return post;
    }

    @Override
    public void deletePost(int id) {
        jdbcTemplate.update(SQL_DELETE_POST, id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post archivePost(Post post) {
        jdbcTemplate.update(SQL_INSERT_ARCHIVE_POST,
                post.getId(), post.getAuthor().getId(), post.getTitle(), post.getContent(),
                post.getNumViews(), post.getStartDate(), post.getEndDate(),
                post.getCategory().getId(), post.isQueued());
        jdbcTemplate.update(SQL_DELETE_POST, post.getId());
        return post;
    }

    @Override
    public void updatePost(Post post) {
        jdbcTemplate.update(SQL_UPDATE_POST, post.getAuthor().getId(), post.getTitle(),
                post.getContent(), post.getNumViews(), post.getStartDate(), post.getEndDate(),
                post.getCategory().getId(), post.isQueued(), post.getId());
    }

    @Override
    public Post getPostById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getAllPosts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getCategoryById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAllCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hashtag addHashtag(Hashtag hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteHashtag(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateHashtag(Hashtag hashtag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hashtag getHashtagById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hashtag> getAllHashtags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getUsedCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hashtag> getUsedHashtags() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByCategoryId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByHashtagId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

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
//            post.setCategory(.getCategoryById(rs.getInt("CategoryId"))
//            );
            post.setQueued(rs.getBoolean("Queued"));
            return post;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category cat = new Category();
            cat.setId(rs.getInt("CategoryId"));
            cat.setName(rs.getString("Name"));
            return cat;
        }
    }

    private static final class HashtagMapper implements RowMapper<Hashtag> {

        @Override
        public Hashtag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hashtag tag = new Hashtag();
            tag.setId(rs.getInt("HashtagId"));
            tag.setHashtag(rs.getString("Hashtag"));
            return tag;
        }
    }
}
