package com.j2web.web.test.ioc;

/** 银行卡和支付宝支付
 * Created by wxj on 16-7-22.
 */
public class BankAndAliPay implements PayMethod {

    @Override
    public String payMoney() {

        return "同时支持银行卡和支付宝支付";
    }
}
