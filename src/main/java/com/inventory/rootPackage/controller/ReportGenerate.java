package com.inventory.rootPackage.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.rootPackage.ReportModel.Report;
import com.inventory.rootPackage.ReportService.ReportService;
import com.inventory.rootPackage.mailService.MailService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class ReportGenerate {

	@Autowired
	private ReportService reportService;

	@Autowired
	private MailService mailService;

	@GetMapping("/report")
	public String showReport(Model model) {
		List<Report> reports = reportService.getInventoryReport();
		model.addAttribute("reports", reports);
		return "report";
	}

	@GetMapping("/report/pdf")
	public String generatePdfAndSendEmail(Model model) {
		try {
			List<Report> reports = reportService.getInventoryReport();

			// 1️⃣ Dynamic PDF path
			String pdfDir = "C:/Users/HP/Downloads/";
			String fileName = "inventory_report_" + System.currentTimeMillis() + ".pdf";
			String pdfPath = pdfDir + fileName;
			File pdfFile = new File(pdfPath);

			// 2️⃣ Create PDF
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
			document.open();

			// Title
			Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.BLUE);
			Paragraph title = new Paragraph("Inventory Management Report", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(20);
			document.add(title);

			// Table
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);
			PdfPCell h1 = new PdfPCell(new Phrase("Item Name", headFont));
			h1.setBackgroundColor(Color.BLUE);
			h1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(h1);

			PdfPCell h2 = new PdfPCell(new Phrase("Total Stock", headFont));
			h2.setBackgroundColor(Color.BLUE);
			h2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(h2);

			PdfPCell h3 = new PdfPCell(new Phrase("Sold", headFont));
			h3.setBackgroundColor(Color.BLUE);
			h3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(h3);

			Font dataFont = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.BLACK);
			for (Report r : reports) {
				PdfPCell cell1 = new PdfPCell(new Phrase(r.getItemName(), dataFont));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(r.getTotalStock()), dataFont));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell2);

				PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(r.getSold()), dataFont));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell3);
			}

			document.add(table);
			document.close();

			// 3️⃣ Send email
			mailService.sendEmailWithAttachment(/*"nitinbsundar@gmail.com"*/"surya.kanthanraja@gmail.com", "Inventory Report",
					"Hi Suru ❤️, please find attached your latest inventory report.", pdfFile);

			// 4️⃣ Pass attributes to JSP	``````````````````````````
			model.addAttribute("status", "success");
			model.addAttribute("recipient", "way3samson@gmail.com");
			model.addAttribute("subject", "Inventory Report");
			model.addAttribute("message", "Mail with PDF sent successfully!");
			model.addAttribute("errorDetails", null);
			model.addAttribute("now", new Date()); // for timestamp

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", "failure");
			model.addAttribute("recipient", "way3samson@gmail.com");
			model.addAttribute("subject", "Inventory Report");
			model.addAttribute("message", "Failed to send mail with PDF.");
			model.addAttribute("errorDetails", e.getMessage());
			model.addAttribute("now", new Date());
		}

		return "ConfirmationMail"; // JSP page
	}
}
