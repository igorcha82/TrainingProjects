import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите каталог: ");
        @NotNull
        String dirPath = scanner.next();

        checkDirectory(dirPath);

        ProcessFileFolders processFileFolders = new ProcessFileFolders();
        processFileFolders.processFilesFromFolder(dirPath);

        System.out.println("Documents " + processFileFolders.getAllDocuments());
        System.out.println("Pages " + processFileFolders.getAllPages());

    }

    protected static void checkDirectory(String pathDir) {

        File file = new File(pathDir);

        if (!file.isDirectory()) {
            System.out.println("Введен некорректный путь");
            System.exit(0);
        } else {
            return;
        }
    }

}
