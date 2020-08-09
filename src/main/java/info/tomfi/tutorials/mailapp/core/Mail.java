package info.tomfi.tutorials.mailapp.core;

public abstract class Mail {
  public abstract String from();

  public abstract String subject();

  public abstract MailSource source();

  @Override
  public String toString() {
    return String.format(
        "Got mail by %s, from %s, with the subject %s", source(), from(), subject());
  }
}
