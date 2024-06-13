package org.example.spring_basic_structure.repository;

import org.example.spring_basic_structure.model.entity.UserInfm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfmDAO extends JpaRepository<UserInfm, String> {
    Optional<UserInfm> findByEml(String email);

    Optional<UserInfm> findByEmlOrTel(String email, String tel);
}
