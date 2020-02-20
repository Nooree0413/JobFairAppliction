package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("Select a FROM UserRole a WHERE a.user.visa = :visa")
    UserRole getUserRoleByVisa(String visa);
}
