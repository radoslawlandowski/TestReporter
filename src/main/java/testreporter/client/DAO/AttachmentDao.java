package testreporter.client.DAO;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import testreporter.core.models.ResultFile;
import testreporter.core.models.TestGroup;

public class AttachmentDao extends AbstractDAO<ResultFile> {

    public AttachmentDao(SessionFactory factory) {
        super(factory);
    }

    public ResultFile getAttachment(Integer attachmentId) {
        return get(attachmentId);
    }
}
