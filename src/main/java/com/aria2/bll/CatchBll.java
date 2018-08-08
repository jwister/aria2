package com.aria2.bll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/*
 * @author :  刘江
 * @date : 2018/8/8 12:56
 * @copyright : 迪爱斯信息技术股份有限公司
 */
@Service
public class CatchBll {
    @Value("${site.url}")
    private String url;
    @Value("${aria2.jsonpc}")
    private String jsonpc;

    @Value("${aria2.path}")
    private String path;

    public void CatchUrl(){
        try{
            Document ydoc = Jsoup.connect(url + "/recent/").get();
            Elements navList = ydoc.getElementsByClass("pagination pagination-lg");
            int pagerLength = navList.last().childNodeSize() - 2;
            int pageNumCount = Integer
                    .parseInt(navList.first().childNode(pagerLength).childNode(0).childNode(0).outerHtml());
            int threadcount = 1;
            for (int i = 0; i < threadcount; i++) {
                if (i == 0) {
                    CatchThread cuth = new CatchThread(1, (pageNumCount / threadcount) * (i + 1), url,jsonpc,path);
                    cuth.start();
                } else {
                    CatchThread cuth = new CatchThread((pageNumCount / threadcount) * i, (pageNumCount / threadcount) * (i + 1),
                            url,jsonpc,path);
                    cuth.start();
                }
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }
    }




}
