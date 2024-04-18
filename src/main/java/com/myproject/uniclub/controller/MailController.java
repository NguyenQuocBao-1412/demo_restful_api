package com.myproject.uniclub.controller;

import com.myproject.uniclub.dto.DataMailDTO;
import com.myproject.uniclub.payload.request.ClientRequest;
import com.myproject.uniclub.service.ClientService;
import com.myproject.uniclub.service.MailService;
import com.myproject.uniclub.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mails")
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private ClientService clientService;


    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody ClientRequest request) {
        DataMailDTO dataMail = clientService.create(request);
        mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
