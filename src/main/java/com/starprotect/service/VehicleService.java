package com.starprotect.service;

import com.starprotect.entity.Underwriter;
import com.starprotect.entity.Vehicle;
import com.starprotect.exception.DatabaseException;
import com.starprotect.exception.PolicyNotFoundException;
import com.starprotect.exception.RenewalException;
import com.starprotect.exception.UpdateException;
import com.starprotect.repository.VehicleRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UnderwriterService underwriterService;

    public VehicleService(VehicleRepository vehicleRepository, UnderwriterService underwriterService) {
        this.vehicleRepository = vehicleRepository;
        this.underwriterService = underwriterService;
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        try {
//            vehicle.setPolicyId(null); // Ensure new policy
            // Set the underwriter to the currently logged-in underwriter
            Underwriter currentUnderwriter = underwriterService.getCurrentLoggedInUnderwriter();
            vehicle.setUnderwriter(currentUnderwriter);
            return vehicleRepository.save(vehicle);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to register vehicle");
        }
    }

    @Transactional
    public Vehicle renewPolicy(Long policyId, String claimStatus) throws RenewalException {
        Vehicle existingPolicy = vehicleRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

        // Check if policy is eligible for renewal
        LocalDate today = LocalDate.now();
        LocalDate expiryDate = existingPolicy.getToDate();

        if (today.isBefore(expiryDate.minusMonths(1))) {
            throw new RenewalException("Policy can only be renewed 1 month before expiry or after expiry date");
        }

        // Create new policy
        Vehicle newPolicy = new Vehicle();
        newPolicy.setVehicleNo(existingPolicy.getVehicleNo());
        newPolicy.setVehicleType(existingPolicy.getVehicleType());
        newPolicy.setCustomerName(existingPolicy.getCustomerName());
        newPolicy.setEngineNo(existingPolicy.getEngineNo());
        newPolicy.setChasisNo(existingPolicy.getChasisNo());
        newPolicy.setPhoneNo(existingPolicy.getPhoneNo());
        newPolicy.setPolicyType(existingPolicy.getPolicyType());
        newPolicy.setClaimStatus(claimStatus);

        // Calculate dates
        LocalDate newFromDate;
        if (today.isAfter(expiryDate)) {
            newFromDate = today;
        } else {
            newFromDate = expiryDate.plusDays(1);
        }
        newPolicy.setFromDate(newFromDate);
        newPolicy.setToDate(newFromDate.plusYears(1));

        // Calculate premium (example logic)
        double basePremium = 1000.0;
        newPolicy.setPremiumAmount(
            existingPolicy.getVehicleType().equals("2-wheeler") ? 
            basePremium * 0.95 : // 5% discount
            basePremium * 0.95
        );

        // Set the underwriter to the currently logged-in underwriter
        Underwriter currentUnderwriter = underwriterService.getCurrentLoggedInUnderwriter();
        newPolicy.setUnderwriter(currentUnderwriter);

        return vehicleRepository.save(newPolicy);
    }

    public List<Vehicle> getAllPolicies() {
        try {
            return vehicleRepository.findAll();
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to fetch policies");
        }
    }

    public Vehicle getPolicyById(Long policyId) {
        return vehicleRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));
    }

    public Vehicle updatePolicyType(Long policyId, String newPolicyType) throws UpdateException {
        Vehicle policy = getPolicyById(policyId);

        if (!policy.getPolicyType().equals("Full Insurance")) {
            throw new UpdateException("Only Full Insurance policies can be updated");
        }

        policy.setPolicyType(newPolicyType);
        try {
            return vehicleRepository.save(policy);
        } catch (DataAccessException e) {
            throw new DatabaseException("Failed to update policy");
        }
    }
}
