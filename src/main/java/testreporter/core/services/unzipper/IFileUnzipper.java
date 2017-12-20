package testreporter.core.services.unzipper;

import testreporter.core.models.ResultFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileUnzipper {
    List<ResultFile> unzip(InputStream stream) throws IOException;
    List<ResultFile> findByName(List<ResultFile> resultFiles, String filename);
    List<ResultFile> findByExtension(List<ResultFile> resultFiles, String extension);
}
