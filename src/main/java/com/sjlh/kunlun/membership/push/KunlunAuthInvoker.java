package com.sjlh.kunlun.membership.push;

import com.sjlh.hotel.common.annotation.InvokerMeta;
import com.sjlh.hotel.common.net.InvokerParam;
import com.sjlh.hotel.common.net.SimpleInvoker;
import lombok.extern.slf4j.Slf4j;

@InvokerMeta(id = "kunlun.auth")
@Slf4j
public class KunlunAuthInvoker extends SimpleInvoker {

    @Override
    public <T> T invoke(InvokerParam<T> param) {
        return super.invoke(param);
    }
}
