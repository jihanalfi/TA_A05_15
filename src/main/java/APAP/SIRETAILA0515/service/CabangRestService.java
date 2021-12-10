package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.rest.CabangDTO;
import APAP.SIRETAILA0515.rest.ItemRequestDTO;

import java.util.HashMap;
import java.util.List;

public interface CabangRestService {
    List<CabangModel> retrieveListCabang();

    List<ItemRequestDTO> getAllItem(Long idCabang);

    CabangModel createCabang(CabangDTO cabang);
}
