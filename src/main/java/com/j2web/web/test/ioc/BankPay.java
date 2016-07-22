package com.j2web.web.test.ioc;

/**
 * 银行卡支付
 * Created by wxj on 16-7-22.
 */
public class BankPay implements PayMethod {


    @Override
    public String payMoney() {

        return "支持银行卡支付";
    }
}
