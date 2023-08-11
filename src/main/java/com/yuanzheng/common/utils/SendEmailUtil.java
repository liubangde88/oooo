package com.yuanzheng.common.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmailUtil {


    public static boolean SendEmail(String sender, String pwd, String receiver, String code) {

        try {

            Properties props = new Properties();

            // 开启debug调试

            props.setProperty("mail.debug", "true"); // false

            // 发送服务器需要身份验证

            props.setProperty("mail.smtp.auth", "true");

            // 设置邮件服务器主机名

            props.setProperty("mail.host", "smtp.gmail.com");

            // 发送邮件协议名称 这里使用的是smtp协议

            props.setProperty("mail.transport.protocol", "smtp");

            // 服务端口号

            props.setProperty("mail.smtp.port", "587");

            props.put("mail.smtp.starttls.enable", "true");

            // 设置环境信息

            Session session = Session.getInstance(props);

            // 创建邮件对象

            MimeMessage msg = new MimeMessage(session);

            // 设置发件人

            msg.setFrom(new InternetAddress(sender));

            // 设置收件人

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // 设置邮件主题

            msg.setSubject("注册验证码");

            // 设置邮件内容

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();

            // 发送邮件的文本内容

            textPart.setText("您的验证码是：" + code);

            multipart.addBodyPart(textPart);

            msg.setContent(multipart);

            Transport transport = session.getTransport();

            // 连接邮件服务器

            transport.connect(sender, pwd);

            // 发送邮件

            transport.sendMessage(msg, new Address[]{new InternetAddress(receiver)});

            // 关闭连接

            transport.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }
}
