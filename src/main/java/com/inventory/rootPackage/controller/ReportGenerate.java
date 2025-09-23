package com.inventory.rootPackage.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.rootPackage.ReportService.ReportService;
import com.inventory.rootPackage.mailService.MailService;
import com.inventory.rootPackage.model.ShoperPaid;
import com.lowagie.text.Document;
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
		List<ShoperPaid> reports = reportService.getInventoryReport();
		model.addAttribute("reports", reports);
		return "report";
	}

	@GetMapping("/report/pdf")
	public String generatePdfAndSendEmail(Model model) {
		try {
			List<ShoperPaid> reports = reportService.getInventoryReport();

			// 1️⃣ Dynamic PDF path
			String pdfDir = "C:/Users/HP/Downloads/";
			String fileName = "inventory_report_" + System.currentTimeMillis() + ".pdf";
			String pdfPath = pdfDir + fileName;
			File pdfFile = new File(pdfPath);

			// 2️⃣ Create PDF
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
			document.open();

			// Fonts
			Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.BLACK);
			Font smallFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
			Font headFont = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
			Font dataFont = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);

			// 3️⃣ Timestamp (left aligned)
			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			Paragraph ts = new Paragraph("Generated: " + timestamp, smallFont);
			ts.setAlignment(Element.ALIGN_LEFT);
			document.add(ts);

			// 4️⃣ Title (center aligned)
			Paragraph title = new Paragraph("Inventory Management Report", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			title.setSpacingAfter(10);
			document.add(title);

			// 5️⃣ Table with all fields
			PdfPTable table = new PdfPTable(14); // 14 columns
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			String[] headers = { "S.No", "Item ID", "Name", "Category", "Brand", "Model", "Price", "GST", "Quantity",
					"Total Price", "Amount Paid", "Overall", "Dispatch Status", "Payment ID" };

			for (String h : headers) {
				PdfPCell cell = new PdfPCell(new Phrase(h, headFont));
				cell.setBackgroundColor(Color.WHITE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}

			int serial = 1;
			for (ShoperPaid r : reports) {
				table.addCell(createCell(String.valueOf(serial++), dataFont));
				table.addCell(createCell(String.valueOf(r.getItem_id()), dataFont));
				table.addCell(createCell(r.getName(), dataFont));
				table.addCell(createCell(r.getCategory(), dataFont));
				table.addCell(createCell(r.getBrand(), dataFont));
				table.addCell(createCell(r.getModel(), dataFont));
				table.addCell(createCell(String.valueOf(r.getPrice()), dataFont));
				table.addCell(createCell(String.valueOf(r.getGst()), dataFont));
				table.addCell(createCell(String.valueOf(r.getQuantity()), dataFont));
				table.addCell(createCell(String.valueOf(r.getTotalprice()), dataFont));
				table.addCell(createCell(String.valueOf(r.getAmountPaid()), dataFont));
				table.addCell(createCell(String.valueOf(r.getOverall()), dataFont));
				table.addCell(createCell(r.getDispatchStatus(), dataFont));
				table.addCell(createCell(r.getPaymentId() != null ? String.valueOf(r.getPaymentId().getId()) : "N/A",
						dataFont));
			}

			document.add(table);

			// 6️⃣ Footer
			Paragraph footer = new Paragraph("UNIQ_MANAGEMENT_SYSTEM", smallFont);
			footer.setAlignment(Element.ALIGN_CENTER);
			document.add(footer);

			document.close();
			writer.close();

			// 7️⃣ Send email
			mailService.sendEmailWithAttachment("manigapthykc@gmail.com", "Inventory Report",
					"Hi Manish ❤️, please find attached your latest inventory report.", pdfFile);

			// 8️⃣ Pass attributes to JSP
			model.addAttribute("status", "success");
			model.addAttribute("recipient", "way3samson@gmail.com");
			model.addAttribute("subject", "Inventory Report");
			model.addAttribute("message", "Mail with PDF sent successfully!");
			model.addAttribute("errorDetails", null);
			model.addAttribute("now", new Date());

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("status", "failure");
			model.addAttribute("recipient", "way3samson@gmail.com");
			model.addAttribute("subject", "Inventory Report");
			model.addAttribute("message", "Failed to send mail with PDF.");
			model.addAttribute("errorDetails", e.getMessage());
			model.addAttribute("now", new Date());
		}

		return "ConfirmationMail";
	}

	private PdfPCell createCell(String value, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(value, font));
		cell.setBackgroundColor(Color.WHITE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return cell;
	}
}
