package com.wan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fdd.api.client.dto.BaseDTO;
import com.fdd.api.client.util.SignUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static JSONObject getParams2(Map<String, String> paramMap) {

        String timeStamp = getFormatTimeStamp();

        Map<String, String> businessParams = new TreeMap<>();

        Map<String, String> params = new LinkedHashMap();

        paramMap.remove("url");
        if (paramMap.containsKey("file")) {
            paramMap.remove("file");

        }
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

        System.out.println("params" + params);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(params));
        System.out.println("实际请求内容:" + jsonObject);

        return jsonObject;
    }

    public static JSONObject getParams(Map<String, String> paramMap) {

        String timeStamp = getFormatTimeStamp();

        Map<String, String> businessParams = new TreeMap<>();

        Map<String, String> params = new LinkedHashMap();


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
        System.out.println(params);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(params));
        return jsonObject;
    }


    public static void inputstreamtofile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    public static File MultipartFile2File(MultipartFile file) throws IOException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        File upload = File.createTempFile(uuid, prefix);
        // MultipartFile to File
        file.transferTo(upload);
        return upload;
    }

}
