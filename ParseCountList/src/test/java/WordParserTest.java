import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WordParserTest {

    @Test
    public void parsingWord() throws IOException {

        final File testFile = File.createTempFile("test", ".docx");
        final XWPFDocument document = new XWPFDocument();

        document.getProperties().getExtendedProperties().getUnderlyingProperties().setPages(3);
        document.write(new FileOutputStream(testFile));
        document.close();

        WordParser wordParser = new WordParser();
        assertEquals("3", String.valueOf(wordParser.parsingWord(testFile)));

    }
}
