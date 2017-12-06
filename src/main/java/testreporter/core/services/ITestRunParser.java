package testreporter.core.services;

import testreporter.core.models.TestRun;

import java.io.InputStream;

public interface ITestRunParser {
    TestRun parseResult(InputStream resultStream) throws Exception;
}
