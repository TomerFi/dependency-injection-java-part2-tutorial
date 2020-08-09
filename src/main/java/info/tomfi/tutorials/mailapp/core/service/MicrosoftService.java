package info.tomfi.tutorials.mailapp.core.service;

import info.tomfi.tutorials.mailapp.core.Mail;
import info.tomfi.tutorials.mailapp.core.MailService;
import info.tomfi.tutorials.mailapp.core.mail.MicrosoftImpl;
import java.util.List;

public final class MicrosoftService implements MailService {
  @Override
  public List<Mail> getMail() {
    // this is where the actual Microsoft api access goes.
    // we'll fake a couple of mails instead.
    var firstFakeMail =
        MicrosoftImpl.builder()
            .from("my.boss@work.info")
            .subject("stop writing tutorials and get back to work!")
            .build();

    var secondFakeMail =
        MicrosoftImpl.builder()
            .from("next.door.neighbor@kibutz.org")
            .subject("do you have a star screwdriver?")
            .build();

    return List.of(firstFakeMail, secondFakeMail);
  }
}
