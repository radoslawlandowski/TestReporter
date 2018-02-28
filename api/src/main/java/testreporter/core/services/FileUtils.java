package testreporter.core.services;

public class FileUtils {
    public String getFileExtension(String filename) {
        return filename.split("\\.")[1];
    }
}
