package testreporter.core.enums;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestRunParserTypesTest {

    @Parameterized.Parameters(name = "{index}: runner {1} for file extension {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "zip", TestRunParserTypes.ZIPPED },
                { "xml", TestRunParserTypes.RAW }
        });
    }

    protected String fileExtension;
    protected TestRunParserTypes parserType;

    public TestRunParserTypesTest(String fileExtension, TestRunParserTypes parserType) {
        this.fileExtension = fileExtension;
        this.parserType = parserType;
    }

    @Test
    public void checkIfReturnedParserClassIsCorrect() {
        TestRunParserTypes type = TestRunParserTypes.getParserTypeForFile(this.fileExtension);

        assertThat(type).isEqualTo(this.parserType);
    }

}
