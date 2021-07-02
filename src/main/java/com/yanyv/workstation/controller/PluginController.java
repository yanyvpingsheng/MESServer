package com.yanyv.workstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * html插入片段控制器
 */
@Controller
@RequestMapping("plugin")
public class PluginController {

    /**
     * 工件列表item
     */
    @RequestMapping("tableLineWorkpiece")
    public String tableLineWorkpiece() {
        return "plugin_tableline_workpiece";
    }

    /**
     * 工序列表item
     */
    @RequestMapping("tableLineProcess")
    public String tableLineProcess() {
        return "plugin_tableline_process";
    }

    /**
     * 机器列表item
     */
    @RequestMapping("tableLineMachine")
    public String tableLineMachine() {
        return "plugin_tableline_machine";
    }

    /**
     * 车间模型item
     */
    @RequestMapping("tableLineWorkstation")
    public String tableLineWorkstation() {
        return "plugin_tableline_workstation";
    }

    /**
     * 车间模型页的工件item
     */
    @RequestMapping("tableLineWorkpieceInWorkstation")
    public String tableLineWorkpieceInWorkstation() {
        return "plugin_tableline_workpiece_in_workstation";
    }

}
