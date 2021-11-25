package APAP.SIRETAILA0515.service;

//import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.UserModel;

import java.util.List;

public interface UserService {
    //    UserModel addUser(UserModel user);
//    public String encrypt(String password);
    List<UserModel> getUserList();
    void deleteUser(String username);
    UserModel findUserbyUsername(String username);
    //    boolean isMatch(String newPassword, String oldPassword);
    void setPassword(UserModel myUser, String newPassword);
}