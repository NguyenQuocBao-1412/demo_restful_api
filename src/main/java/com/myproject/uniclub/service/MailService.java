package com.myproject.uniclub.service;

import com.myproject.uniclub.dto.DataMailDTO;
import jakarta.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName);
}
