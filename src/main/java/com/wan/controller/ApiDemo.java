package com.wan.controller;


import com.wan.utils.CMD;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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


    @PostMapping("/file/upload")
    public String upload(@RequestParam Map<String, String> paramMap, @RequestParam("file") MultipartFile file) throws IOException {
        String url = paramMap.get("url");
        Map<String, Object> map=new HashMap<>();
        map = CMD.getParams(paramMap);
        map.put("file",file);
        Response response = given().multiPart((File) file).params(map).post(url);

        System.err.println("实际返回结果::" + response.asString());
        return response.asString();
    }


    @PostMapping("/getParams")
    public String getParams(@RequestParam Map<String, String> paramMap) throws IOException {
        return CMD.getParams(paramMap).toString();
    }

}
