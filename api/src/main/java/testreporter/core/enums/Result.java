package testreporter.core.enums;

public enum Result {
    Passed("Passed"),
    Failed("Failed"),
    Skipped("Skipped"),
    Inconclusive("Inconclusive");

    private String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
