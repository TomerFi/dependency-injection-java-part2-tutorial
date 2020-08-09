package info.tomfi.tutorials.mailapp.core.service;

import info.tomfi.tutorials.mailapp.core.Mail;
import info.tomfi.tutorials.mailapp.core.MailService;
import info.tomfi.tutorials.mailapp.core.mail.GmailImpl;
import java.util.List;

public final class GmailService implements MailService {
  @Override
  public List<Mail> getMail() {
    // this is where the actual Gmail api access goes.
    // we'll fake a couple of mails instead.
    var firstFakeMail =
        GmailImpl.builder()
            .from("a.cool.friend@gmail.com")
            .subject("wanna get together and write some code?")
            .build();

    var secondFakeMail =
        GmailImpl.builder()
            .from("an.annoying.salesman@some.company.com")
            .subject("wanna buy some stuff?")
            .build();

    return List.of(firstFakeMail, secondFakeMail);
  }
}
