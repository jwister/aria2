package com.aria2.controller;

import com.aria2.bll.Aria2Bll;
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

    @PostMapping("/push")
    public String push(@RequestBody Map<String, String> file){
      String url =   file.get("url");
      String fileName = file.get("name");
      return   aria2Bll.pushUrl(url,fileName);
    }

}
