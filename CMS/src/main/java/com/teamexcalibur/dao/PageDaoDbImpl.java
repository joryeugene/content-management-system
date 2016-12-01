/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PageDaoDbImpl implements PageDao {

    private JdbcTemplate jdbcTemplate;
    private UserDao uDao;

    private static final String SQL_INSERT_PAGE
            = "insert into Page (UserId, Title, Content)"
            + " values (?, ?, ?)";
    private static final String SQL_DELETE_PAGE
            = "delete from Page where PageId = ?";
    private static final String SQL_UPDATE_PAGE
            = "update Page set UserId = ?, Title = ?, Content = ? where PageId = ?";
    private static final String SQL_SELECT_PAGE_BYID
            = "select * from Page where PageId = ?";
    private static final String SQL_SELECT_ALL_PAGES
            = "select * from Page join Nav on Page.PageId = Nav.NavId order by Nav.Position";

    private static final String SQL_INSERT_NAV
            = "insert into Nav (PageId, Position, MenuName)"
            + " values (?, ?, ?)";
    private static final String SQL_DELETE_NAV
            = "delete from Nav where NavId = ?";
    private static final String SQL_DELETE_NAV_BY_PAGEID
            = "delete from Nav where PageId = ?";
    private static final String SQL_UPDATE_NAV
            = "update Nav set Position = ?, MenuName = ? where PageId = ?";
    private static final String SQL_SELECT_NAV_BYID
            = "select * from Nav where NavId = ?";
    private static final String SQL_SELECT_NAV_BY_PAGEID
            = "select * from Nav where PageId = ?";
    private static final String SQL_SELECT_ALL_NAVS
            = "select * from Nav order by Position";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PageDaoDbImpl() {
        this.uDao = new UserDaoDbImpl();
    }

    public PageDaoDbImpl(UserDao uDao) {
        this.uDao = uDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Page addPage(Page page) {
        jdbcTemplate.update(SQL_INSERT_PAGE,
                page.getUser().getId(), page.getTitle(), page.getContent());
        page.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        addNav(new Nav(page.getId(), page.getId(), page.getTitle()));
        return page;
    }

    @Override
    public void deletePage(int id) {
        deleteNavByPageId(id);
        jdbcTemplate.update(SQL_DELETE_PAGE, id);
    }

    @Override
    public void updatePage(Page page) {
        jdbcTemplate.update(SQL_UPDATE_PAGE, page.getUser().getId(),
                page.getTitle(), page.getContent(), page.getId());
    }

    @Override
    public Page getPageById(int id) {
        try {
            Page page = jdbcTemplate.queryForObject(SQL_SELECT_PAGE_BYID,
                    new PageMapper(), id);
            return page;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Page> getAllPages() {
        List<Page> result = jdbcTemplate.query(SQL_SELECT_ALL_PAGES, new PageMapper());
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Nav addNav(Nav nav) {
        jdbcTemplate.update(SQL_INSERT_NAV,
                nav.getPageId(), nav.getPosition(), nav.getMenuName());
        nav.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        return nav;
    }

    @Override
    public void deleteNav(int id) {
        jdbcTemplate.update(SQL_DELETE_NAV, id);
    }
    
    public void deleteNavByPageId(int id) {
        jdbcTemplate.update(SQL_DELETE_NAV_BY_PAGEID, id);
    }

    @Override
    public void updateNav(Nav nav) {
        jdbcTemplate.update(SQL_UPDATE_NAV, nav.getPosition(),
                nav.getMenuName(), nav.getPageId());
    }

    @Override
    public Nav getNavById(int id) {
        try {
            Nav nav = jdbcTemplate.queryForObject(SQL_SELECT_NAV_BYID,
                    new NavMapper(), id);
            return nav;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override    
    public Nav getNavByPageId(int id) {
        try {
            Nav nav = jdbcTemplate.queryForObject(SQL_SELECT_NAV_BY_PAGEID,
                    new NavMapper(), id);
            return nav;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Nav> getAllNavs() {
        List<Nav> result = jdbcTemplate.query(SQL_SELECT_ALL_NAVS, new NavMapper());
        return result;
    }

    private final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
            Page page = new Page();
            page.setId(rs.getInt("PageId"));
            page.setUser(uDao.getUserById(rs.getInt("UserId")));
            page.setTitle(rs.getString("Title"));
            page.setContent(rs.getString("Content"));
            return page;
        }
    }
    
    private final class NavMapper implements RowMapper<Nav> {

        @Override
        public Nav mapRow(ResultSet rs, int rowNum) throws SQLException {
            Nav nav = new Nav();
            nav.setId(rs.getInt("NavId"));
            nav.setPageId(rs.getInt("PageId"));
            nav.setPosition(rs.getInt("Position"));
            nav.setMenuName(rs.getString("MenuName"));
            return nav;
        }
    }
}
