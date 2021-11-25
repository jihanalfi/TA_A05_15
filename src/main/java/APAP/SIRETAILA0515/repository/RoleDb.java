package APAP.SIRETAILA0515.repository;
import APAP.SIRETAILA0515.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel,Long> {

}
