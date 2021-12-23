package APAP.SIRETAILA0515.service;

//import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.UserModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    List<UserModel> getUserList();
    void deleteUser(String username);
    UserModel findUserbyUsername(String username);
    UserModel findUserbyName(String name);
//    boolean isMatch(String newPassword, String oldPassword);
    void setPassword(UserModel myUser, String newPassword);
    void updateUser(UserModel user);
}
