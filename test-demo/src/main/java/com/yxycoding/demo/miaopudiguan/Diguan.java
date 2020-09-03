package com.yxycoding.demo.miaopudiguan;/*
 * @author yangxy
 * @date 2020/8/17 14:57
 */

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Diguan  {


    public static void main(String[] args) {


        Diguan diguan = new Diguan();
        diguan.qryList();
    }

    public void qryList(){

        RestTemplate restTemplate = new RestTemplate();
        String logUrl ="http://www.tscsmart.com/API/user/login?name=sxtxl001&pwd=654321";

        BaseRest forLogin;
        try {
             forLogin = restTemplate.getForObject(logUrl, BaseRest.class);
        }catch (Exception e ){
            log.error("",e);
            return;
        }

        String url ="http://www.tscsmart.com/API/App/checkEquipment?uid="+forLogin.getId()+"&tokey="+forLogin.getMsg();
        Shebei[] forObject = restTemplate.getForObject(url, Shebei[].class);
        log.info("");
        log.info(String.valueOf(forObject.length));

//        try {
//            for (Shebei shebei : forObject) {
//                Db.use().insert(
//                        Entity.create("diguan_titu").parseBean(shebei)
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }


}
