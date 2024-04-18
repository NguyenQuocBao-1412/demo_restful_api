package com.myproject.uniclub.service.imp;

import com.myproject.uniclub.dto.DataMailDTO;
import com.myproject.uniclub.payload.request.ClientRequest;
import com.myproject.uniclub.service.ClientService;
import com.myproject.uniclub.service.MailService;
import com.myproject.uniclub.utils.Const;
import com.myproject.uniclub.utils.DataUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;
    @Override
    public DataMailDTO create(ClientRequest request) {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(request.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", request.getName());
            props.put("username", request.getUsername());
            props.put("password", DataUtils.generateTempPwd(6));

            dataMail.setProps(props);

            return dataMail;

    }
}
