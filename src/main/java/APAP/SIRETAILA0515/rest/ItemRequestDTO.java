package APAP.SIRETAILA0515.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequestDTO {
    public String uuid;
    public String nama;
    public Long updateStok = null;
    public String kategori;
    public Long idCabang;
}
