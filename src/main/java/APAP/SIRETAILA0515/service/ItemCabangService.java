package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;

import java.util.List;

public interface ItemCabangService {
    List<ItemCabangModel> retrieveItemByCabang(CabangModel cabang);
}
