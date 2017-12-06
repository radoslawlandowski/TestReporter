package testreporter.core.services;

import testreporter.core.IXmlDeserializer;
import testreporter.core.models.ResultFile;
import testreporter.core.models.TestRun;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IZippedTestRunParser extends TestRunParser {

    public final static String XML_EXTENSION = ".xml";

    public IZippedTestRunParser(IXmlDeserializer deserializer) {
        super(deserializer);
    }

    @Override
    public TestRun parseResult(InputStream resultStream) throws IOException, JAXBException {
        List<ResultFile> unzippedFiles = unzip(resultStream);

        ResultFile resultFile = findResultFile(unzippedFiles);

        TestRun testRun = super.deserialize(new ByteArrayInputStream(resultFile.getData()));

        testRun.getTestSuite().getTestSuites().get(0).getTestCases().get(0).setResultFile(unzippedFiles.get(1));

        return testRun;
    }

    private List<ResultFile> unzip(InputStream resultStream) throws IOException {
        List<ResultFile> unzippedFiles = new ArrayList<>();
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(resultStream);
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry != null){
            String entryName = zipEntry.getName();

            ResultFile image = new ResultFile();
            image.setFileName(entryName);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int len;
            while ((len = zis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();
            image.setData(os.toByteArray());
            unzippedFiles.add(image);

            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        return unzippedFiles;
    }

    private ResultFile findResultFile(List<ResultFile> streams) {
        return streams.stream().filter(item -> item.getFileName().contains(XML_EXTENSION)).collect(Collectors.toList()).get(0);
    }
}
