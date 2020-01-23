package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
