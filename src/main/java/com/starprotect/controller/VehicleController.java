package com.starprotect.controller;

import com.starprotect.entity.Vehicle;
import com.starprotect.exception.RenewalException;
import com.starprotect.exception.UpdateException;
import com.starprotect.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;

    /**
     * Constructor for VehicleController
     * @param vehicleService the vehicle service
     */
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Register a new vehicle
     * @param vehicle the vehicle to register
     * @return ResponseEntity containing the registered vehicle
     */
    @PostMapping
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        // Ignore any underwriter information in the request
        vehicle.setUnderwriter(null);
        return ResponseEntity.ok(vehicleService.registerVehicle(vehicle));
    }

    /**
     * Renew a policy
     * @param policyId the ID of the policy to renew
     * @param claimStatus the claim status for the renewal
     * @return ResponseEntity containing the renewed policy
     * @throws RenewalException if the renewal fails
     */
    @PostMapping("/{policyId}/renew")
    public ResponseEntity<Vehicle> renewPolicy(
            @PathVariable Long policyId,
            @RequestParam String claimStatus) throws RenewalException {
        return ResponseEntity.ok(
                vehicleService.renewPolicy(policyId, claimStatus));
    }

    /**
     * Get all policies
     * @return ResponseEntity containing a list of all policies
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllPolicies() {
        return ResponseEntity.ok(vehicleService.getAllPolicies());
    }

    /**
     * Get a policy by ID
     * @param policyId the ID of the policy to retrieve
     * @return ResponseEntity containing the policy if found
     */
    @GetMapping("/{policyId}")
    public ResponseEntity<Vehicle> getPolicyById(@PathVariable Long policyId) {
        return ResponseEntity.ok(vehicleService.getPolicyById(policyId));
    }

    /**
     * Update a policy type
     * @param policyId the ID of the policy to update
     * @param newPolicyType the new policy type
     * @return ResponseEntity containing the updated policy
     * @throws UpdateException if the update fails
     */
    @PatchMapping("/{policyId}/policy-type")
    public ResponseEntity<Vehicle> updatePolicyType(
            @PathVariable Long policyId,
            @RequestParam String newPolicyType) throws UpdateException {
        return ResponseEntity.ok(
                vehicleService.updatePolicyType(policyId, newPolicyType));
    }
}
