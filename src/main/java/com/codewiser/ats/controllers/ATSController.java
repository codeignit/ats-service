package com.codewiser.ats.controllers;


import com.codewiser.ats.common.ResponseDto;
import com.codewiser.ats.service.CVReviewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/ats")
public class ATSController {

    private final CVReviewerService cvReviewerService;

    public ATSController(CVReviewerService cvReviewerService) {
        this.cvReviewerService = cvReviewerService;
    }

    @PostMapping(value = "/review", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> reviewCV(
            @RequestParam("cv") MultipartFile cv
            ) throws IOException {
        ResponseDto responseDto = this.cvReviewerService.cvReviewService(cv);
        return ResponseEntity.status(responseDto.getResponseCode()).body(responseDto);
    }
}
