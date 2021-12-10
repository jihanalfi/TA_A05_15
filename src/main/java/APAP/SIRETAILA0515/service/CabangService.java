package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CabangService {
    void addCabang(CabangModel cabang);

    void updateCabang(CabangModel cabang);

    void deleteCabang(CabangModel cabang);

    List<CabangModel> getCabangList();

    CabangModel getCabangById(Long Id);
}