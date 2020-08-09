package info.tomfi.tutorials.mailapp;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import info.tomfi.tutorials.mailapp.core.MailEngine;
import info.tomfi.tutorials.mailapp.core.MailService;
import info.tomfi.tutorials.mailapp.core.service.GmailService;
import info.tomfi.tutorials.mailapp.core.service.MicrosoftService;
import info.tomfi.tutorials.mailapp.engine.RobustMailEngine;
import java.util.Set;

public final class DIModule extends AbstractModule {
  @Provides
  static Set<MailService> getServices() {
    return Set.of(new GmailService(), new MicrosoftService());
  }

  @Provides
  @Singleton
  static MailEngine getEngine(final Set<MailService> services) {
    return new RobustMailEngine(services);
  }
}
