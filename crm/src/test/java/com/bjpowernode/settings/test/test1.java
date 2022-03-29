package com.bjpowernode.settings.test;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.MD5Util;

public class test1 {

    public static void main(String[] args) {


        //验证失效时间
        //失效时间
        String expireTime = "2022-08-10 10:10:10";
        //当前系统时间
        String currentTime = DateTimeUtil.getSysTime();
        int count = expireTime.compareTo(currentTime);
        System.out.println(count);

        String LockState = "0";
        if("0".equals(LockState)){
            System.out.println("账号已锁定");
        }

        //浏览器端的ip地址
        String ip = "192.168.1.2";
        //允许访问的ip地址群
        String allowIps = "192.168.1.1,192.168.1.2";
        if(allowIps.contains(ip)){
            System.out.println("有效的IP地址，允许访问系统");
        }else{
            System.out.println("IP地址受限，请联系管理员");
        }

        String pwd = "sv030408";
        pwd = MD5Util.getMD5(pwd);
        System.out.println(pwd);

    }
}
