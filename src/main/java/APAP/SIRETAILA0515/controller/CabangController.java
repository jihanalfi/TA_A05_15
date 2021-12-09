package APAP.SIRETAILA0515.controller;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.model.UserModel;
import APAP.SIRETAILA0515.rest.CouponDetail;
import APAP.SIRETAILA0515.service.CabangService;
import APAP.SIRETAILA0515.service.CouponService;
import APAP.SIRETAILA0515.service.ItemCabangService;
import APAP.SIRETAILA0515.service.UserService;
import APAP.SIRETAILA0515.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCabangService itemCabangService;

    @GetMapping("/cabang/add")
    public String addCabangForm(Model model) {
        CabangModel cabang = new CabangModel();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        if (currentPrincipalName.equals("[Kepala Retail]")) {
            model.addAttribute("cabang", cabang);
            return "form-add-cabang";
        } else if (currentPrincipalName.equals("[Manager Cabang]")) {
            model.addAttribute("cabang", cabang);
            return "form-add-cabang";
        }
        return "Access-Denied";
    }

    @PostMapping(value="/cabang/add")
    public String addCabangSubmit(@ModelAttribute CabangModel cabang, BindingResult bindingResult, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserModel penanggung_jawab = userService.findUserbyUsername(currentPrincipalName);
        int i=2;
        long status = i;
        cabang.setPenanggungJawab(penanggung_jawab);
        cabang.setStatus(status);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        model.addAttribute("role",currentPrincipalName);
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }


    @GetMapping("/kupon/{cabangId}/{itemId}")
    public String pakaiKupon(Model model, @PathVariable Long cabangId,@PathVariable Long itemId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        model.addAttribute("role",currentPrincipalName);
        List<CouponDetail> listKupon = couponService.getCoupons();
        CabangModel cabang = cabangService.getCabangById(cabangId);
        ItemCabangModel item = itemCabangService.getItemById(itemId);
        CouponDetail coupon = new CouponDetail();
        model.addAttribute("coupon", coupon);
        model.addAttribute("listKupon", listKupon);
        model.addAttribute("cabang",cabang);
        model.addAttribute("item",item);
        return "list-kupon";
    }

    @PostMapping(value="/kupon/{cabangId}/{itemId}")
    public String pakaiKuponSubmit(@ModelAttribute CouponDetail coupon, @PathVariable Long cabangId,@PathVariable Long itemId, BindingResult bindingResult, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserModel penanggung_jawab = userService.findUserbyUsername(currentPrincipalName);
        ItemCabangModel item = itemCabangService.getItemById(itemId);
        Long newHarga = item.getHarga()-Math.round(coupon.getDiscountAmount());
        item.setHarga(newHarga);
        item.setId_promo(coupon.getId());
        itemCabangService.addItem(item);
        return "kupon-acc";
    }



    @GetMapping("/cabang/view/{idCabang}")
    public String viewDetailCabang(
            @PathVariable Long idCabang,
            Model model
    ) {
        CabangModel cabang = cabangService.getCabangById(idCabang);
        List<ItemCabangModel> itemCabang = itemCabangService.retrieveItemByCabang(cabang);
        model.addAttribute("cabang", cabang);
        model.addAttribute("listItem", itemCabang);
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