package info.tomfi.tutorials.mailapp.core.mail;

import static com.google.common.base.Strings.emptyToNull;
import static java.util.Objects.requireNonNull;

import info.tomfi.tutorials.mailapp.core.Mail;
import info.tomfi.tutorials.mailapp.core.MailSource;

public final class MicrosoftImpl extends Mail {
  private final String setFrom;
  private final String setSubject;

  private MicrosoftImpl(final String from, final String subject) {
    setFrom = from;
    setSubject = subject;
  }

  @Override
  public String from() {
    return setFrom;
  }

  @Override
  public String subject() {
    return setSubject;
  }

  @Override
  public MailSource source() {
    return MailSource.MICROSOFT;
  }

  public static MicrosoftImpl.Builder builder() {
    return new MicrosoftImpl.Builder();
  }

  public static final class Builder {
    private String prepFrom;
    private String prepSubject;

    public Builder from(final String setFrom) {
      prepFrom = setFrom;
      return this;
    }

    public Builder subject(final String setSubject) {
      prepSubject = setSubject;
      return this;
    }

    public MicrosoftImpl build() {
      requireNonNull(emptyToNull(prepFrom), "from cannot be empty or null");
      requireNonNull(emptyToNull(prepSubject), "subject cannot be empty or null");

      return new MicrosoftImpl(prepFrom, prepSubject);
    }
  }
}
