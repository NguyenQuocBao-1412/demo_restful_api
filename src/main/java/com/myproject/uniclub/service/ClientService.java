package com.myproject.uniclub.service;

import com.myproject.uniclub.dto.DataMailDTO;
import com.myproject.uniclub.payload.request.ClientRequest;

public interface ClientService {
    DataMailDTO create(ClientRequest request);
}
