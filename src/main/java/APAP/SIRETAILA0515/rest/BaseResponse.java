package APAP.SIRETAILA0515.rest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse<T> {
    private int status;
    private String message;
    private T result;
}
