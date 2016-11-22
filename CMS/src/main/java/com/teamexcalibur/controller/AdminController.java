package com.teamexcalibur.controller;

import com.teamexcalibur.dao.PageDao;
import com.teamexcalibur.dto.Page;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    PageDao dao;

    @Inject
    public AdminController(PageDao dao) {
        this.dao = dao;

    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String displayAdminPage() {
        return "admin";
    }

@RequestMapping(value = {"pages"}, method = RequestMethod.GET)
@ResponseBody    
public List<Page> getAllPages() {
        return dao.getAllPages();
    }

@RequestMapping(value = {"/edit/page/{id}"}, method = RequestMethod.GET)
@ResponseBody
public Page displayEditPage(@PathVariable("id") int id){
    return dao.getPageById(id);
    
}


}
