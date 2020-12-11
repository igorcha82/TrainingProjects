import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PdfParserTest {

    @Test
    public void parsingPdf() throws IOException {

        final PDDocument document = new PDDocument();
        final File testFile = File.createTempFile("test", ".pdf");
        document.save(testFile);

        PdfParser pdfParser = new PdfParser();
        assertEquals("0", String.valueOf((pdfParser.parsingPdf(testFile))));
    }
}
