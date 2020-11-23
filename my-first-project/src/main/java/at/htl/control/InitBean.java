package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class InitBean {

    // private final Logger logger = Logger.getLogger(InitBean.class.getSimpleName())

    @Inject
    Logger LOGGER;

    @Inject
    GreetingService greetingService;

    public InitBean() {
    }

    @PostConstruct
    void init() {
        LOGGER.info(greetingService.greeting("Susi"));
    }

    void onStart(@Observes StartupEvent ev) {
        LOGGER.error("The application is starting...");
    }
}
