package info.tomfi.tutorials.mailapp;

import static com.google.inject.multibindings.Multibinder.newSetBinder;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.willReturn;

import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Scopes;
import info.tomfi.tutorials.mailapp.core.MailEngine;
import info.tomfi.tutorials.mailapp.core.MailService;
import info.tomfi.tutorials.mailapp.core.mail.GmailImpl;
import info.tomfi.tutorials.mailapp.core.mail.MicrosoftImpl;
import info.tomfi.tutorials.mailapp.engine.RobustMailEngine;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class MailCollectorAppTest extends AbstractModule {
  private MailService gmailServiceMock;
  private MailService microsoftServiceMock;
  private MailService thirdServiceMock;

  private MailCollectorApp sut;

  private Faker faker;

  @Override
  public void configure() {
    var listBinder = newSetBinder(binder(), MailService.class);
    listBinder.addBinding().toInstance(gmailServiceMock);
    listBinder.addBinding().toInstance(microsoftServiceMock);
    listBinder.addBinding().toInstance(thirdServiceMock);

    bind(MailEngine.class).to(RobustMailEngine.class).in(Scopes.SINGLETON);
  }

  @BeforeEach
  public void initialize() {
    faker = new Faker();

    gmailServiceMock = mock(MailService.class);
    microsoftServiceMock = mock(MailService.class);
    thirdServiceMock = mock(MailService.class);

    var injector = Guice.createInjector(this);

    sut = injector.getInstance(MailCollectorApp.class);
  }

  @Test
  @DisplayName(
      "make the services mocks return no mail and validate the return string as 'No mail found'")
  public void getMail_noMailExists_returnsNoMailFound() {
    willReturn(emptyList()).given(gmailServiceMock).getMail();
    willReturn(emptyList()).given(microsoftServiceMock).getMail();
    willReturn(emptyList()).given(thirdServiceMock).getMail();

    then(sut.getMail()).isEqualTo("No mail found.");
  }

  @Test
  @DisplayName(
      "make the services return legitimate mail and validate the return string as expected")
  public void getMail_foundMail_returnsExpectedString() {
    var mail1 =
        GmailImpl.builder()
            .from(faker.internet().emailAddress())
            .subject(faker.lorem().sentence())
            .build();
    var mail2 =
        MicrosoftImpl.builder()
            .from(faker.internet().emailAddress())
            .subject(faker.lorem().sentence())
            .build();
    var mail3 =
        MicrosoftImpl.builder()
            .from(faker.internet().emailAddress())
            .subject(faker.lorem().sentence())
            .build();

    willReturn(List.of(mail1)).given(gmailServiceMock).getMail();
    willReturn(List.of(mail2, mail3)).given(microsoftServiceMock).getMail();
    willReturn(emptyList()).given(thirdServiceMock).getMail();

    then(sut.getMail().split(System.lineSeparator()))
        .containsOnly(mail1.toString(), mail2.toString(), mail3.toString());
  }
}
