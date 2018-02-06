package testreporter.core.services.filter.criteria;

import testreporter.client.DAO.AttachmentDao;
import testreporter.core.models.TestCase;

import java.util.List;
import java.util.stream.Collectors;

public class AttachmentPropertyCriteria extends PropertyCriteria {

    public final static String ATTACHMENT_PROPERTY_NAME = "Attachment";

    @Override
    public List<TestCase> meetCriteria(List<TestCase> testCases) {
        List<TestCase> testCasesWithProperties = super.meetCriteria(testCases);

        return testCasesWithProperties.stream()
                .filter(testCaseWithProps -> testCaseWithProps
                    .getProperties()
                    .stream()
                    .anyMatch(property -> property.getName().equals(ATTACHMENT_PROPERTY_NAME)))
                .collect(Collectors.toList());
    }
}
