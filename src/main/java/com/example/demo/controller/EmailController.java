package com.example.demo.controller;

import com.example.demo.dto.request.SendEmailForm;
import com.example.demo.security.appuserprincipal.AppUserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Controller
public class EmailController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/sendmail")
    public String sendmailForm(){
        return "sendMail";
    }
    @PostMapping("/sendemail")
    public String sendEmail(@Valid SendEmailForm sendEmailForm, HttpSession session) throws AddressException, MessagingException, IOException {
        AppUserPrinciple appUserPrinciple = (AppUserPrinciple)session.getAttribute("user");
        boolean a = passwordEncoder.matches(sendEmailForm.getPassword(),appUserPrinciple.getPassword());
        if((sendEmailForm.getEmail()).equals(appUserPrinciple.getEmail()) && a){
            String number =  String.valueOf(Math.round(Math.floor(Math.random()*1000000)));
            session.setAttribute("number",number);
            sendmail(number,sendEmailForm.getEmail());
            return "redirect:/changePassword";
        }else {
            return "sendMail";
        }

    }
    private void sendmail(String number,String email) throws AddressException, MessagingException, IOException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); //Thay abc b???ng ?????a ch??? ng?????i nh???n

        // B???n c?? th??? ch???n CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //?????a ch??? cc gmail


        mailMessage.setSubject("Email g???i m?? x??c nh???n");
        mailMessage.setText(number);

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail th??nh gmail c???a b???n, thay your_password th??nh m???t kh???u gmail c???a b???n
        transport.connect("smtp.gmail.com", "waygon23@gmail.com", "steroids23");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
}
