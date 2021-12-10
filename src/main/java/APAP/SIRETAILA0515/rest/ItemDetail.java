package APAP.SIRETAILA0515.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemDetail {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("nama")
    private String nama;

    @JsonProperty("harga")
    private Long harga;

    @JsonProperty("stok")
    private Long stok;

    @JsonProperty("kategori")
    private String kategori;
}

