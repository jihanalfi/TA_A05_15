package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService {
    @Autowired
    CabangDb cabangDb;

    @Override
    public void addCabang(CabangModel cabang) {
        cabangDb.save(cabang);
    }

    @Override
    public void updateCabang(CabangModel cabang) {
        cabangDb.save(cabang);
    }

    @Override
    public void deleteCabang(CabangModel cabang) {
        cabangDb.delete(cabang);
    }

    @Override
    public List<CabangModel> getCabangList() {
        return cabangDb.findAll(Sort.by(Sort.Direction.ASC, "namaCabang"));
    }

    @Override
    public CabangModel getCabangById(Long Id) {
        Optional<CabangModel> cabang = cabangDb.findById(Id);
        if (cabang.isPresent()) {
            return cabang.get();
        }
        return null;
    }

}