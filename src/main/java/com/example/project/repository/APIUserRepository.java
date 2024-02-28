package com.example.project.repository;

import com.example.project.domain.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIUserRepository extends JpaRepository<APIUser,String> {
}
