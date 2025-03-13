package com.verma.sandeep.hospital.mate.Invoice;

import java.awt.Color;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.verma.sandeep.hospital.mate.entity.PaymentDetail;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class InvoicePdfGenerator {
	
	public void generateInvoice(HttpServletResponse response,
			                                                PaymentDetail paymentDetail) throws Exception
	{
		try {
			String orderId=paymentDetail.getOrderId();
			// Set the response type to PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice" + orderId +".pdf");
            
         // Create Document
            Document document = new Document();
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);

            // Open document
            document.open();
            
         // Add Title
            Font titleFont = new Font(Font.TIMES_ROMAN, 22, Font.BOLD);
            Paragraph title = new Paragraph("SV HOSPITAL", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
         // Add Address
            Font addressFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
            Paragraph address = new Paragraph("Lucknow Utter Pradesh", addressFont);
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);
            
            // Formatting Date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm");
            String paymentDate = paymentDetail.getDateOfPayment().format(dtf);
            
         // Table for Patient & Payment Details
            PdfPTable table = new PdfPTable(2);
            table.setSpacingBefore(20f);
            table.setWidthPercentage(100);
            
            table.addCell(getTableCell("Order ID:", true));
            table.addCell(getTableCell(paymentDetail.getOrderId(), false));
            
            table.addCell(getTableCell("Payment Date:", true));
            table.addCell(getTableCell(paymentDate, false));
            
            table.addCell(getTableCell("Patient Name:", true));
            table.addCell(getTableCell(paymentDetail.getPatientName(), false));
            
            table.addCell(getTableCell("Patient Email:", true));
            table.addCell(getTableCell(paymentDetail.getPatientEmail(), false));
            
            table.addCell(getTableCell("Final Amount:", true));
            table.addCell(getTableCell(paymentDetail.getCurrency() + " " +  paymentDetail.getAmount(), false));
            
            document.add(table);
            
         // Note
            Font noteFont = new Font(Font.TIMES_ROMAN, 10, Font.BOLD, Color.RED);
            Paragraph note = new Paragraph("NOTE: This is an auto-generated payment slip. For more details, contact Front Desk sandeepverma2614@gmail.com", noteFont);
            note.setAlignment(Element.ALIGN_CENTER);
            note.setSpacingBefore(10f);
            document.add(note);
            
            // Signature
            Font signFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
            Paragraph sign = new Paragraph("SIGNATURE", signFont);
            sign.setAlignment(Element.ALIGN_RIGHT);
            sign.setSpacingBefore(20f);
            document.add(sign);
            
            Paragraph datePar = new Paragraph("Date: " + new Date());
            datePar.setAlignment(Element.ALIGN_RIGHT);
            document.add(datePar);

            // Close document
            document.close();

		}catch (Exception e) {
			throw e;
		}
		
		
	}
	
	private PdfPCell getTableCell(String text, boolean isHeader) {
        Font font = isHeader ? new Font(Font.TIMES_ROMAN, 12, Font.BOLD) : new Font(Font.TIMES_ROMAN, 12);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        return cell;
    }

}
