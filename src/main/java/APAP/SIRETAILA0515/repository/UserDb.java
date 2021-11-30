package APAP.SIRETAILA0515.repository;

import APAP.SIRETAILA0515.model.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository <UserModel, Long> {
    UserModel findByUsername (String username);
    UserModel findByNama(String nama);
}
