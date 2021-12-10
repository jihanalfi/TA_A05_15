package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.repository.CabangDb;
import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import APAP.SIRETAILA0515.rest.setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService {
    private final WebClient webClient;

    @Autowired
    private CabangDb cabangDb;

    public CabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(setting.Item).build();
    }

    @Override
    public List<CabangModel> retrieveListCabang() {
        return cabangDb.findAll();
    }

    @Override
    public List<ItemRequestDTO> getAllItem(Long idCabang) {
        HashMap<String, List<HashMap<String, Object>>> hashResponse = webClient.get().uri(setting.Item + "/api/item")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
        List<HashMap<String, Object>> listItem = hashResponse.get("result");

        List<ItemRequestDTO> listItemRequest = new ArrayList<>();
        for (HashMap<String, Object> item : listItem){
            ItemRequestDTO itemRequest = new ItemRequestDTO();
            itemRequest.setUuid(String.valueOf(item.get("uuid")));
            itemRequest.setNama(String.valueOf(item.get("nama")));
            itemRequest.setKategori(String.valueOf(item.get("kategori")));
            itemRequest.setIdCabang(idCabang);
            listItemRequest.add(itemRequest);
        }
        return listItemRequest;    }



}
