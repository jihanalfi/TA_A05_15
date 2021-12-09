package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.repository.CabangDb;
import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import APAP.SIRETAILA0515.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CabangRestServiceImpl implements CabangRestService {
    private final WebClient webClient;

    @Autowired
    private CabangDb cabangDb;

    public CabangRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.item).build();
    }

    @Override
    public List<CabangModel> retrieveListCabang() {
        return cabangDb.findAll();
    }

    @Override
    public List<HashMap<String, Object>> getAllItem() {
        HashMap<String, List<HashMap<String, Object>>> hashResponse = webClient.get().uri(Setting.item + "/api/item")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
        List<HashMap<String, Object>> listItem = hashResponse.get("result");

        return listItem;
    }



}
