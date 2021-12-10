package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.rest.ItemDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemRestService {
    List<ItemDetail> getListItem() throws JsonProcessingException;
    void addItemToCabang(CabangModel cabang, List<ItemCabangModel> listExisting);
    ItemDetail getItemByUuid(String uuid);
    Mono<String> reduceItem(Long stok, String uuid);
    Boolean cekStokItem(List<ItemCabangModel> list);

}
