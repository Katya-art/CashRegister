package com.epam.KaterynaKravchenko.repos;

import com.epam.KaterynaKravchenko.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
