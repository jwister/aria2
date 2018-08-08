package com.aria2.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * @author :  刘江
 * @date : 2018/8/8 12:35
 * @copyright : 迪爱斯信息技术股份有限公司
 */
@Component
public class CatchUrlTask {

    //定时抓取url
    @Scheduled(cron = "0 59 23 ? * *" )
    public void CatchAction(){


    }
}
