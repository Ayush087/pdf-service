package org.ayush.pdf.service.services;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.ayush.pdf.service.models.ScanResult;
import org.ayush.pdf.service.repository.ScanResultRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Service class for processing PDF files.
 */
@Service
public class PdfService {
    private final Logger logger = LogManager.getLogger(PdfService.class);

    private final ScanResultRepository scanResultRepository;

    public PdfService(ScanResultRepository scanResultRepository) {
        this.scanResultRepository = scanResultRepository;
    }

    /**
     * Processes a PDF file asynchronously.
     *
     * @param file the PDF file to be processed
     * @throws IOException              if an I/O error occurs
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    @Async
    @Transactional
    public void processPdfFile(File file) throws IOException, NoSuchAlgorithmException {
        // Extract metadata
        String sha256Hash = calculateSHA256(file);
        PDDocument document = PDDocument.load(file);
        PDDocumentInformation info = document.getDocumentInformation();

        String pdfVersion = String.valueOf(document.getVersion());
        String producer = info.getProducer();
        String author = info.getAuthor();
        String createdDate = info.getCreationDate() != null ? info.getCreationDate().toString() : "Unknown";
        String updatedDate = info.getModificationDate() != null ? info.getModificationDate().toString() : "Unknown";
        ScanResult scanResult = new ScanResult();
        scanResult.setSha256Hash(sha256Hash);
        scanResult.setPdfVersion(pdfVersion);
        scanResult.setProducer(producer);
        scanResult.setAuthor(author);
        scanResult.setCreatedDate(createdDate);
        scanResult.setUpdatedDate(updatedDate);
        scanResult.setSubmissionDate(LocalDateTime.now());

        // Save to the database
        scanResultRepository.save(scanResult);
        logger.info("PDF processed successfully.");
        document.close();
    }

    /**
     * Calculates the SHA-256 hash of a file.
     *
     * @param file the file to be hashed
     * @return the SHA-256 hash as a hexadecimal string
     * @throws IOException              if an I/O error occurs
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
     */
    private String calculateSHA256(File file) throws IOException, NoSuchAlgorithmException {
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(fileBytes);
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}