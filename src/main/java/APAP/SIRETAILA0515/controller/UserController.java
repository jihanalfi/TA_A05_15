package APAP.SIRETAILA0515.controller;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.RoleModel;
import APAP.SIRETAILA0515.model.UserModel;
import APAP.SIRETAILA0515.service.RoleService;
import APAP.SIRETAILA0515.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }
}