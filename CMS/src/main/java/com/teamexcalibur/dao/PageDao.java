package com.teamexcalibur.dao;

import com.teamexcalibur.dto.Nav;
import com.teamexcalibur.dto.Page;
import java.util.List;

public interface PageDao {
    
    Page addPage(Page page);
    void deletePage(int id);
    void updatePage(Page page);
    Page getPageById(int id);
    List<Page> getAllPages();
    List<Nav> getAllNavs();

}
