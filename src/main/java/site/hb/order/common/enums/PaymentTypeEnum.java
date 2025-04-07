package site.hb.order.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {


    /**
     * 余额支付
     */
    BALANCE("1", "余额支付"),
    
    WECHAT("2", "微信支付"),

    MAYI("3", "支付宝支付"),

    OTHER("10000", "其他支付")
    ;


    private String code;

    private String name;

}
