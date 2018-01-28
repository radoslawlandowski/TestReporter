package testreporter.client.DAO;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import testreporter.core.models.File;

public class AttachmentDao extends AbstractDAO<File> {

    public AttachmentDao(SessionFactory factory) {
        super(factory);
    }

    public File getAttachment(Integer attachmentId) {
        return get(attachmentId);
    }
}
