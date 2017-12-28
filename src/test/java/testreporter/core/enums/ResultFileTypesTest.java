package testreporter.core.enums;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testreporter.core.exceptions.IncorrectResultFileFormat;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Enclosed.class)
public class ResultFileTypesTest {

    @RunWith(Parameterized.class)
    public static class CorrectResultFilenamesVerification {

        @Parameterized.Parameters(name = "{index}: type {1} for file extension {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { "file.zip", ResultFileTypes.ZIPPED },
                    { "file.xml", ResultFileTypes.RAW }
            });
        }

        protected String fileExtension;
        protected ResultFileTypes resultFileType;

        public CorrectResultFilenamesVerification(String fileExtension, ResultFileTypes resultFileType) {
            this.fileExtension = fileExtension;
            this.resultFileType = resultFileType;
        }

        @Test
        public void checkIfReturnedParserClassIsCorrect() {
            ResultFileTypes type = null;
            try {
                type = ResultFileTypes.getResultFileType(this.fileExtension);
            } catch(IncorrectResultFileFormat e) {
                fail("Getting result file type returned an unexpected exception!");
            }

            assertThat(type).isEqualTo(this.resultFileType);
        }
    }

    public static class IncorrectResultFilenamesVerification {

        @Test
        public void checkIfGettingUnhandledTypeThrowsException() {

            String incorrectExtension = "file.asd";

            try {
                ResultFileTypes.getResultFileType(incorrectExtension);
                fail(String.format("Expected %s to be thrown!", IncorrectResultFileFormat.class.getName()));
            } catch(IncorrectResultFileFormat e) {
                assertThat(e.getMessage()).isEqualTo("The provided file has an unsupported extension!");
            }

        }
    }

    public static class CorrectExceptionalResultFilenamesVerification {

        @Test
        public void checkIfGettingExtensionOfMultipleDottedFilenameWorksProperly() {

            String incorrectExtension = "file.multiple.dots.asd";

            try {
                ResultFileTypes.getResultFileType(incorrectExtension);
                fail(String.format("Expected %s to be thrown!", IncorrectResultFileFormat.class.getName()));
            } catch(IncorrectResultFileFormat e) {
                assertThat(e.getMessage()).isEqualTo("The provided file has an unsupported extension!");
            }

        }
    }
}
