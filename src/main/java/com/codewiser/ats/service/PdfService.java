package com.codewiser.ats.service;


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    public String extractText(MultipartFile cv) {
        String text;
        try(PDDocument pdf = Loader.loadPDF(cv.getBytes())) {
           text  = new PDFTextStripper().getText(pdf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

}
