package com.aria2.bll;

import com.alibaba.fastjson.JSONObject;
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


    HttpClient httpClient = new HttpClient();


    public String pushUrl(String rcpurl,String path,String url,String fileName){
        String query = "[{\"jsonrpc\":\"2.0\",\"method\":\"aria2.addUri\",\"id\":1,\"params\":[[\"" + url
                + "\"],{\"out\":\"" + fileName + "\",\"dir\":\"" + path
                + "\",\"split\":\"10\",\"max-connection-per-server\":\"10\",\"seed-ratio\":\"1.0\"}]}]";
        String res =  httpClient.sendPost(rcpurl,query);
        return  res;
    }

    //获取当前正在下载的任务数
    public int getActiveNum(String rcpUrl){
        System.out.println(rcpUrl);
        String query = "{\"jsonrpc\":\"2.0\",\"method\":\"aria2.getGlobalStat\",\"id\":1,\"params\":[]}";


        JSONObject jsonObject = JSONObject.parseObject(httpClient.sendPost(rcpUrl,query));
        String num =  JSONObject.parseObject(jsonObject.get("result").toString()).get("numActive").toString();
      return  Integer.parseInt(num);
    }
}
