package testreporter.core.FileManager;

import java.io.InputStream;
import java.io.OutputStream;

public interface IFileManager {

    void save(InputStream stream, String fileName);

    void get(String fileName, OutputStream stream);
}
