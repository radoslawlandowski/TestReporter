package testreporter.core.enums;

import java.util.Arrays;

public enum TestRunParserTypes {
    ZIPPED("zip"),
    RAW("xml");

    private final String resultsFileExtension;

    TestRunParserTypes(String resultsFileExtension) {
        this.resultsFileExtension = resultsFileExtension;
    }

    public static TestRunParserTypes getParserTypeForFile(String resultsFileExtension) {

        TestRunParserTypes type = Arrays.stream(TestRunParserTypes.values())
                .filter(value -> value.resultsFileExtension.equals(resultsFileExtension))
                .findFirst()
                .get();

        return type;
    }
}
