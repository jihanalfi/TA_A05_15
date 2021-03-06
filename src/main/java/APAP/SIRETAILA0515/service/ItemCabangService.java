package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;

import java.util.List;
import java.util.Optional;

public interface ItemCabangService {
    List<ItemCabangModel> retrieveItemByCabang(CabangModel cabang);
    ItemCabangModel getItemById(Long id);
    ItemCabangModel findByUuidAndCabang(String uuid, CabangModel cabang);
    void addItem(ItemCabangModel item);
//    ItemCabangModel findByUuid(String uuid);
    void addItemCabang(ItemCabangModel cabangModel);
    void deleteItem(ItemCabangModel cabangModel);
}
