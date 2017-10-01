package testreporter.core.FileManager;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileManager implements IFileManager {

    protected Path basePath;

    public FileManager(String basePath) {
        this.basePath = Paths.get(basePath);

        if(!Files.exists(this.basePath)) {
            try {
                Files.createDirectory(this.basePath.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(InputStream stream, String fileName) {
        String filePath = Paths.get(this.basePath.toString(), fileName).toString();

        File targetFile = new File(filePath);

        try {
            Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IOUtils.closeQuietly();
    }

    @Override
    public void get(String name, OutputStream stream) {

        try {
            Path filePath = Paths.get(this.basePath.toString(), name);

            Files.copy(filePath, stream);

            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
