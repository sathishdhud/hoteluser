package com.hotel.repository;

import com.hotel.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    
    List<Company> findByCompanyNameContainingIgnoreCase(String companyName);
    
    List<Company> findByGstNumber(String gstNumber);
}
