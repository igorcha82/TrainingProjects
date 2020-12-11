import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

public class PdfParser {

    protected Integer parsingPdf(File entry) {
        int countPdf = 0;

        try {

            PDDocument pdDocument = PDDocument.load(entry);
            countPdf = pdDocument.getNumberOfPages();
            pdDocument.close();


        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        return countPdf;
    }

}
