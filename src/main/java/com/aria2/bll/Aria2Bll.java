package com.aria2.bll;

import com.aria2.toolkit.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * @author :  刘江
 * @date : 2018/8/7 16:40
 * @copyright : 迪爱斯信息技术股份有限公司
 */
@Service
public class Aria2Bll {

    @Autowired
    HttpClient httpClient;

    @Value("${aria2.path}")
    private String path;

    @Value("${aria2.jsonpc}")
    private String jsonpc;

    public String pushUrl(String url,String fileName){

        String query = "[{\"jsonrpc\":\"2.0\",\"method\":\"aria2.addUri\",\"id\":1,\"params\":[[\"" + url
                + "\"],{\"out\":\"" + fileName + "\",\"dir\":\"" + path
                + "\",\"split\":\"10\",\"max-connection-per-server\":\"10\",\"seed-ratio\":\"1.0\"}]}]";
         String res =  httpClient.sendPost(jsonpc,query);
         return  res;

    }
}
