package com.yanyv.workstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 页面转发
@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("workpiece")
    public String workpiece() {
        return "workpiece";
    }

    @RequestMapping("process")
    public String process() {
        return "process";
    }

    @RequestMapping("machine")
    public String machine() {
        return "machine";
    }

    @RequestMapping("workstation")
    public String workstation() {
        return "workstation";
    }

    @RequestMapping("system")
    public String system() {
        return "system";
    }

    @RequestMapping("gantt0")
    public String gantt0() {
        return "gantt0";
    }
}
