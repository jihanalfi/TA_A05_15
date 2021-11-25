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

//    @PostMapping(value = "/add")
//    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
//        userService.addUser(user);
//        model.addAttribute("user", user);
//        return "redirect:/";
//    }

//    @GetMapping("/viewall")
//    public String listUser(Model model) {
//        List<UserModel> listUser = userService.getUserList();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getAuthorities().toString();
//        if (currentPrincipalName.equals("[Admin]")){
//            model.addAttribute("listUser",listUser);
//            return  "viewall-user";
//        }
//        return "Acces-Denied";
//    }

//    @RequestMapping("/delete/{username}")
//    public String deleteUser(@PathVariable String username, Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String role = auth.getAuthorities().toString().replace("[", "").replace("]","");
//        if (role.equalsIgnoreCase("ADMIN")){
//            userService.deleteUser(username);
//            return "viewall-user";
//        }
//        return "redirect:/";
//    }

//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    public String changePassword(@ModelAttribute UserModel userModel, String newPassword, String confPassword, Model model){
//        UserModel myUser = userService.findUserbyUsername(userModel.getUsername());
//
//        if (userService.isMatch(userModel.getPassword(), myUser.getPassword() )){
//            if (newPassword.equals(confPassword)){
//                userService.setPassword(myUser, newPassword);
//                userService.addUser(myUser);
//                model.addAttribute("message", "password berhasil diubah");
//            }else {
//                model.addAttribute("message", "password tidak sama");
//            }
//        }else {
//            model.addAttribute("message", "password salah");
//        }
//        return "home";
//    }


}
