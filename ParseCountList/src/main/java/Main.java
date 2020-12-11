import org.jetbrains.annotations.NotNull;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите каталог: ");
        @NotNull
        String dirPath = scanner.next();


        ProcessFileFolders processFileFolders = new ProcessFileFolders();
        processFileFolders.processFilesFromFolder(dirPath);

        System.out.println("Documents " + processFileFolders.getAllDocuments());
        System.out.println("Pages " + processFileFolders.getAllPages());


    }

}
