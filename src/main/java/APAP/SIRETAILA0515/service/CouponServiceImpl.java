package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.rest.CouponDetail;
import APAP.SIRETAILA0515.rest.setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class CouponServiceImpl implements CouponService {


    private final WebClient webClient;

    public CouponServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(setting.Coupon).build();
    }

    @Override
    public List<CouponDetail> getCoupons() {
                Flux<CouponDetail> coupon = this.webClient.get().uri("/api/coupon")
                .retrieve()
                .bodyToFlux(CouponDetail.class);
//        return this.webClient.get().uri("api/coupon")
//                .retrieve()
//                .bodyToMono(CouponDetail.class);
        return coupon.collectList().block();
    }

//    @Override
//    public CouponDetail getCoupons() {
//        Flux<CouponDetail> coupon = this.webClient.get().uri("/api/coupon")
//                .retrieve()
//                .bodyToFlux(CouponDetail.class);
////        return this.webClient.get().uri("api/coupon")
////                .retrieve()
////                .bodyToMono(CouponDetail.class);
//        return coupon.collectList().block();
//    }


}
