package com.KaterynaKravchenko.repos;

import com.KaterynaKravchenko.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
