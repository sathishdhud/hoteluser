package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.entity.Company;
import com.hotel.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@Tag(name = "Company Management", description = "APIs for managing companies and corporate clients")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieve a list of all companies")
    public ResponseEntity<ApiResponse<List<Company>>> getAllCompanies() {
        try {
            List<Company> companies = companyService.getAllCompanies();
            return ResponseEntity.ok(ApiResponse.success("Companies retrieved successfully", companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve companies: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{companyId}")
    @Operation(summary = "Get company by ID", description = "Retrieve a specific company by its ID")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(
            @Parameter(description = "Company ID") @PathVariable String companyId) {
        try {
            Optional<Company> company = companyService.getCompanyById(companyId);
            if (company.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success("Company found", company.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Company not found with ID: " + companyId));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to retrieve company: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create new company", description = "Add a new company to the system")
    public ResponseEntity<ApiResponse<Company>> createCompany(@Valid @RequestBody Company company) {
        try {
            Company savedCompany = companyService.saveCompany(company);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Company created successfully", savedCompany));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to create company: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{companyId}")
    @Operation(summary = "Update company", description = "Update an existing company")
    public ResponseEntity<ApiResponse<Company>> updateCompany(
            @Parameter(description = "Company ID") @PathVariable String companyId,
            @Valid @RequestBody Company company) {
        try {
            Company updatedCompany = companyService.updateCompany(companyId, company);
            return ResponseEntity.ok(ApiResponse.success("Company updated successfully", updatedCompany));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Failed to update company: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{companyId}")
    @Operation(summary = "Delete company", description = "Remove a company from the system")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(
            @Parameter(description = "Company ID") @PathVariable String companyId) {
        try {
            companyService.deleteCompany(companyId);
            return ResponseEntity.ok(ApiResponse.success("Company deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to delete company: " + e.getMessage()));
        }
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search companies by name", description = "Search companies by name (case-insensitive)")
    public ResponseEntity<ApiResponse<List<Company>>> searchCompaniesByName(
            @Parameter(description = "Company name to search") @RequestParam String companyName) {
        try {
            List<Company> companies = companyService.searchCompaniesByName(companyName);
            return ResponseEntity.ok(ApiResponse.success("Search results retrieved", companies));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Failed to search companies: " + e.getMessage()));
        }
    }
}
