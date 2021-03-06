package com.aria2.controller;

import com.aria2.bll.Aria2Bll;
import com.aria2.bll.CatchBll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * @author :  刘江
 * @date : 2018/8/7 16:24
 * @copyright : 迪爱斯信息技术股份有限公司
 */
@CrossOrigin
@RequestMapping("/aria2")
@RestController
public class Aria2Controller {

    @Autowired
    Aria2Bll aria2Bll;

    @Autowired
    CatchBll catchBll;

    @Value("${aria2.path}")
    private String path;

    @Value("${aria2.jsonpc}")
    private String jsonpc;

    @PostMapping("/push")
    public String push(@RequestBody Map<String, String> file){
      String url =   file.get("url");
      String fileName = file.get("name");
      return   aria2Bll.pushUrl(jsonpc,path,url,fileName);
    }


    @GetMapping("/catch")
    public String catchUrl(){
        catchBll.CatchUrl();
        return  "任务已经启动";
    }


}
