package api.services;

public class FileUtils {
    public String getFileExtension(String filename) {
        return filename.split("\\.")[1];
    }
}
