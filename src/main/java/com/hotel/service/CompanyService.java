package com.hotel.service;

import com.hotel.entity.Company;
import com.hotel.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    
    public Optional<Company> getCompanyById(String companyId) {
        return companyRepository.findById(companyId);
    }
    
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
    
    public void deleteCompany(String companyId) {
        companyRepository.deleteById(companyId);
    }
    
    public List<Company> searchCompaniesByName(String companyName) {
        return companyRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }
    
    public Company updateCompany(String companyId, Company updatedCompany) {
        Optional<Company> existingCompany = companyRepository.findById(companyId);
        if (existingCompany.isPresent()) {
            updatedCompany.setCompanyId(companyId);
            return companyRepository.save(updatedCompany);
        }
        throw new RuntimeException("Company not found with id: " + companyId);
    }
}
