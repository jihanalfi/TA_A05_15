package APAP.SIRETAILA0515.restcontroller;


import APAP.SIRETAILA0515.model.CabangModel;
import APAP.SIRETAILA0515.service.CabangRestService;
import APAP.SIRETAILA0515.rest.BaseResponse;
import APAP.SIRETAILA0515.rest.CabangDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CabangRestController {
    @Autowired
    private CabangRestService cabangRestService;

    @GetMapping(value = "/cabang/list")
    private BaseResponse<List<CabangModel>> retrieveListCabang(){
        BaseResponse<List<CabangModel>> response = new BaseResponse<>();
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(cabangRestService.retrieveListCabang());
        return response;
    }

    @PostMapping(value="/cabang/bikincabang")
    private BaseResponse<CabangModel> bikinCabangSubmit(@Valid @RequestBody CabangModel cabang,
    BindingResult bindingResult){
        BaseResponse<CabangModel> response = new BaseResponse<>();
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request Body has invalid type or missing field");
        } else {
            try {
                CabangModel cabangBaru = cabangRestService.bikinCabang(cabang);
                cabangBaru.setStatus(0L)
                response.setStatus(201);
                response.setMessage("created");
                response.setResult(cabangBaru);
            } catch (Exception e) {
                response.setStatus(400);
                response.setMessage(e.toString());
                response.setResult(null);
            }
            return response;
        }
    }
}
