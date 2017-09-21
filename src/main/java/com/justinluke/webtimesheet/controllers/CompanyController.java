package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.data.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Created by there on 8/3/2017.
 */
@Controller
@RequestMapping(value = "company")
public class CompanyController {

    @Autowired
    private CompanyDao companyDao;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Company());
        return "company/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Company company, Errors errors) {
        if (errors.hasErrors()){
            return "company/add";
        }
        companyDao.save(company);
        return "redirect:/company/";
    }

    @RequestMapping(value = "view/{companyId}", method = RequestMethod.GET)
    public String viewCompany(Model model, @PathVariable int companyId){
    Company comp = companyDao.findOne(companyId);
    model.addAttribute("company", comp);
        return "company/view";
    }

    @RequestMapping(value = "remove/{companyId}", method = RequestMethod.GET)
    public String displayRemoveShiftForm(Model model, @PathVariable int companyId) {
        model.addAttribute("company", companyDao.findOne(companyId));
        return "company/remove";
    }

    @RequestMapping(value = "remove/{companyId}", method = RequestMethod.POST)
    public String processRemoveShiftForm(@RequestParam int[] shiftIds, @PathVariable int companyId) {
        Company comp = companyDao.findOne(companyId);
        for (int shiftId : shiftIds) {
            comp.removeShift(shiftId);

        }
        return "redirect:/company/view/" + companyId;
    }
}
