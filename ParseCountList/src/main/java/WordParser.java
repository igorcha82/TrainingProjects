import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

public class WordParser {

    protected Integer parsingWord(File entry) {

        int countPages = 0;

        try {

            XWPFDocument word = new XWPFDocument(new FileInputStream(entry));
            countPages = word.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();

        } catch (Exception ex) {
            ex.getMessage();
        }
        return countPages;
    }

}
