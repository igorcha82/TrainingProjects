
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class ProcessFileFoldersTest {


    @Test
    public void testParserPdf () throws IOException {

        File testFile = File.createTempFile("test",".pdf");


        ProcessFileFolders processFileFolders = new ProcessFileFolders();
        assertEquals("pdf", processFileFolders.getFileExtension(testFile));

    }

}
