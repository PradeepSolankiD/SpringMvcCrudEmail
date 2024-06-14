package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    @Qualifier("mailSender")
    private JavaMailSender mailSender;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("to") String to,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body,
                            Model model) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("tell_pradeep@yahoo.co.in");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            model.addAttribute("message", "Email sent successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error sending email: " + e.getMessage());
        }
        return "ArticleReport";
    }
}
