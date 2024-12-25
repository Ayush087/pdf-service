package org.ayush.pdf.service.controller;

import org.ayush.pdf.service.models.ScanResult;
import org.ayush.pdf.service.repository.ScanResultRepository;
import org.ayush.pdf.service.services.PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private final PdfService pdfService;

    private final ScanResultRepository scanResultRepository;

    public PdfController(PdfService pdfService, ScanResultRepository scanResultRepository) {
        this.pdfService = pdfService;
        this.scanResultRepository = scanResultRepository;
    }

    @PostMapping("/scan")
    public ResponseEntity<String> scanPdfFile(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        File tempFile = File.createTempFile("pdf_", ".pdf");
        file.transferTo(tempFile);

        pdfService.processPdfFile(tempFile);

        return ResponseEntity.ok("PDF is being processed asynchronously.");
    }

    @GetMapping("/lookup/{sha256Hash}")
    public ResponseEntity<ScanResult> lookupScanResult(@PathVariable String sha256Hash) {
        Optional<ScanResult> scanResult = scanResultRepository.findBySha256Hash(sha256Hash);
        return scanResult.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}