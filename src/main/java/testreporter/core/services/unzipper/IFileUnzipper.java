package testreporter.core.services.unzipper;

import testreporter.core.models.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileUnzipper {
    List<File> unzip(InputStream stream) throws IOException;
}
