package APAP.SIRETAILA0515.repository;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCabangDb extends JpaRepository<ItemCabangModel, Long> {
    List<ItemCabangModel> findAllByCabang(CabangModel cabang);
    Optional<ItemCabangModel> findByUuidItem(String uuid);
}
