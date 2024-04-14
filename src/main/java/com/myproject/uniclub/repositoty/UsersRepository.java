package com.myproject.uniclub.repositoty;

import com.myproject.uniclub.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByEmail(String email);
}
