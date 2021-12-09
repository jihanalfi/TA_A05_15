package APAP.SIRETAILA0515.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)

public class CouponDetail {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("couponCode")
    private String couponCode;

    @JsonProperty("couponName")
    private String couponName;

    @JsonProperty("discountAmount")
    private Float discountAmount;

    @JsonProperty("expiryDate")
    private Date expiryDate;

//    private String pair;

}
