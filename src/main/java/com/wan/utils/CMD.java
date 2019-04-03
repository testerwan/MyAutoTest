package com.wan.utils;

import com.fdd.api.client.dto.BaseDTO;
import com.fdd.api.client.util.SignUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class CMD {
    public static String getFormatTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }

    public static BaseDTO getSign(String timeStamp, Map businessParams){



        BaseDTO bDto = new BaseDTO();
        bDto.setAppId("100000");
        bDto.setAppKey("vNTbK2CTNuQzm0ociYIEdbF2");
        bDto.setSignType("SHA256");
        bDto.setTimestamp(timeStamp);
        return SignUtil.sign(bDto, businessParams);
    }

}
