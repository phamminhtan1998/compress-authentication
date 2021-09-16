package com.example.testcompressauth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDataRepo extends JpaRepository<AuthenticationData,Long> {
}
