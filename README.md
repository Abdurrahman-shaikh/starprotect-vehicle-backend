# StarProtect Vehicle Backend

This is the backend service for the StarProtect Vehicle Insurance application. It provides APIs for managing vehicle insurance policies, administrators, and underwriters.

## Features

- User authentication (Admin and Underwriter)
- Role-based access control
- Vehicle policy management
- Policy renewal
- Policy type updates

## Authentication

The application supports two types of users:
1. **Admin** - Has full access to all features
2. **Underwriter** - Has limited access to vehicle policies

### Login

#### Admin Login
```
POST /api/admin/login
```
Parameters:
- `adminName`: The admin username
- `adminPassword`: The admin password

#### Underwriter Login
```
POST /api/v1/underwriter/login
```
Parameters:
- `name`: The underwriter username
- `password`: The underwriter password

### Logout

The application provides multiple endpoints for logging out:

#### General Logout (for any authenticated user)
```
POST /api/logout
```

#### Admin-specific Logout
```
POST /api/admin/logout
```

#### Underwriter-specific Logout
```
POST /api/v1/underwriter/logout
```

## API Endpoints

### Admin Endpoints

- `POST /api/admin/register` - Register a new admin
- `GET /api/admin/admins` - Get all admins
- `GET /api/admin/admins/{id}` - Get an admin by ID
- `PUT /api/admin/admins/{id}` - Update an admin
- `DELETE /api/admin/admins/{id}` - Delete an admin
- `GET /api/admin/underwriters` - Get all underwriters
- `GET /api/admin/underwriters/{id}` - Get an underwriter by ID
- `PUT /api/admin/underwriters/{id}` - Update an underwriter
- `DELETE /api/admin/underwriters/{id}` - Delete an underwriter

### Underwriter Endpoints

- `POST /api/v1/underwriter/register` - Register a new underwriter
- `GET /api/v1/underwriter/get_all` - Get all underwriters
- `GET /api/v1/underwriter/get_logged_in` - Get the currently logged-in underwriter

### Vehicle Endpoints

- `POST /api/vehicles` - Register a new vehicle
- `GET /api/vehicles` - Get all policies
- `GET /api/vehicles/{policyId}` - Get a policy by ID
- `POST /api/vehicles/{policyId}/renew` - Renew a policy
- `PATCH /api/vehicles/{policyId}/policy-type` - Update a policy type

## Security

The application uses Spring Security for authentication and authorization. It implements role-based access control to ensure that users can only access the resources they are authorized to use.

- Admin users have the "ADMIN" authority
- Underwriter users have the "USER" authority

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- H2 Database (for development)# starprotect-vehicle-backend
