package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.model.ItemCabangModel;
import APAP.SIRETAILA0515.rest.ItemDetail;
import APAP.SIRETAILA0515.rest.setting;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService  {
    @Qualifier("itemCabangServiceImpl")
    @Autowired
    private ItemCabangService itemCabangService;

    private final WebClient webClient;
    List<ItemDetail> listItem = new ArrayList<>();

    public ItemRestServiceImpl(WebClient.Builder webclientBuilder){
        this.webClient = webclientBuilder.baseUrl(setting.Item
        ).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }


    @Override
    public List<ItemDetail> getListItem() throws JsonProcessingException {
        JsonNode result = this.webClient.get().uri("/api/item").retrieve().bodyToMono(JsonNode.class).block().get("result");
        ObjectMapper mapper = new ObjectMapper();
        listItem = Arrays.asList(mapper.treeToValue(result,ItemDetail[].class));
        return listItem;
    }

    @Override
    public void addItemToCabang(CabangModel cabang,List<ItemCabangModel> listExisting){
        Boolean cek = false;
        if (listExisting.size() != 0){
            for (ItemCabangModel x:cabang.getListItemCabang()) {
                cek = cekContains(listExisting, x.getUuidItem());
                if (cek){
                    ItemCabangModel item = itemCabangService.findByUuidAndCabang(x.getUuidItem(),cabang);
                    item.setStok(item.getStok() + x.getStok());
                    Mono<String> post =  reduceItem(x.getStok(), item.getUuidItem());
                    post.block().toString();
                    itemCabangService.addItemCabang(item);
                } else {

                    ItemDetail item = getItemByUuid(x.getUuidItem());
                    x.setCabang(cabang);
                    x.setNama(item.getNama());
                    x.setHarga(item.getHarga());
                    x.setKategori(item.getKategori());
                    Mono<String> post =  reduceItem(x.getStok(), x.getUuidItem());
                    post.block().toString();
                    itemCabangService.addItemCabang(x);
                }
            }

        } else {
            for (ItemCabangModel x:cabang.getListItemCabang()) {
                ItemDetail item = getItemByUuid(x.getUuidItem());
                x.setCabang(cabang);
                x.setNama(item.getNama());
                x.setHarga(item.getHarga());
                x.setKategori(item.getKategori());
                Mono<String> post =  reduceItem(x.getStok(), x.getUuidItem());
                post.block().toString();
                itemCabangService.addItemCabang(x);
            }
        }


    }

    @Override
    public ItemDetail getItemByUuid(String uuid){
        JsonNode result = this.webClient.get().uri("/api/item/" + uuid).retrieve().bodyToMono(JsonNode.class).block().get("result");
        ObjectMapper mapper = new ObjectMapper();
        ItemDetail item = mapper.convertValue(result, ItemDetail.class);
        return item;

    }

    @Override
    public Mono<String> reduceItem(Long stok, String uuid){
        Map body = new HashMap<>();
        ItemDetail itemDetail = getItemByUuid(uuid);

        Long newStok = itemDetail.getStok() - stok;
        body.put("stok", newStok);
        return this.webClient.put().uri("/api/item/" + uuid)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Boolean cekStokItem(List<ItemCabangModel> list){
        Boolean cek = true;
        for (ItemCabangModel x:list
        ) {
            ItemDetail item = getItemByUuid(x.getUuidItem());
            if (x.getStok() <= item.getStok()){
                cek = true;
            } else {
                cek = false;
            }
        }
        return cek;
    }

    public Boolean cekContains(List<ItemCabangModel> listExisting, String uuid){
        for (ItemCabangModel x:listExisting
        ) {
            if (x.getUuidItem().equals(uuid)){
                return true;
            }
        }
        return false;
    }

}
