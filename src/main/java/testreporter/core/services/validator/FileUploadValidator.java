package testreporter.core.services.validator;

import testreporter.core.models.ResultFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUploadValidator {

    public boolean isValid(List<ResultFile> resultFiles) {
        List<String> fileNames = new ArrayList<>();
        resultFiles.forEach(file -> fileNames.add(file.getFileName()));

        return areFilesDistinct(fileNames) && hasOnlyOneXmlFile(fileNames);
    }

    protected boolean areFilesDistinct(List<String> fileNames) {
        Integer filesCount = fileNames.size();
        Integer distinctFilesCount = fileNames.stream().distinct().collect(Collectors.toList()).size();

        return distinctFilesCount == filesCount;
    }

    protected boolean hasOnlyOneXmlFile(List<String> fileNames) {
        return fileNames.stream().filter(fileName -> fileName.contains(".xml")).collect(Collectors.toList()).size() == 1;
    }

}
