package APAP.SIRETAILA0515.controller;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.model.UserModel;
import APAP.SIRETAILA0515.rest.CouponDetail;
import APAP.SIRETAILA0515.rest.ItemDetail;
import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import APAP.SIRETAILA0515.service.*;
import APAP.SIRETAILA0515.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class CabangController {
    @Qualifier("itemRestServiceImpl")
    @Autowired
    private ItemRestService itemRestService;

    @Qualifier("cabangServiceImpl")

    @Autowired
    private CabangService cabangService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCabangService itemCabangService;

    @Autowired
    private CabangRestService cabangRestService;

    @Autowired
    private ItemRequestRestService itemRequestRestService;

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
        UserModel penanggung_jawab = userService.findUserbyUsername(currentPrincipalName);
        String namaPengguna = authentication.getName();
        model.addAttribute("role",currentPrincipalName);
        if (currentPrincipalName.equals("[Kepala Retail]")) {
            List<CabangModel> listCabang = cabangService.getCabangList();
            model.addAttribute("listCabang", listCabang);
            return "viewall-cabang";
        } else if (currentPrincipalName.equals("[Manager Cabang]")) {
            List<CabangModel> listCabangBefore = cabangService.getCabangList();
            List<CabangModel> listCabang = new ArrayList<CabangModel>();
//            System.out.println(namaPengguna);
            for (CabangModel c:listCabangBefore) {
//                System.out.println(c.getPenanggungJawab().getUsername());
                if (c.getPenanggungJawab().getUsername().equals(namaPengguna)){
                    listCabang.add(c);
                }
            }
            model.addAttribute("listCabang", listCabang);
            return "viewall-cabang";
        }
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/list-request-cabang")
    public String listRequestCabang(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        List<CabangModel> listCabang = cabangRestService.retrieveListRequestCabang();
        
        model.addAttribute("role",currentPrincipalName);
        model.addAttribute("listCabang", listCabang);
          if (currentPrincipalName.equals("[Kepala Retail]")) {
              return "viewall-request-cabang";
          }   
          return "Access-DeniedItem";
    }

    @PostMapping("/cabang/list-request-cabang")
    public String requestCabang(@ModelAttribute CabangModel cabang,
            Model model){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String currentPrincipalName = authentication.getName();
         UserModel penanggungJawab = userService.findUserbyUsername(currentPrincipalName);

         cabang.setPenanggungJawab(penanggungJawab);
         cabangService.updateCabang(cabang);

         model.addAttribute("role",currentPrincipalName);
         List<CabangModel> listCabang = cabangRestService.retrieveListRequestCabang();
         model.addAttribute("listCabang", listCabang);
         return "viewall-request-cabang";
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
    @GetMapping("/cabang/{cabangId}/delete/{itemId}")
    public String deleteItem(
            @PathVariable Long cabangId,@PathVariable Long itemId, Model model
    ) {
        CabangModel cabang = cabangService.getCabangById(cabangId);
        ItemCabangModel item = itemCabangService.getItemById(itemId);
        String Id = cabang.getId().toString();
        String namaItem = item.getNama();
        model.addAttribute("Id",Id);
        model.addAttribute("namaItem",namaItem);
        itemCabangService.deleteItem(item);
        return "delete-item";

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

    @GetMapping("/cabang/addItem/{idCabang}")
    public String addItemCabang (@PathVariable Long idCabang, Model model) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        CabangModel cabang = cabangService.getCabangById(idCabang);
        List<ItemDetail> listItem =  itemRestService.getListItem();
        List<ItemCabangModel> listItemCabang = new ArrayList<>();
        if (currentPrincipalName.equals("[Kepala Retail]")) {

            cabang.setListItemCabang(listItemCabang);
            cabang.getListItemCabang().add(new ItemCabangModel());

            model.addAttribute("idCabang", idCabang);
            model.addAttribute("listItem", listItem);
            model.addAttribute("cabang", cabang);

            return "form-add-item";
        } else if (currentPrincipalName.equals("[Manager Cabang]")) {
            UserModel user = userService.findUserbyUsername(authentication.getName().toString());
            if (cabang.getPenanggungJawab().equals(user)){

                cabang.setListItemCabang(listItemCabang);
                cabang.getListItemCabang().add(new ItemCabangModel());

                model.addAttribute("idCabang", idCabang);
                model.addAttribute("listItem", listItem);
                model.addAttribute("cabang", cabang);

                return "form-add-item";
            }

        }
        return "Access-DeniedItem";


    }

    @PostMapping(value="/cabang/addItem/{idCabang}", params={"addRow"})
    public String addRowItem(@PathVariable Long idCabang, @ModelAttribute CabangModel cabang, Model model) throws JsonProcessingException {
        if (cabang.getListItemCabang() == null || cabang.getListItemCabang().size() == 0){
            cabang.setListItemCabang(new ArrayList<>());
        }
        List<ItemDetail> listItem =  itemRestService.getListItem();
        cabang.getListItemCabang().add(new ItemCabangModel());
        model.addAttribute("idCabang", idCabang);
        model.addAttribute("listItem", listItem);
        model.addAttribute("cabang", cabang);
        return "form-add-item";
    }

    @PostMapping(value="/cabang/addItem/{idCabang}", params={"deleteRow"})
    public String deleteRowItem(@PathVariable Long idCabang, @RequestParam("deleteRow") Integer row, @ModelAttribute CabangModel cabang, Model model) throws JsonProcessingException {
        final Integer indexRow = Integer.valueOf(row);
        cabang.getListItemCabang().remove(indexRow.intValue());
        List<ItemDetail> listItem =  itemRestService.getListItem();
        model.addAttribute("idCabang", idCabang);
        model.addAttribute("listItem", listItem);
        model.addAttribute("cabang", cabang);

        return "form-add-item";
    }

    @PostMapping(value="/cabang/addItem/{idCabang}", params={"save"})
    public String saveItem(@PathVariable Long idCabang, @ModelAttribute CabangModel cabang, Model model) throws JsonProcessingException {

        Boolean cek = itemRestService.cekStokItem(cabang.getListItemCabang());

        if(cek == true){
            CabangModel cabangLast = cabangService.getCabangById(idCabang);
            List<ItemCabangModel> listItemCabang = cabangLast.getListItemCabang();
            cabangLast.setListItemCabang(cabang.getListItemCabang());
            itemRestService.addItemToCabang(cabangLast, listItemCabang);
            return "addItemSuccess";
        } else {
            model.addAttribute("id", idCabang);
            return "outOfStok";
        }



    }



    @GetMapping(value = "/cabang/{idCabang}/req-update")
    private String formRequestItemStock(@PathVariable Long idCabang,
            Model model){

        List<ItemRequestDTO> listItemRequest = cabangRestService.getAllItem(idCabang);

        model.addAttribute("itemRequest", new ItemRequestDTO());
        model.addAttribute("idCabang", idCabang);
        model.addAttribute("listItemRequest", listItemRequest);
        return "form-req-itemstock";
    }

    @PostMapping(value = "/cabang/{idCabang}/req-update")
    private String requestItemStock(@PathVariable Long idCabang,
                                    @ModelAttribute("itemRequest") ItemRequestDTO itemRequest,
                                    Model model){
//        itemRequest.setUpdateStok(updateStok);
        List<ItemRequestDTO> listItemRequest = cabangRestService.getAllItem(idCabang);
        Mono<String> itemPost = itemRequestRestService.postRequestUpdateItem(idCabang, itemRequest);

        System.out.println(itemPost);

        model.addAttribute("post", true);
        model.addAttribute("namaItem", itemRequest.getNama());
        model.addAttribute("cabang", cabangService.getCabangById(idCabang));
        model.addAttribute("listItemRequest", listItemRequest);
        return "form-req-itemstock";

    }

    @GetMapping("/cabang/update/{noCabang}")
    public String updateCabangForm(
            @PathVariable Long noCabang,
            Model model

    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getAuthorities().toString();
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        if (currentPrincipalName.equals("[Kepala Retail]")) {
            model.addAttribute("cabang", cabang);
            return "form-update-cabang";
        }
        else if (currentPrincipalName.equals("[Manager Cabang]")) {
            UserModel user = userService.findUserbyUsername(authentication.getName().toString());
            if (cabang.getPenanggungJawab().equals(user)) {
                model.addAttribute("cabang", cabang);
                return "form-update-cabang";
            }
        }
        return "Access-DeniedItem";

    }

    @PostMapping("/cabang/update")
    public String updateCabangSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserModel penanggung_jawab = userService.findUserbyUsername(currentPrincipalName);
        int i=2;
        long status = i;
        cabang.setPenanggungJawab(penanggung_jawab);
        cabang.setStatus(status);
        cabangService.updateCabang(cabang);
        model.addAttribute("noCabang", cabang.getId());
        return "update-cabang";
    }

    @GetMapping("/cabang/delete/{noCabang}")
    public String deleteCabang(
            @PathVariable Long noCabang,
            Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        if (cabang.getListItemCabang().isEmpty()) {
            model.addAttribute("cabang",cabang);
            cabangService.deleteCabang(cabang);
            return "delete-cabang";
        }

        return "Access-DeniedDeleteCabang";
    }

}
