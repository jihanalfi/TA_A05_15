package APAP.SIRETAILA0515.restcontroller;


import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.service.CabangRestService;
import APAP.SIRETAILA0515.service.ItemRequestRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CabangRestController {
    @Autowired
    private CabangRestService cabangRestService;

    @GetMapping(value = "/cabang/list")
    private List<CabangModel> retrieveListCabang(){
        return cabangRestService.retrieveListCabang();
    }

}
