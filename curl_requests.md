# Curl Requests for StarProtect Vehicle Backend API

This document contains curl requests for all endpoints in the StarProtect Vehicle Backend API. Dummy data is used where necessary.

## VehicleController Endpoints

### 1. Register a Vehicle
> Note: The underwriter ID is not needed in the request as it will be automatically set to the currently logged-in underwriter.

```bash
curl -X POST "http://localhost:8080/api/vehicles" \
  -u "Azmi:Secure@123" \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleNo": "UP50AR0007",
    "vehicleType": "4-wheeler",
    "customerName": "Azmi",
    "engineNo": "EN12345678",
    "chasisNo": "CH12345678",
    "phoneNo": "9999999999",
    "policyType": "Full Insurance",
    "fromDate": "2023-01-01",
    "toDate": "2024-01-01",
    "premiumAmount": 5000.00,
    "claimStatus": "No"
  }'
```

### 2. Renew a Policy
```bash
curl -X POST "http://localhost:8080/api/vehicles/1/renew?claimStatus=No" \
  -u "Azmi:Secure@123" \
  -H "Content-Type: application/json"
```

### 3. Get All Policies
```bash
curl -X GET "http://localhost:8080/api/vehicles" \
  -u "Azmi:Secure@123" \
  -H "Accept: application/json"
```

### 4. Get Policy by ID
```bash
curl -X GET "http://localhost:8080/api/vehicles/1" \
  -u "Azmi:Secure@123" \
  -H "Accept: application/json"
```

### 5. Update Policy Type
```bash
curl -X PATCH "http://localhost:8080/api/vehicles/1/policy-type?newPolicyType=Third%20Party" \
  -H "Content-Type: application/json"
```

## AdminController Endpoints

### 1. Register an Admin
```bash
curl -X POST "http://localhost:8080/api/admin/register" \
  -H "Content-Type: application/json" \
  -d '{
    "adminName": "admin123",
    "adminPassword": "securePassword123"
  }'
```

### 2. Admin Login
```bash
curl -X POST "http://localhost:8080/api/admin/login?adminName=admin&adminPassword=admin123" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

### 3. Update an Underwriter
```bash
curl -X PUT "http://localhost:8080/api/admin/underwriters/10" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Shifa",
    "dateOfBirth": "1985-05-15",
    "dateOfJoining": "2020-01-10",
    "password": "underwriter123"
  }'
```

### 4. Delete an Underwriter
```bash
curl -X DELETE "http://localhost:8080/api/admin/underwriters/1" \
  -H "Content-Type: application/json"
```

### 5. Get All Underwriters
```bash
curl -X GET "http://localhost:8080/api/admin/underwriters" \
  -H "Accept: application/json"
```

### 6. Get Underwriter by ID
```bash
curl -X GET "http://localhost:8080/api/admin/underwriters/1" \
  -H "Accept: application/json"
```

### 7. Get All Admins
```bash
curl -X GET "http://localhost:8080/api/admin/admins" \
  -H "Accept: application/json"
```

### 8. Get Admin by ID
```bash
curl -X GET "http://localhost:8080/api/admin/admins/1" \
  -H "Accept: application/json"
```

### 9. Update an Admin
```bash
curl -X PUT "http://localhost:8080/api/admin/admins/10" \
  -H "Content-Type: application/json" \
  -d '{
    "adminName": "admin123",
    "adminPassword": "newSecurePassword456"
  }'
```

### 10. Delete an Admin
```bash
curl -X DELETE "http://localhost:8080/api/admin/admins/1" \
  -H "Content-Type: application/json"
```

### 11. Admin Logout
```bash
curl -X POST "http://localhost:8080/api/admin/logout" \
  -H "Content-Type: application/json"
```

### 12. General Logout (for any authenticated user)
```bash
curl -X POST "http://localhost:8080/api/logout" \
  -H "Content-Type: application/json"
```

## HomeController Endpoints

### 1. Register an Underwriter
```bash
curl -X POST "http://localhost:8080/api/v1/underwriter/register" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Robert Johnson",
    "dateOfBirth": "1990-08-20",
    "dateOfJoining": "2022-03-15",
    "password": "underwriter456"
  }'
```

### 2. Get All Underwriters
```bash
curl -X GET "http://localhost:8080/api/v1/underwriter/get_all" \
  -u "Azmi:Secure@123" \
  -H "Accept: application/json"
```

### 3. Get Currently Logged-in Underwriter
```bash
curl -X GET "http://localhost:8080/api/v1/underwriter/get_logged_in" \
  -u "Azmi:Secure@123" \
  -H "Accept: application/json"
```

### 4. Underwriter Login
```bash
curl -X POST "http://localhost:8080/api/v1/underwriter/login?name=Azmi&password=Secure@123" \
  -H "Content-Type: application/x-www-form-urlencoded"
```

### 5. Underwriter Logout
```bash
curl -X POST "http://localhost:8080/api/v1/underwriter/logout" \
  -H "Content-Type: application/json"
```
