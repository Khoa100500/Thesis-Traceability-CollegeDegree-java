package com.iupv.demo.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "signatures")
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sign_id", nullable = false)
    private Integer id;

    @Column(name = "is_verified_against_root", nullable = false)
    private Boolean isVerifiedAgainstRoot = false;

    @Column(name = "issuer", nullable = false)
    private String issuer;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "valid_from", nullable = false)
    private String validFrom;

    @Column(name = "valid_to", nullable = false)
    private String validTo;

    @Column(name = "validity_now", nullable = false, length = 35)
    private String validityNow;

    @Column(name = "validity_on_sign", nullable = false, length = 55)
    private String validityOnSign;

    @Column(name = "contact_info", nullable = false)
    private String contactInfo;

    @Column(name = "digest_algorithm", nullable = false)
    private String digestAlgorithm;

    @Column(name = "encryptionAlgorithm", nullable = false)
    private String encryptionAlgorithm;

    @Column(name = "field_on_page", nullable = false)
    private Byte fieldOnPage;

    @Column(name = "filter_subtype")
    private String filterSubtype;

    @Column(name = "integrity", nullable = false)
    private Boolean integrity = false;

    @Column(name = "is_annotations_allowed", nullable = false)
    private Boolean isAnnotationsAllowed = false;

    @Column(name = "is_fill_in_allowed", nullable = false)
    private Boolean isFillInAllowed = false;

    @Column(name = "is_signature_invisible", nullable = false)
    private Boolean isSignatureInvisible = false;

    @Column(name = "isTimeStampVerified", nullable = false)
    private Boolean isTimeStampVerified = false;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "revision", nullable = false)
    private String revision;

    @Column(name = "signature_covers_whole_document", nullable = false)
    private Boolean signatureCoversWholeDocument = false;

    @Column(name = "signature_type", nullable = false)
    private String signatureType;

    @Column(name = "signed_on", nullable = false)
    private LocalDate signedOn;

    @Column(name = "signerAlternativeName", nullable = false)
    private String signerAlternativeName;

    @Column(name = "signerName", nullable = false)
    private String signerName;

    @Column(name = "timeStamp", nullable = false)
    private String timeStamp;

    @Column(name = "timeStampService", nullable = false)
    private String timeStampService;

    @OneToMany(mappedBy = "sign")
    private Set<Report> reports = new LinkedHashSet<>();

}