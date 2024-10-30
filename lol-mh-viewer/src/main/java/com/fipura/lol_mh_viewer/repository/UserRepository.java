package com.fipura.lol_mh_viewer.repository;

import com.fipura.lol_mh_viewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
