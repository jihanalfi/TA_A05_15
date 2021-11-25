package APAP.SIRETAILA0515.controller;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CabangController {
    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;


    @GetMapping("/cabang/add")
    public String addCabangForm(Model model) {
        model.addAttribute("cabang", new CabangModel());
        return "form-add-cabang";
    }

    @PostMapping(value="/cabang/add",params={"save"})
    public String addCabangSubmit(@ModelAttribute CabangModel cabang, Model model) {
        cabangService.addCabang(cabang);
        model.addAttribute("Id",cabang.getId());
        return "add-cabang";
    }
//
//    @PostMapping(value="/cabang/add", params={"addRow"})
//    public String addRow(
//            @ModelAttribute CabangModel cabang,
//            BindingResult bindingResult,
//            Model model) {
//        List<MenuModel> listMenu = menuService.getListMenu();
//        if(cabang.getListMenu() == null) {
//            cabang.setListMenu(new ArrayList<MenuModel>());
//        }
//        List<MenuModel> listMenuSementara = cabang.getListMenu();
//        MenuModel menu = new MenuModel();
//        listMenuSementara.add(menu);
//        model.addAttribute("listMenu", listMenu);
//        model.addAttribute("cabang", cabang);
//        return "form-add-cabang";
//    }
//
//    @PostMapping(value="/cabang/add", params={"removeRow"})
//    public String removeRow(
//            @ModelAttribute CabangModel cabang,
//            final BindingResult bindingResult,
//            Model model,
//            final HttpServletRequest request) {
//        List<MenuModel> listMenu = menuService.getListMenu();
//        final Integer idRow = Integer.valueOf(request.getParameter("removeRow"));
//        cabang.getListMenu().remove(idRow.intValue());
//        model.addAttribute("cabang", cabang);
//        model.addAttribute("listMenu", listMenu);
//        return "form-add-cabang";
//    }

    @GetMapping("/cabang/viewall")
    public String listCabang(Model model) {
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/view")
    public String viewDetailCabang(
            @RequestParam(value = "Id") Long Id,
            Model model
    ) {
        CabangModel cabang = cabangService.getCabangById(Id);
        model.addAttribute("cabang", cabang);
        return "view-cabang";
    }

//    @GetMapping("/cabang/update/{noCabang}")
//    public String updateCabangForm(
//            @PathVariable Long noCabang,
//            Model model
//    ) {
//        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
//        model.addAttribute("cabang", cabang);
//        return "form-update-cabang";
//    }
//
//    @PostMapping("/cabang/update")
//    public String updateCabangSubmit(
//            @ModelAttribute CabangModel cabang,
//            Model model
//    ) {
//        cabangService.updateCabang(cabang);
//        model.addAttribute("noCabang", cabang.getNoCabang());
//        return "update-cabang";
//    }
}