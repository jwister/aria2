package com.aria2.bll;
/*
 * @author :  刘江
 * @date : 2018/8/8 13:17
 * @copyright : 迪爱斯信息技术股份有限公司
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CatchThread extends Thread{
    private int start;
    private int end;
    private String url;
    private String jsonpc;
    private String path;



    public CatchThread(int start, int end, String url ,String jsonpc,String path) {
        this.start = start;
        this.end = end;
        this.url = url;
        this.jsonpc = jsonpc;
        this.path = path;

    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();// 记录开始时间
        long endTime = System.currentTimeMillis();// 记录结束时间
        float excTime = (float) (endTime - startTime) / 1000;
        Aria2Bll aria2Bll = new Aria2Bll();
        for (int i = start; i < end; i++) {
            Document pagedoc = null;
            System.out.println("当前采集地址：" + url + "/recent/" + i);
            try {
                if (i == 1) {
                    pagedoc = Jsoup.connect(url + "/recent/").get();
                } else {
                    pagedoc = Jsoup.connect(url + "/recent/" + i).get();
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Elements videoList = pagedoc.select(".thumbnail");
            ArrayList<String> dirlst = new ArrayList<String>();
            for (Element link : videoList) {
                String linkHref = link.attr("href");
                dirlst.add(linkHref);
            }
            endTime = System.currentTimeMillis();// 记录结束时间
            excTime = (float) (endTime - startTime) / 1000;

            System.out.println("加载完成，执行时间：" + excTime + "s");

            HashMap<String, String> hm = new HashMap<String, String>();
            System.out.println("准备抓取MP4链接");
            startTime = System.currentTimeMillis();// 记录开始时间

            for (String detail : dirlst) {
              int numActive =  aria2Bll.getActiveNum(jsonpc);
                while (numActive>=3) {
                    System.out.println("当前下载数量已到达3个！暂停新增任务");
                  try {
                      Thread.sleep(3000);
                      numActive =  aria2Bll.getActiveNum(jsonpc);
                  }catch (Exception e){
                      e.printStackTrace();
                  }
                }

                Document detaildoc = null;
                try {
                    detaildoc = Jsoup.connect(url + detail).get();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String title = detaildoc.getElementsByTag("h1").html();
                Elements detailList = detaildoc.getElementsByAttributeValue("label", "360p");
                for (Element link : detailList) {
                    String linkHref = link.attr("src");
                    linkHref = linkHref.replace(url, "");
                    String num =linkHref.substring(linkHref.lastIndexOf('/')+1,linkHref.lastIndexOf('.'));
                    try {
                        String sql = "insert into av(title,url,num) values(?,?,?)";
                        System.out.println("准备抓取MP4链接");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // db.closeconnection();
                    }
                    System.out.println(title + ":" + linkHref);
                    aria2Bll.pushUrl(jsonpc,path,linkHref,title+".mp4");

                    hm.put(title, linkHref);
                }
            }
        }

        endTime = System.currentTimeMillis();// 记录结束时间
        excTime = (float) (endTime - startTime) / 1000;
        System.out.println("抓取完成，执行时间：" + excTime + "s");

    }
}