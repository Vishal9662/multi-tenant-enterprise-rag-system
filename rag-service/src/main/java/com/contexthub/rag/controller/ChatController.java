package com.contexthub.rag.controller;

import com.contexthub.rag.dto.ChatRequest;
import com.contexthub.rag.dto.IngestionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return "RAG chat controller placeholder: " + request.message();
    }
}
