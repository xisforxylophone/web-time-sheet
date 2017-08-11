package com.justinluke.webtimesheet.controllers;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.CompanyData;
import com.justinluke.webtimesheet.models.Shift;
import com.justinluke.webtimesheet.models.ShiftData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by there on 7/15/2017.
 */

@Controller
@RequestMapping(value = "shift")
public class ShiftController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("companies", CompanyData.getAll());
        return "shift/index";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.GET)
    public String displayTimeEntry(Model model) {
        model.addAttribute(new Shift());
        model.addAttribute("companies", CompanyData.getAll());
        return "shift/time-entry";
    }

    @RequestMapping(value = "time-entry", method = RequestMethod.POST)
    public String processTimeEntry(Model model, @ModelAttribute @Valid Shift shift, Errors errors, int companyId) {
        Company comp = CompanyData.getById(companyId);
        shift.setCompany(comp);
        comp.addShift(shift);
        ShiftData.add(shift);
        return "shift/confirmation";
    }

}