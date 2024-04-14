package com.myproject.uniclub.service;


import com.myproject.uniclub.entity.RolesEntity;
import com.myproject.uniclub.repositoty.RolesRepository;
import com.myproject.uniclub.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<RolesEntity> getAllRole() {
        return rolesRepository.findAll();
    }

    @Override
    public boolean deleteRoleById(int id) {

        boolean isSuccess = false;

        if(rolesRepository.existsById(id)) {
            rolesRepository.deleteById(id);
            isSuccess = true;
        }

        return isSuccess;
    }

    @Override
    public RolesEntity saveRole(RolesEntity role) {
        return rolesRepository.save(role);
    }


}
