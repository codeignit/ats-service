package com.codewiser.ats.service;

import com.codewiser.ats.common.FileValidatorService;
import com.codewiser.ats.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class CVReviewerService {


    private final FileValidatorService fileValidatorServiceService;
    private final ChatClient chatClient;
    private final PdfService pdfService;


    public CVReviewerService(FileValidatorService fileValidatorServiceService, ChatClient atsReviewerChatClient, PdfService pdfService) {
        this.fileValidatorServiceService = fileValidatorServiceService;
        this.chatClient = atsReviewerChatClient;
        this.pdfService = pdfService;
    }

    public ResponseDto cvReviewService(MultipartFile cv) throws IOException {
        this.fileValidatorServiceService.validatePdfOrDoc(cv);
        String extractedText = this.pdfService.extractText(cv);


        return chatClient.prompt(extractedText)
                .call()
                .entity(ResponseDto.class);
    }
}
