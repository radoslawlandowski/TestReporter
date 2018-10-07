package api.services.unzipper;

import api.model.models.File;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileUnzipper implements IFileUnzipper {

    public static final int BUFFER_SIZE = 1024;

    @Override
    public List<File> unzip(InputStream stream) throws IOException {
        List<File> unzippedFiles = new ArrayList<>();

        byte[] buffer = new byte[BUFFER_SIZE];

        ZipInputStream zis = new ZipInputStream(stream);
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry != null){

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int len;
            while ((len = zis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();

            File file = new File();
            file.setFileName(zipEntry.getName());
            file.setData(os.toByteArray());

            unzippedFiles.add(file);

            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        return unzippedFiles;
    }
}
