import lombok.Getter;

import java.io.File;

@Getter
public class ProcessFileFolders {

    Integer allDocuments = 0;
    Integer allPages = 0;

    private static boolean findObject(String entry) {

        for (FactoryParsers item : FactoryParsers.values()) {
            if (item.name().equals(entry)) {
                return true;
            }
        }
        return false;
    }

    public void setAllDocuments(Integer allDocuments) {
        this.allDocuments = this.allDocuments + allDocuments;
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

        File file = new File(folder);
        File[] folderEntries = file.listFiles();


        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(String.valueOf(entry));
                continue;
            }

            String resultExt = getFileExtension(entry);

            if (findObject(resultExt)) {
                FactoryParsers factoryParsers = FactoryParsers.valueOf(resultExt);
                setAllPages(factoryParsers.getCode(entry));
                countDocuments++;
            }

        }

        setAllDocuments(countDocuments);

    }

}
