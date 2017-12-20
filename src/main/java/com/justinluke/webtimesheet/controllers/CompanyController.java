package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.User;
import com.justinluke.webtimesheet.models.data.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by there on 8/3/2017.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController extends AuthenticationController {

    @Autowired
    private CompanyDao companyDao;


    @RequestMapping(value = "view/{companyId}", method = RequestMethod.GET)
    public String displayCompany(Model model, @PathVariable int companyId, HttpServletRequest request){
    Company comp = companyDao.findOne(companyId);
    User user = getUserForModel(request);
    model.addAttribute("user", user);
    model.addAttribute("company", comp);
        return "company/view";
    }

}
