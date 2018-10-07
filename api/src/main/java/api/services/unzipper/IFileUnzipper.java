package api.services.unzipper;

import api.model.models.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IFileUnzipper {
    List<File> unzip(InputStream stream) throws IOException;
}
