package com.contexthub.rag.controller;

import com.contexthub.rag.dto.IngestionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class IngestionController {

    @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public IngestionResponse ingest(@RequestParam("file") MultipartFile file) {
        return new IngestionResponse("Ingestion controller placeholder: " + file.getOriginalFilename());
    }

    @DeleteMapping("/documents/{documentId}")
    public IngestionResponse deleteDocument(@PathVariable String documentId) {
        return new IngestionResponse("Document deletion controller placeholder: " + documentId);
    }
}
