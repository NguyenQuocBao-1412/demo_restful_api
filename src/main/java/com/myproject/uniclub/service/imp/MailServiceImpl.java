package com.myproject.uniclub.service.imp;

import com.myproject.uniclub.dto.DataMailDTO;
import com.myproject.uniclub.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(DataMailDTO dataMail, String templateName){
        try {
            // Khởi tạo mail
            MimeMessage message = mailSender.createMimeMessage();

            // Config
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // Context by thymeleaf
            Context context = new Context();
            context.setVariables(dataMail.getProps());

            // Set giữ liệu cho template
            String html = templateEngine.process(templateName, context);

            // Set thông tin người gửi và tiêu đề
            helper.setTo(dataMail.getTo());
            helper.setSubject(dataMail.getSubject());
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
