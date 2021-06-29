package com.yanyv.workstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("plugin")
public class PluginController {

    @RequestMapping("tableLineWorkpiece")
    public String tableLineWorkpiece() {
        return "plugin_tableline_workpiece";
    }
    @RequestMapping("tableLineProcess")
    public String tableLineProcess() {
        return "plugin_tableline_process";
    }
    @RequestMapping("tableLineMachine")
    public String tableLineMachine() {
        return "plugin_tableline_machine";
    }
    @RequestMapping("tableLineWorkstation")
    public String tableLineWorkstation() {
        return "plugin_tableline_workstation";
    }
    @RequestMapping("tableLineWorkpieceInWorkstation")
    public String tableLineWorkpieceInWorkstation() {
        return "plugin_tableline_workpiece_in_workstation";
    }

}
