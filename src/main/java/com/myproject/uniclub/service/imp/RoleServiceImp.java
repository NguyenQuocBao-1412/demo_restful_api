package com.myproject.uniclub.service.imp;



import com.myproject.uniclub.entity.RolesEntity;

import java.util.List;

public interface RoleServiceImp {
    public List<RolesEntity> getAllRole();

    public boolean deleteRoleById(int id);

    public RolesEntity saveRole(RolesEntity role);
}
