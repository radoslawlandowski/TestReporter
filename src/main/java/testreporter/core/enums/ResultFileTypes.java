package testreporter.core.enums;

import testreporter.core.exceptions.IncorrectResultFileFormat;

import java.util.Arrays;

public enum ResultFileTypes {
    ZIPPED("zip"),
    RAW("xml");

    private final String resultsFileExtension;

    ResultFileTypes(String resultsFileExtension) {
        this.resultsFileExtension = resultsFileExtension;
    }

    public static ResultFileTypes getResultFileType(String resultsFileExtension) throws IncorrectResultFileFormat {

        ResultFileTypes type = Arrays.stream(ResultFileTypes.values())
                .filter(value -> value.resultsFileExtension.equals(resultsFileExtension))
                .findFirst()
                .orElseThrow(() -> new IncorrectResultFileFormat("The provided file has an unsupported extension!"));

        return type;
    }
}
