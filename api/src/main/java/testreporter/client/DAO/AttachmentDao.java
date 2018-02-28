package testreporter.client.DAO;

import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.installer.feature.eager.EagerSingleton;
import testreporter.core.models.File;

@EagerSingleton
public class AttachmentDao extends AbstractDAO<File> {

    @Inject
    public AttachmentDao(SessionFactory factory) {
        super(factory);
    }

    public File getAttachment(Integer attachmentId) {
        return get(attachmentId);
    }
}
