package testreporter.core.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testreporter.core.enums.ResultFileTypes;
import testreporter.core.services.deserializer.TestRunDeserializer;
import testreporter.core.services.parser.RawTestRunParser;
import testreporter.core.services.parser.TestRunParserFactory;
import testreporter.core.services.parser.ZippedTestRunParser;
import testreporter.core.services.unzipper.FileUnzipper;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class TestRunParserFactoryTest {

    @Parameterized.Parameters(name = "{index}: runner {1} for runner type {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { ZippedTestRunParser.class, ResultFileTypes.ZIPPED },
                { RawTestRunParser.class, ResultFileTypes.RAW }
        });
    }

    protected Class parser;
    protected ResultFileTypes parserType;

    public TestRunParserFactoryTest(Class parser, ResultFileTypes parserType) {
        this.parser = parser;
        this.parserType = parserType;
    }

    @Test
    public void checkIfReturnedParserIsOfCorrectInstance() {
        TestRunParserFactory factory = new TestRunParserFactory(new TestRunDeserializer(), new FileUnzipper());

        assertThat(factory.create(this.parserType)).isInstanceOf(this.parser);
    }
}
