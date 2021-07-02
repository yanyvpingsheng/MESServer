package com.yanyv.workstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面转发控制器
 */
@Controller
public class PageController {

    /**
     * 主页
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 登录
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 注册
     */
    @RequestMapping("register")
    public String register(){
        return "register";
    }

    /**
     * 工件管理
     */
    @RequestMapping("workpiece")
    public String workpiece() {
        return "workpiece";
    }

    /**
     * 工序管理
     */
    @RequestMapping("process")
    public String process() {
        return "process";
    }

    /**
     * 机器管理
     */
    @RequestMapping("machine")
    public String machine() {
        return "machine";
    }

    /**
     * 车间模型管理
     */
    @RequestMapping("workstation")
    public String workstation() {
        return "workstation";
    }

    /**
     * 车间调度系统页面
     */
    @RequestMapping("system")
    public String system() {
        return "system";
    }

}
