package com.nicolasmpc.usermanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nicolasmpc.usermanagement.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
