package com.yanyv.workstation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yanyv.workstation.entity.*;
import com.yanyv.workstation.entity.Process;
import com.yanyv.workstation.heredity.DNA;
import com.yanyv.workstation.heredity.Heredity;
import com.yanyv.workstation.heredity.RRNA;
import com.yanyv.workstation.service.*;
import com.yanyv.workstation.util.Email;
import com.yanyv.workstation.vo.MachineVo;
import com.yanyv.workstation.vo.ProcessVo;
import com.yanyv.workstation.vo.WorkpieceVo;
import com.yanyv.workstation.vo.WorkstationVo;
import com.yanyv.workstation.view.Canvas;
import org.apache.commons.mail.EmailException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

/**
 * 网络接口控制器
 */
@Controller
@RequestMapping("api")
public class ApiController {
    @Autowired
    Email emailUtil;
    @Autowired
    CodeService codeService;
    @Autowired
    UserService userService;
    @Autowired
    WorkpieceService workpieceService;
    @Autowired
    ProcessService processService;
    @Autowired
    MachineService machineService;
    @Autowired
    WorkstationService workstationService;

    // 返回结果json数据
    JSONObject result = new JSONObject();

    /**
     * 获取图片验证码 向resp中输出生成的验证码图片
     */
    @RequestMapping("code-img")
    public void getCodeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-cache");
        //在内存中创建图像，设置图像的宽和高
        int width = 60, height = 30;
        //实例化java.awt.image.BufferedImage,作用是访问图像数据缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   //第三个参数：使用的颜色模式为RGB模式
        //获得画笔
        Graphics g = image.getGraphics();
        //设置背景颜色 RGB
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, width, height);
        //取随机产生的验证码（4位数字）
        Random rnd = new Random();
        int randNum = rnd.nextInt(8999) + 1000;  //返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
        String ranStr = String.valueOf(randNum);
        //将验证码存到session中
        request.getSession().setAttribute("randomString", ranStr);
        //将验证码显示到图像中
        g.setColor(Color.red);
        g.setFont(new Font("", Font.PLAIN, 20));  //名称   样式    磅值大小
        g.drawString(ranStr, 10, 22);
        //随机产生100个干扰点，使图像中的验证码不易被其他程序检测到
        for (int i = 0; i < 100; i++) {
            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }
        //输出图像到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    /**
     * 判断图片验证码是否正确 传入验证码
     */
    @RequestMapping("is-code-img-right")
    @ResponseBody
    public String isCodeImgRight(HttpServletRequest request, String code) {

        result.clear();
        String randomString = (String) request.getSession().getAttribute("randomString");
        result.put("data", randomString.equals(code));
        return result.toString();
    }

    /**
     * 发送邮箱验证码API
     */
    @RequestMapping("sendemail")
    @ResponseBody
    public String apiSendEmail(String email) throws Exception {
        System.out.println("in email api");
        System.out.println("email is " + email);
        result.clear();
        if (email != null && !"".equals(email)) {
            int pin;
            User user = userService.queryByEmail(email);
            if (user == null) {
                Code code = codeService.queryByEmail(email);
                if (code != null) {
                    pin = code.getCode();
                } else {
                    pin = getPin();
                    code = new Code();
                    code.setEmail(email);
                    code.setCode(pin);
                    codeService.save(code);
                }
                new Thread(() -> {

                    try {
                        emailUtil.sendEmail(email, pin + "");
                        System.out.println("发送了");
                    } catch (EmailException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }).start();

                result.put("data", "验证码已发送，五分钟内有效，请注意查收");
            } else {
                result.put("data", "该邮箱已注册，若忘记密码请访问找回页面");
            }

        } else System.out.println("no email");
        return result.toString();
    }

    /**
     * 随机生成验证码
     */
    private int getPin() {
        int pin = (int) (Math.random() * 9000 + 1000);
        return pin;
    }

    /**
     * 登录API
     */
    @RequestMapping("login")
    @ResponseBody
    public String apiLogin(HttpServletResponse response, User user, boolean useCookies) throws Exception {
        result.clear();

        User realUser;
        boolean loginSuccess;
        if (user.getUid() != 0) {
            // uid登录
            realUser = userService.get(user.getUid());
        } else if (user.getEmail() != null) {
            // 邮箱登录
            realUser = userService.queryByEmail(user.getEmail());
        } else {
            // 非法请求
            result.put("data", false);
            result.put("msg", "非法请求!");
            return result.toString();
        }
        //System.out.println(user);
        //System.out.println(realUser);
        if (realUser != null) {
            loginSuccess = realUser.getPass().equals(user.getPass());
            if (loginSuccess) {
                Cookie cookie = new Cookie("uid", user.getUid() + "");
                cookie.setPath("/");
                if (useCookies) {
                    cookie.setMaxAge(864000);
                }
                response.addCookie(cookie);
            } else {
                result.put("msg", "账号或密码错误!");
            }
        } else {
            loginSuccess = false;
            result.put("msg", "用户不存在!");
        }
        result.put("data", loginSuccess);
        return result.toString();
    }

    /**
     * 退出登录API
     */
    @RequestMapping("logout")
    public void apiLogout(HttpServletResponse response) throws Exception {
        Cookie cookie = new Cookie("uid", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/");
    }

    /**
     * 注册API
     */
    @RequestMapping("register")
    @ResponseBody
    public String apiRegister(User user, String surepass, int code) {
        result.clear();

        System.out.println(user);
        System.out.println(surepass);
        System.out.println(code);

        Code realCode = codeService.queryByEmail(user.getEmail());
        if (realCode == null) {
            result.put("data", false);
            result.put("msg", "尚未发送验证码!");
        } else if (realCode.getCode() != code) {
            result.put("data", false);
            result.put("msg", "验证码错误!");
        } else {
            long id = userService.save(user);
            result.put("data", true);
            result.put("msg", "注册成功");
            result.put("uid", id);
        }
        return result.toString();
    }

    /**
     * 获取账号信息API
     */
    @RequestMapping("get-acc-info")
    @ResponseBody
    public User apiGetAccountInfo(User u) {
        result.clear();

        User user = null;
        if (u.getUid() != 0) {
            // uid查询
            user = userService.get(u.getUid());
        } else if (u.getEmail() != null) {
            // 邮箱查询
            user = userService.queryByEmail(u.getEmail());
        }
        return user;
    }


    /**
     * 保存或更新工件API
     */
    @RequestMapping("workpieceSaveOrUpdate")
    @ResponseBody
    public String apiWorkpieceSaveOrUpdate(User user, Workpiece workpiece) {
        result.clear();
        workpiece.setCreater(user);
        if (workpiece.getId() == 0) {
            workpiece.setId(null);
        }
        workpieceService.saveOrUpdate(workpiece);
        result.put("data", "保存成功");
        return result.toString();
    }

    /**
     * 删除工件API
     */
    @RequestMapping("workpieceDelete")
    @ResponseBody
    public String apiWorkpieceDelete(Workpiece workpiece) {
        result.clear();
        workpieceService.delete(workpiece);
        result.put("data", "删除成功");
        return result.toString();
    }

    /**
     * 获取工件API 传入起始位置和查询长度
     */
    @RequestMapping("workpiece/get")
    @ResponseBody
    @JsonFormat
    public List<WorkpieceVo> getWorkpieces(int from, int length, User creater) {
        return WorkpieceVo.format(workpieceService.queryAllLimit(from, length, creater));
    }

    /**
     * 获取车间模型中的工件列表API
     */
    @RequestMapping("workpiece/getInWorkstation")
    @ResponseBody
    public List<WorkpieceVo> getWorkpiecesInWorkstation(WorkStation workStation) {
        return WorkpieceVo.format(workpieceService.queryInWorkstation(workStation));
    }

    /**
     * 获取工件数量API
     */
    @RequestMapping("workpiece/getNum")
    @ResponseBody
    public String getNumWorkpiece(User creater) {
        result.clear();
        long num = workpieceService.queryNum(creater);
        result.put("data", num);
        return result.toString();
    }

    /**
     * 保存或更新工序API
     */
    @RequestMapping("processSaveOrUpdate")
    @ResponseBody
    public String apiProcessSaveOrUpdate(User user, Process process, Long workpieceId, Long machineId) {
        result.clear();
        process.setCreater(user);
        if (process.getId() == 0) {
            process.setId(null);
        }
        process.setWorkpiece(workpieceService.get(workpieceId));
        process.setMachine(machineService.get(machineId));
        processService.saveOrUpdate(process);
        result.put("data", "保存成功");
        return result.toString();
    }

    /**
     * 删除工序API
     */
    @RequestMapping("processDelete")
    @ResponseBody
    public String apiProcessDelete(Process process) {
        result.clear();
        processService.delete(process);
        result.put("data", "删除成功");
        return result.toString();
    }

    /**
     * 获取工序API
     */
    @RequestMapping("process/get")
    @ResponseBody
    @JsonFormat
    public List<ProcessVo> getProcesses(int from, int length, User creater, Long workpiece) {
        return ProcessVo.format(processService.queryAllLimit(from, length, creater, workpiece));
    }

    /**
     * 获取工序数量API
     */
    @RequestMapping("process/getNum")
    @ResponseBody
    public String getNumProcess(User creater, Long workpiece) {
        result.clear();
        Long num = processService.queryNum(creater, workpiece);
        result.put("data", num);
        return result.toString();
    }


    /**
     * 保存或更新机器API
     */
    @RequestMapping("machineSaveOrUpdate")
    @ResponseBody
    public String apiMachineSaveOrUpdate(User user, Machine machine) {
        result.clear();
        machine.setCreater(user);
        if (machine.getId() == 0) {
            machine.setId(null);
        }
        machineService.saveOrUpdate(machine);
        result.put("data", "保存成功");
        return result.toString();
    }

    /**
     * 删除机器API
     */
    @RequestMapping("machineDelete")
    @ResponseBody
    public String apiMachineDelete(Machine machine) {
        result.clear();
        machineService.delete(machine);
        result.put("data", "删除成功");
        return result.toString();
    }

    /**
     * 获取机器API
     */
    @RequestMapping("machine/get")
    @ResponseBody
    @JsonFormat
    public List<MachineVo> getMachine(int from, int length, User creater) {
        return MachineVo.format(machineService.queryAllLimit(from, length, creater));
    }

    /**
     * 获取机器数量API
     */
    @RequestMapping("machine/getNum")
    @ResponseBody
    public String getNumMachine(User creater) {
        result.clear();
        long num = machineService.queryNum(creater);
        result.put("data", num);
        return result.toString();
    }

    /**
     * 保存或更新车间模型API
     */
    @RequestMapping("workstationSaveOrUpdate")
    @ResponseBody
    public String apiWorkstationSaveOrUpdate(User user, WorkStation workStation) {
        result.clear();
        workStation.setCreater(user);
        if (workStation.getId() == 0) {
            workStation.setId(null);
        }
        workstationService.saveOrUpdate(workStation);
        result.put("data", "保存成功");
        return result.toString();
    }

    /**
     * 删除车间模型API
     */
    @RequestMapping("workstationDelete")
    @ResponseBody
    public String apiWorkstationDelete(WorkStation workStation) {
        result.clear();
        workstationService.delete(workStation);
        result.put("data", "删除成功");
        return result.toString();
    }

    /**
     * 获取车间模型API
     */
    @RequestMapping("workstation/get")
    @ResponseBody
    @JsonFormat
    public List<WorkstationVo> getWorkstation(int from, int length, User creater) {
        return WorkstationVo.format(workstationService.queryAllLimit(from, length, creater));
    }

    /**
     * 获取车间模型数量API
     */
    @RequestMapping("workstation/getNum")
    @ResponseBody
    public String getNumWorkstation(User creater) {
        result.clear();
        long num = workstationService.queryNum(creater);
        result.put("data", num);
        return result.toString();
    }

    /**
     * 向车间模型添加工件API
     */
    @RequestMapping("workstation/add")
    @ResponseBody
    public String workstationAdd(WorkStation workStation, Long workpieceId) {
        result.clear();
        workStation = workstationService.get(workStation.getId());
        workStation.getWorkpieceList().add(workpieceService.get(workpieceId));
        workstationService.saveOrUpdate(workStation);
        result.put("data", "添加成功");
        return result.toString();
    }

    /**
     * 从车间模型删除工件API
     */
    @RequestMapping("workstationDeleteWorkpiece")
    @ResponseBody
    public String workstationDeleteWorkpiece(WorkStation workStation, Long itemId) {
        result.clear();
        workStation = workstationService.get(workStation.getId());
        Workpiece workpiece = null;
        for (Workpiece p : workStation.getWorkpieceList()) {
            if (p.getId() == itemId) workpiece = p;
        }
        workStation.getWorkpieceList().remove(workpiece);
        workstationService.saveOrUpdate(workStation);
        result.put("data", "删除成功");
        return result.toString();
    }

    /**
     * 车间模型数据导出API
     */
    @RequestMapping("workstationExport")
    @ResponseBody
    public String workstationExport(WorkStation workStation) {
        result.clear();
        JSONObject object = workstationService.export(workStation);
        String json = object.toString();
        result.put("file", json);
        result.put("data", "导出成功");
        return result.toString();
    }

    /**
     * 运行车间调度并返回结果数据API
     */
    @RequestMapping("run")
    @ResponseBody
    public String run(WorkStation workStation, Canvas canvas) {
        result.clear();

        workStation = workstationService.get(workStation.getId());
        workStation.init();
        List<DNA> dnaResult = Heredity.start(workStation);
        RRNA.translate(workStation, dnaResult.get(0));

        workstationService.draw(workStation, canvas, "work");
        result.put("work", canvas.toJson());
        canvas.getChildren().clear();
        workstationService.draw(workStation, canvas, "machine");
        result.put("machine", canvas.toJson());

        JSONArray dnaArray = new JSONArray();
        for (DNA dna : dnaResult) {
            dnaArray.put(dna.toString());
        }
        result.put("dna", dnaArray);
        result.put("time", dnaResult.get(0).getTime());

        result.put("data", "执行成功");
        return result.toString();
    }

    /**
     * 根据dna画甘特图API
     */
    @RequestMapping("draw")
    @ResponseBody
    public String draw(WorkStation workStation, Canvas canvas, String dna) {
        result.clear();

        workStation = workstationService.get(workStation.getId());
        workStation.init();
        DNA dnaReal = new DNA();
        dnaReal.format(dna);
        RRNA.translate(workStation, dnaReal);

        workstationService.draw(workStation, canvas, "work");
        result.put("work", canvas.toJson());
        canvas.getChildren().clear();
        workstationService.draw(workStation, canvas, "machine");
        result.put("machine", canvas.toJson());

        result.put("data", "执行成功");
        return result.toString();
    }

}
