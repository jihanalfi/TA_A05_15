package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.rest.ItemRequestDTO;
import reactor.core.publisher.Mono;

public interface ItemRequestRestService {
    Mono<String> postRequestUpdateItem(Long idCabang, ItemRequestDTO itemRequest);
}
