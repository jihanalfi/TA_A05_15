package APAP.SIRETAILA0515.service;

//import apap.tutorial.emsidi.model.CabangModel;
import APAP.SIRETAILA0515.model.UserModel;
import APAP.SIRETAILA0515.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getUserList() {
        return userDb.findAll();
    }

    @Override
    public void deleteUser(String username){
        UserModel user = userDb.findByUsername(username);
        userDb.delete(user);
    }

    @Override
    public UserModel findUserbyUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public UserModel findUserbyName(String name){
        return userDb.findByNama(name);
    }

//    @Override
//    public boolean isMatch(String newPassword, String oldPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(newPassword, oldPassword);
//    }

    @Override
    public void setPassword(UserModel myUser, String newPassword) {
        myUser.setPassword(newPassword);
    }

    @Override
    public UserModel updateUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }
}