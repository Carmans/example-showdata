package cn.itcast.controller;

import cn.itcast.service.AvgPvNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private AvgPvNum service;

    @RequestMapping("/index")
    public  String showIndex(){
        return  "index";
    }

    @RequestMapping(value = "/avgPvNum",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getPvAvg(){
        return  service.getByDates("20130919","20130925");
    }
}
