package com.codewiser.ats.common;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class FileValidatorService {

    private static final Set<String> ALLOWED_MIME = Set.of("application/pdf", "application/msword");

    private String detectMimeHeader(MultipartFile file) throws IOException {
        byte[] h = file.getBytes(); // if files can be big, read only first bytes instead

        // PDF: %PDF
        if (h.length >= 4 &&
                h[0] == (byte)0x25 && h[1] == (byte)0x50 &&
                h[2] == (byte)0x44 && h[3] == (byte)0x46) {
            return "application/pdf";
        }

        // DOC: D0 CF 11 E0 A1 B1 1A E1
        if (h.length >= 8 &&
                (h[0] & 0xFF) == 0xD0 && (h[1] & 0xFF) == 0xCF &&
                (h[2] & 0xFF) == 0x11 && (h[3] & 0xFF) == 0xE0 &&
                (h[4] & 0xFF) == 0xA1 && (h[5] & 0xFF) == 0xB1 &&
                (h[6] & 0xFF) == 0x1A && (h[7] & 0xFF) == 0xE1) {
            return "application/msword";
        }

        return "unknown";
    }

    public void validatePdfOrDoc(MultipartFile file) throws IOException {
        String detected = detectMimeHeader(file);
        if (!ALLOWED_MIME.contains(detected)) {
            throw new IllegalArgumentException("Only PDF or DOC CV files are allowed.");
        }
    }

}
