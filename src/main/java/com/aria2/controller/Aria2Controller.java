package com.aria2.controller;

import com.aria2.bll.Aria2Bll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
 * @author :  刘江
 * @date : 2018/8/7 16:24
 * @copyright : 迪爱斯信息技术股份有限公司
 */

@RequestMapping("/aria2")
@RestController
public class Aria2Controller {

    @Autowired
    Aria2Bll aria2Bll;

    @PostMapping("/push")
    public String push(@RequestBody Map<String, String> person){
      String url =   person.get("name");
      String fileName = person.get("fileName");
      return   aria2Bll.pushUrl(url,fileName);
    }

}
