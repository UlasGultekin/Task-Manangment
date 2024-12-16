package com.ulasgultkn.GYU.repository;

import com.ulasgultkn.GYU.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {


}
