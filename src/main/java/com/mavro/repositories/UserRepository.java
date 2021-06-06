package com.mavro.repositories;

import com.mavro.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {


    Optional<AppUser> findUserByUsername(String username);
}
