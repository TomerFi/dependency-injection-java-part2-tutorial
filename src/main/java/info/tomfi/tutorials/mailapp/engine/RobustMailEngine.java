package info.tomfi.tutorials.mailapp.engine;

import static java.util.stream.Collectors.toList;

import com.google.inject.Inject;
import info.tomfi.tutorials.mailapp.core.Mail;
import info.tomfi.tutorials.mailapp.core.MailEngine;
import info.tomfi.tutorials.mailapp.core.MailService;
import java.util.List;
import java.util.Set;

public final class RobustMailEngine implements MailEngine {
  private final Set<MailService> mailServices;

  @Inject
  public RobustMailEngine(final Set<MailService> setMailSerices) {
    mailServices = setMailSerices;
  }

  @Override
  public List<Mail> getAllMail() {
    return mailServices.stream().map(MailService::getMail).flatMap(List::stream).collect(toList());
  }
}
