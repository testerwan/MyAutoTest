package com.wan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wan.utils.CMD;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static io.restassured.RestAssured.given;

@RestController
@Slf4j
public class ApiDemo {

    @PostMapping("/account/register")
    public String register(@RequestParam Map<String, String> paramMap) {


        String url = paramMap.get("url");

        Response response = given().contentType(ContentType.JSON).body(CMD.getParams(paramMap)).post(url);
        System.err.println("实际返回结果::" + response.asString());
        return response.asString();
    }


}
