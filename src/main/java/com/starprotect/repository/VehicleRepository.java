package com.starprotect.repository;

import com.starprotect.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByUnderwriter_UnderwriterId(Long underwriterId);
}