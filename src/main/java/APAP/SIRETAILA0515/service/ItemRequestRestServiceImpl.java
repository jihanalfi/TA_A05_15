package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import APAP.SIRETAILA0515.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemRequestRestServiceImpl implements ItemRequestRestService {
    private final WebClient webClient;

    public ItemRequestRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.itemRequest).build();
    }
    @Override
    public Mono<String> postRequestUpdateItem(Long idCabang, ItemRequestDTO itemRequest){

        return this.webClient.post()
                .uri("/rest/cabang/"+ idCabang +"/req-update")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(itemRequest)
                .retrieve()
                .bodyToMono(String.class);
    }
}
