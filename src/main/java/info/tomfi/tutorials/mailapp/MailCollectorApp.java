package info.tomfi.tutorials.mailapp;

import com.google.common.base.Joiner;
import com.google.inject.Guice;
import com.google.inject.Inject;
import info.tomfi.tutorials.mailapp.core.MailEngine;

public final class MailCollectorApp {
  private MailEngine engine;

  @Inject
  public MailCollectorApp(final MailEngine setEngine) {
    engine = setEngine;
  }

  public String getMail() {
    var ret = "No mail found.";
    if (!engine.getAllMail().isEmpty()) {
      ret = Joiner.on(System.lineSeparator()).join(engine.getAllMail());
    }
    return ret;
  }

  public static void main(final String... args) {
    var injector = Guice.createInjector(new DIModule());

    var app = injector.getInstance(MailCollectorApp.class);

    System.out.println(app.getMail());
  }
}
