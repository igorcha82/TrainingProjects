import java.io.File;


public class ProcessFileFolders {

    Integer allDocuments = 0;
    Integer allPages = 0;

    public Integer getAllDocuments() {
        return allDocuments;
    }

    public void setAllDocuments(Integer allDocuments) {
        this.allDocuments = this.allDocuments + allDocuments;
    }

    public Integer getAllPages() {
        return allPages;
    }

    public void setAllPages(Integer allPages) {
        this.allPages = this.allPages + allPages;
    }


    protected String getFileExtension(File file) {
        String fileName = file.getName();

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)

            return fileName.substring(fileName.lastIndexOf(".") + 1);

        else return "";
    }

    protected void processFilesFromFolder(String folder) {
        int countDocuments = 0;

        WordParser wordParser = new WordParser();
        PdfParser pdfParser = new PdfParser();

        File file = new File(folder);
        File[] folderEntries = file.listFiles();

        try {


            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    processFilesFromFolder(String.valueOf(entry));
                    continue;
                }

                String resultExt = getFileExtension(entry);


                if (resultExt.equals("docx")) {
                    countDocuments++;
                    int countDocxPages = wordParser.parsingWord(entry);

                    setAllPages(countDocxPages);

                }

                if (resultExt.equals("pdf")) {
                    countDocuments++;
                    int countPdf = pdfParser.parsingPdf(entry);

                    setAllPages(countPdf);

                }

            }

            setAllDocuments(countDocuments);

        } catch (NullPointerException ex) {
            ex.getMessage();
        }
    }

}
