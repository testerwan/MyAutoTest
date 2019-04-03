package com.wan.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdd.api.client.dto.BaseDTO;
import com.fdd.api.client.util.SignUtil;
import com.wan.utils.CMD;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

@RestController
@Slf4j
public class demo {

    @GetMapping("/test")
    public String test1(){
        return "success";
    }

    @PostMapping("/account/register")
    public String test2(String type,String account,String url){

        String timeStamp = CMD.getFormatTimeStamp();


        Map<String, String> businessParams = new TreeMap<>();
        Map<String,String> params=new HashMap();

        businessParams.put("type",type);
        businessParams.put("account",account);

        BaseDTO sign = CMD.getSign(timeStamp,businessParams);

        params.put("appId","100000");
        params.put("signType","SHA256");
        params.put("timestamp",timeStamp);
        params.put("sign",sign.getSign());
        params.put("bizContent",sign.getBizContent());


        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(params));
        Response response = given().contentType(ContentType.JSON).body(jsonObject).post(url);
        return response.asString();
    }

}
