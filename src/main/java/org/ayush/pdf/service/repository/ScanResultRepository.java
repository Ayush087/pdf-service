package org.ayush.pdf.service.repository;

import org.ayush.pdf.service.models.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScanResultRepository extends JpaRepository<ScanResult, String> {
    Optional<ScanResult> findBySha256Hash(String sha256Hash);
}
