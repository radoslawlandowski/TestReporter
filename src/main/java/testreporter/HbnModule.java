package testreporter;

import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;

public class HbnModule extends AbstractModule {

    private final HbnBundle hbnBundle;

    public HbnModule(HbnBundle hbnBundle) {
        this.hbnBundle = hbnBundle;
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
    }
}
