//package com.inventory.rootPackage.mailController;
//
//import com.inventory.rootPackage.mailService.MailService;
//import jakarta.mail.MessagingException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.Comparator;
//
//@Controller
//public class EmailController {
//
//    @Autowired
//    private MailService mailService;
//
//    @GetMapping("/email")
//    public String send(HttpServletRequest request) {
//        try {
//            // 1️⃣ Directory where PDFs are saved
//            File pdfDir = new File("C:/Users/Surya/Downloads/");
//
//            // 2️⃣ Find the latest PDF file in the directory
//            File[] pdfFiles = pdfDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
//
//            if (pdfFiles == null || pdfFiles.length == 0) {
//                throw new RuntimeException("No PDF files found in " + pdfDir.getAbsolutePath());
//            }
//
//            // Sort by last modified date descending
//            Arrays.sort(pdfFiles, Comparator.comparingLong(File::lastModified).reversed());
//
//            File latestPdf = pdfFiles[0]; // the most recently generated PDF
//
//            // 3️⃣ Send the latest PDF
//            mailService.sendEmailWithAttachment(
//                    "surya.kanthanraja@gmail.com",
//                    "Test Mail with PDF",
//                    "Hello Suru ❤️, please find your attached latest report.",
//                    latestPdf
//            );
//
//            request.setAttribute("status", "success");
//            request.setAttribute("recipient", "surya.kanthanraja@gmail.com");
//            request.setAttribute("subject", "Test Mail with PDF");
//            request.setAttribute("message", "Mail with latest PDF sent successfully!");
//
//        } catch (MessagingException | RuntimeException e) {
//            request.setAttribute("status", "failure");
//            request.setAttribute("recipient", "surya.kanthanraja@gmail.com");
//            request.setAttribute("subject", "Test Mail with PDF");
//            request.setAttribute("message", "Failed to send mail with PDF.");
//            request.setAttribute("errorDetails", e.getMessage());
//        }
//
//        return "ConfirmationMail"; // /WEB-INF/jsp/ConfirmationMail.jsp
//    }
//}
