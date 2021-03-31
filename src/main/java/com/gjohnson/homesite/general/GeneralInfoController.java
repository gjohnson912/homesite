package com.gjohnson.homesite.general;

import com.gjohnson.homesite.general.contact.resume.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Primary controller for anything considered "general information". This will likely be in flux as I decide how I want
 * to group information.
 */
@RestController
@RequestMapping(path = "/general")
public class GeneralInfoController {

    private final GeneralInfoService generalInfoService;

    @Autowired
    public GeneralInfoController(GeneralInfoService generalInfoService) {
        this.generalInfoService = generalInfoService;
    }

    /**
     * Gets the {@link GeneralInfo} for the default contact of this system.
     *
     * @return an {@link HttpEntity} with the associated {@link GeneralInfo} if it exists, otherwise, this will return a
     * {@link ResponseEntity#notFound()}
     */
    @GetMapping(path = "/default")
    public HttpEntity<GeneralInfo> getSystemDefaultGeneralInfo() {
        return ResponseEntity.of(this.generalInfoService.retrieveSystemDefaultGeneralInfo());
    }

    /**
     * This gets the resume for the contact with the provided contactId. It responds with the appropriate headers to
     * indicate to the browser that the byte array should be downloaded as a PDF.
     *
     * @param contactId the unique ID for the contact for whom the resume is being requested
     * @return an {@link HttpEntity} with the resume as a PDF
     */
    @GetMapping(path = "/contact/{contactId}/resume/download")
    public HttpEntity<ByteArrayResource> getResumeByContact(@PathVariable Integer contactId) {
        return this.generalInfoService.retrieveResumeByContactId(contactId)
                .map(resumeInfo -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resumeInfo.getDocumentName() + "." + resumeInfo.getFileExtension())
                        .contentLength(resumeInfo.getDocument().length)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(new ByteArrayResource(resumeInfo.getDocument())))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for handling the upload of a new resume.
     *
     * NOTE: As of now, this endpoint is disabled as it is not needed in the production environment at this time,
     * especially without any sort of authentication/authorization.
     *
     * @param file the {@link MultipartFile} containing the resume file
     * @param contactId the unique ID for the contact for whom the resume is being submitted
     * @return an {@link HttpEntity} with the {@link Resume} information that was just created
     * @throws IOException in case there are issues getting the file into a byte array
     */
//    @PostMapping(path = "/contact/{contactId}/resume")
    public HttpEntity<Resume> uploadResume(@RequestParam("file") MultipartFile file, @PathVariable Integer contactId) throws IOException {
        if (file != null && !file.isEmpty() && contactId != null) {
            final var resume = new Resume();
            resume.setDocument(file.getBytes());
            if (file.getOriginalFilename() != null) {
                final var fileExtensionBeginIndex = file.getOriginalFilename().lastIndexOf(".");
                resume.setDocumentName(file.getOriginalFilename().substring(0, fileExtensionBeginIndex));
                resume.setFileExtension(file.getOriginalFilename().substring(fileExtensionBeginIndex + 1));
            }
            return ResponseEntity.ok(this.generalInfoService.insertResume(contactId, resume));

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
