package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import java.util.List;

public interface PageDao {

    Page addPage(Page page);

    void deletePage(int id);
    void deletePageByUser(int id, int userId);

    void updatePage(Page page);
    void updatePageByUser(Page page, int userId);

    Page getPageById(int id);

    List<Page> getAllPages();
    List<Page> getAllPagesByUser(int userId);

    Nav addNav(Nav nav);

    void deleteNav(int id);

    void updateNav(Nav nav);

    Nav getNavById(int id);
    
    Nav getNavByPageId(int id);

    List<Nav> getAllNavs();

}
