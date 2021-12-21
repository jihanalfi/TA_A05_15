package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;

import java.util.List;

public interface CabangService {
    void addCabang(CabangModel cabang);

    void updateCabang(CabangModel cabang);

    void deleteCabang(CabangModel cabang);

    List<CabangModel> getCabangList();

    CabangModel getCabangById(Long Id);

    CabangModel getCabangByNoCabang(Long noCabangq);
}