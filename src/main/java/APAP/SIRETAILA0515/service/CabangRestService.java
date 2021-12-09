package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;

import java.util.HashMap;
import java.util.List;

public interface CabangRestService {
    List<CabangModel> retrieveListCabang();

    List<HashMap<String, Object>> getAllItem();

}
