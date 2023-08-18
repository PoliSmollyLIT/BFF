package com.tinqin.academy.core.invoice;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tinqin.academy.api.invoice.GenerateInvoiceOperation;
import com.tinqin.academy.api.invoice.InvoiceRequest;
import com.tinqin.academy.api.invoice.InvoiceResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;


@RequiredArgsConstructor
public class InvoiceGenerator implements GenerateInvoiceOperation {
        private final HttpServletResponse response;

        @SneakyThrows
        @Override
        public InvoiceResponse process(InvoiceRequest request) {
                // Creating the Object of Document
                Document document = new Document(PageSize.A4);

                // Setting the filename to orderId.pdf
                StringBuilder fileName = new StringBuilder();
                fileName.append(request.getOrderId());
                fileName.append(".pdf");

                // Getting instance of PdfWriter
                PdfWriter.getInstance(document, new FileOutputStream(fileName.toString()));

                // Opening the created document to modify it
                document.open();

                // Creating font
                // Setting font style and size
                Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                fontTiltle.setSize(20);

                // Creating paragraph
                Paragraph paragraph = new Paragraph("Invoice №" + request.getOrderId(), fontTiltle);

                // Aligning the paragraph in document
                paragraph.setAlignment(Paragraph.ALIGN_CENTER);

                // Adding the created paragraph in document
                document.add(paragraph);

                StringBuilder user = new StringBuilder();
                user.append(request.getUserFistName());
                user.append(" ");
                user.append(request.getUserLastName());
                //Adding info for the user
                Chunk userName = new Chunk(user.toString(), FontFactory.getFont(FontFactory.COURIER, 20, Font.ITALIC));
                document.add(userName);
                Chunk userPhone = new Chunk(request.getPhone(), FontFactory.getFont(FontFactory.COURIER, 20, Font.ITALIC));
                document.add(userPhone);
                Chunk dateMade = new Chunk(request.getDateMade().toString(), FontFactory.getFont(FontFactory.COURIER, 20, Font.ITALIC));
                document.add(dateMade);

                // Creating a table of 3 columns
                PdfPTable table = new PdfPTable(3);

                // Setting width of table, its columns and spacing
                table.setWidthPercentage(100f);
                table.setWidths(new int[]{3, 3, 3});
                table.setSpacingBefore(5);

                // Create Table Cells for table header
                PdfPCell cell = new PdfPCell();

                // Setting the background color and padding
                cell.setBackgroundColor(CMYKColor.DARK_GRAY);
                cell.setPadding(5);

                // Creating font
                // Setting font style and size
                Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
                font.setColor(CMYKColor.WHITE);

                // Adding headings in the created table cell/ header
                // Adding Cell to table
                cell.setPhrase(new Phrase("Item", font));
                table.addCell(cell);
                cell.setPhrase(new Phrase("Quantity", font));
                table.addCell(cell);
                cell.setPhrase(new Phrase("Price", font));
                table.addCell(cell);

                request.getItems().stream()
                        .forEach(item -> {
                                table.addCell(String.valueOf(item.getItemTitle()));
                                table.addCell(String.valueOf(item.getQuantity()));
                                table.addCell(String.valueOf(item.getPrice()));
                        });


                // Add prices, discounts and final price
                table.addCell(request.getPrice().toString());
                table.addCell(request.getDiscount().toString());
                table.addCell(request.getFinalPrice().toString());

                // Adding the created table to document
                document.add(table);

                // Closing the document
                document.close();

                return InvoiceResponse.builder().response("Succssesfuly made file with name: " + fileName.toString()).build();
        }

}
