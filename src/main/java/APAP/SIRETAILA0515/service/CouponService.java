package APAP.SIRETAILA0515.service;

import APAP.SIRETAILA0515.repository.CabangDb;
import APAP.SIRETAILA0515.rest.CouponDetail;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import java.util.List;

public interface CouponService {
    List<CouponDetail> getCoupons();
    CouponDetail getCouponById(Integer Id);
}
