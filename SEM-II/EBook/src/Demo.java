import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class Demo {

    public static void main(String[] args) {
        try {
            // Creating Child class object
            EBook ebook = new EBook(
                    "Java Programming",
                    "John Smith",
                    "2023",
                    450,
                    "5 MB",
                    "PDF"
            );

            // Display on terminal
            ebook.Display();

            // Create PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("EBookInfo.pdf"));
            document.open();
            document.add(new Paragraph(ebook.getBookText()));
            document.close();

            System.out.println("\nEBook information saved as PDF successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
