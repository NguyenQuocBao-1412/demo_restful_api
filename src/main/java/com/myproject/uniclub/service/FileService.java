package com.myproject.uniclub.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public void save(MultipartFile file);

    public Resource getFile(String fileName);
}
