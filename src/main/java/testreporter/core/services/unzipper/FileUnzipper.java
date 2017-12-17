package testreporter.core.services.unzipper;

import testreporter.core.models.ResultFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUnzipper implements IFileUnzipper {

    public static final int BUFFER_SIZE = 1024;

    @Override
    public List<ResultFile> unzip(InputStream stream) throws IOException {
        List<ResultFile> unzippedFiles = new ArrayList<>();

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

            ResultFile file = new ResultFile();
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
