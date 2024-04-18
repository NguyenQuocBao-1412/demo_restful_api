package com.myproject.uniclub.service;



import com.myproject.uniclub.entity.RolesEntity;

import java.util.List;

public interface RoleService {
    public List<RolesEntity> getAllRole();

    public boolean deleteRoleById(int id);

    public RolesEntity saveRole(RolesEntity role);
}
