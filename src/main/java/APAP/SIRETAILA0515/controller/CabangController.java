package APAP.SIRETAILA0515.controller;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.model.UserModel;
import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import APAP.SIRETAILA0515.service.*;
import APAP.SIRETAILA0515.service.UserService;
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
    @Qualifier("cabangServiceImpl")

    @Autowired
    private CabangService cabangService;

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
        UserModel penanggungJawab = userService.findUserbyName(currentPrincipalName);
        int i=2;
        long status = i;
        cabang.setPenanggungJawab(penanggungJawab);
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

    private List<ItemRequestDTO> getAllItem(Long idCabang){
        List<HashMap<String, Object>> listItem = cabangRestService.getAllItem();
        List<ItemRequestDTO> listItemRequest = new ArrayList<>();
        for (HashMap<String, Object> item : listItem){
            ItemRequestDTO itemRequest = new ItemRequestDTO();
            itemRequest.setUuid(String.valueOf(item.get("uuid")));
            itemRequest.setNama(String.valueOf(item.get("nama")));
            itemRequest.setKategori(String.valueOf(item.get("kategori")));
            itemRequest.setIdCabang(idCabang);
            listItemRequest.add(itemRequest);
        }
        return listItemRequest;
    }

    @GetMapping(value = "/cabang/{idCabang}/req-update")
    private String formRequestItemStock(@PathVariable Long idCabang,
            Model model){

        List<ItemRequestDTO> listItemRequest = getAllItem(idCabang);

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
        List<ItemRequestDTO> listItemRequest = getAllItem(idCabang);
        Mono<String> itemPost = itemRequestRestService.postRequestUpdateItem(idCabang, itemRequest);

        System.out.println(itemPost);

        model.addAttribute("post", true);
        model.addAttribute("namaItem", itemRequest.getNama());
        model.addAttribute("cabang", cabangService.getCabangById(idCabang));
        model.addAttribute("listItemRequest", listItemRequest);
        return "form-req-itemstock";

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