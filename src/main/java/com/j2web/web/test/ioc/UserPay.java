package com.j2web.web.test.ioc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户支付类，包含银行卡、支付宝支持
 * Created by wxj on 16-7-22.
 */
public class UserPay {

    private PayMethod payMethod;
    private HttpServletResponse response;

    void pay() throws IOException {
        response.getWriter().write(payMethod.payMoney());
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
