package testreporter.core.FileManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class FileManagerTest {

    private File testEnvironment;

    @Before
    public void Before() {
        testEnvironment = new File(new File(".").getAbsolutePath() + "/testEnv");
        testEnvironment.mkdir();
    }

    @Ignore
    @Test
    public void SavingFileShouldExistAndHaveProperContent() {
        String path = testEnvironment.getAbsolutePath();
        String fileName = "radek.txt";
        String fileContent = "some test data for my input stream";

        InputStream stubInputStream = null;
        try {
            stubInputStream = IOUtils.toInputStream(fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        IFileManager manager = new FileManager(path);
        manager.save(stubInputStream, fileName);

        File outputFile = new File(Paths.get(path, fileName).toString());
        String content = null;
        try {
            content = new String(Files.readAllBytes(outputFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(outputFile.exists()).isTrue();
        assertThat(content).contains(fileContent);
    }

    @Ignore
    @Test
    public void ReadingFileShouldGiveProperContent() {
        String path = testEnvironment.getAbsolutePath();
        String fileName = "radek.txt";
        String content = "test";

        try(PrintWriter out = new PrintWriter(Paths.get(path, fileName).toString())){
            out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        IFileManager manager = new FileManager(path);
        manager.get(fileName, stream);

        String data = new String(stream.toString());
        assertThat(data).contains(content);
    }

    @After
    public void After() {
        try {
            FileUtils.deleteDirectory(testEnvironment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
