package APAP.SIRETAILA0515.repository;

import APAP.SIRETAILA0515.model.CabangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabangDb extends JpaRepository<CabangModel, Long> {
}