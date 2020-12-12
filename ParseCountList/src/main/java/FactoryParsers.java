import java.io.File;

public enum FactoryParsers {

    docx("docx") {
        @Override
        public Integer getCode(File file) {
            WordParser wordParser = new WordParser();
            int countDocxPages = wordParser.parsingWord(file);
            return countDocxPages;
        }
    },

    pdf("pdf") {
        @Override
        public Integer getCode(File file) {
            PdfParser pdfParser = new PdfParser();
            int countPdf = pdfParser.parsingPdf(file);
            return countPdf;
        }
    };

    private String parserChoice;

    FactoryParsers(String parserChoice) {
        this.parserChoice = parserChoice;
    }

    public abstract Integer getCode(File file);

}