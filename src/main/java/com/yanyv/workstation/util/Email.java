package com.yanyv.workstation.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Email {
    Integer port;
    String hostName;
    String charSet;
    String fromEmail;
    String fromName;
    String authenticationUserName;
    String authenticationUserPass;
    String subject;
    String msgAfter;
    String msgBefore;
    public void sendEmail(String emailTo, String pin) throws EmailException {


        // 发送验证码邮件
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostName);
        // 邮箱SMTP服务器
        email.setCharset(charSet);
        // 设置编码格式
        email.addTo(emailTo);
        // 设置收件人
        email.setFrom(fromEmail, fromName);
        // 设置发件人
        email.setAuthentication(authenticationUserName, authenticationUserPass);
        // 设置邮箱地址和授权码
        email.setSubject(subject);
        // 设置发送主题
        email.setMsg(msgAfter + pin + msgBefore);
        // 设置发送内容
        email.setSSLOnConnect(true);
        email.setSslSmtpPort(port.toString());
        email.send();
        // 进行发送
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getAuthenticationUserName() {
        return authenticationUserName;
    }

    public void setAuthenticationUserName(String authenticationUserName) {
        this.authenticationUserName = authenticationUserName;
    }

    public String getAuthenticationUserPass() {
        return authenticationUserPass;
    }

    public void setAuthenticationUserPass(String authenticationUserPass) {
        this.authenticationUserPass = authenticationUserPass;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgAfter() {
        return msgAfter;
    }

    public void setMsgAfter(String msgAfter) {
        this.msgAfter = msgAfter;
    }

    public String getMsgBefore() {
        return msgBefore;
    }

    public void setMsgBefore(String msgBefore) {
        this.msgBefore = msgBefore;
    }
}
