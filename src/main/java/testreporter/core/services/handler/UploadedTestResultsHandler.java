package testreporter.core.services.handler;

import org.apache.commons.io.IOUtils;
import testreporter.core.enums.ResultFileTypes;
import testreporter.core.models.File;
import testreporter.core.models.TestResults;
import testreporter.core.services.unzipper.FileUnzipper;
import testreporter.core.services.validator.UploadedFilesValidator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UploadedTestResultsHandler {

    protected UploadedFilesValidator uploadedFilesValidator;
    protected FileUnzipper fileUnzipper;

    public UploadedTestResultsHandler(UploadedFilesValidator uploadedFilesValidator, FileUnzipper fileUnzipper) {
        this.uploadedFilesValidator = uploadedFilesValidator;
        this.fileUnzipper = fileUnzipper;
    }

    public TestResults getTestResult(InputStream is, ResultFileTypes resultFileType) throws IOException {
        List<File> files = getFiles(is, resultFileType);
        File xmlFile = findByExtension(files, "xml").get(0);

        files.remove(xmlFile);

        return new TestResults(xmlFile, files);
    }

    public List<File> getFiles(InputStream is, ResultFileTypes resultFileType) throws IOException {

        List<File> files = null;

        switch (resultFileType) {
            case RAW: {
                File xmlFile = new File();
                xmlFile.setData(IOUtils.toByteArray(is));
                xmlFile.setFileName("file.xml");
                files = new ArrayList<>(Arrays.asList(xmlFile));
                break;
            }
            case ZIPPED: {
                files = fileUnzipper.unzip(is);
                break;
            }
        }

        validate(files);

        return files;
    }

    public boolean validate(List<File> files) {
        return uploadedFilesValidator.isValid(files);
    }

    public List<File> findByExtension(List<File> files, String extension) {
        return files.stream().filter(file -> Arrays.stream(file.getFileName().split("\\."))
                .reduce((first, last) -> last).get().equals(extension))
                .collect(Collectors.toList());
    }

}
