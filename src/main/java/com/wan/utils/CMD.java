package com.wan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdd.api.client.dto.BaseDTO;
import com.fdd.api.client.util.SignUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CMD {
    public static String getFormatTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(timestamp);
    }

    public static BaseDTO getSign(String timeStamp, Map businessParams) {
        BaseDTO bDto = new BaseDTO();
        bDto.setAppId("100000");
        bDto.setAppKey("vNTbK2CTNuQzm0ociYIEdbF2");
        bDto.setSignType("SHA256");
        bDto.setTimestamp(timeStamp);
        return SignUtil.sign(bDto, businessParams);
    }

    public static JSONObject getParams(Map<String, String> paramMap) {

        String timeStamp = getFormatTimeStamp();

        Map<String, String> businessParams = new TreeMap<>();

        Map<String, String> params = new HashMap<>();

        paramMap.remove("url");
        for (String key : paramMap.keySet()) {
            businessParams.put(key, paramMap.get(key));
        }

        BaseDTO sign = getSign(timeStamp, businessParams);
        System.err.println("业务参数:" + businessParams);

        params.put("appId", "100000");
        params.put("signType", "SHA256");
        params.put("timestamp", timeStamp);
        params.put("sign", sign.getSign());
        params.put("bizContent", sign.getBizContent());

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(params));
        System.err.println("实际请求内容:" + jsonObject);

        return jsonObject;
    }


}
