package com.starprotect.repository;

import com.starprotect.entity.Underwriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderwriterRepository extends JpaRepository <Underwriter, Long>{
    Underwriter findByName(String username);
}
