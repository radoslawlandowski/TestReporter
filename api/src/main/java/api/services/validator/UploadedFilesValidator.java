package api.services.validator;

import api.model.models.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadedFilesValidator {

    public boolean isValid(List<File> files) {
        List<String> fileNames = new ArrayList<>();
        files.forEach(file -> fileNames.add(file.getFileName()));

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
