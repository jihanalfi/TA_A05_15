package APAP.SIRETAILA0515.rest;
import APAP.SIRETAILA0515.model.CabangModel;

public class CabangDTO {
    
    public String nama_cabang;
    public String alamat_cabang;
    public String no_telepon_cabang;
    public Long ukuran_cabang;
    public Long status;

    public CabangModel convertToCabang(){
        CabangModel cabang = new CabangModel();
        cabang.setNamaCabang(nama_cabang);
        cabang.setAlamatCabang(alamat_cabang);
        cabang.setNoTeleponCabang(no_telepon_cabang);
        cabang.setUkuranCabang(ukuran_cabang);
        cabang.setStatus(0L); 
        return cabang;
    }

    public CabangModel convertToCabang(CabangModel cabang){
        cabang.setNamaCabang(nama_cabang);
        cabang.setAlamatCabang(alamat_cabang);
        cabang.setNoTeleponCabang(no_telepon_cabang);
        cabang.setUkuranCabang(ukuran_cabang);
        cabang.setStatus(0L); 
        return cabang;
    }
}
