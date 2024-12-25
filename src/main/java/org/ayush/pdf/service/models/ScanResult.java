package org.ayush.pdf.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ScanResult {
    @Id
    private String sha256Hash;
    private String pdfVersion;
    private String producer;
    private String author;
    private String createdDate;
    private String updatedDate;
    private LocalDateTime submissionDate;

    public void setSha256Hash(String sha256Hash) {
        this.sha256Hash = sha256Hash;
    }

    public void setPdfVersion(String pdfVersion) {
        this.pdfVersion = pdfVersion;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "sha256Hash='" + sha256Hash + '\'' +
                ", pdfVersion='" + pdfVersion + '\'' +
                ", producer='" + producer + '\'' +
                ", author='" + author + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", submissionDate=" + submissionDate +
                '}';
    }
}