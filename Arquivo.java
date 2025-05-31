import java.util.Date;

public class Arquivo {
  private String name;
  private String user;
  private Boolean printed = false;
  private Date timestamp;

  public Arquivo(String name, String user) {
    this.name = name;
    this.user = user;
    this.timestamp = new Date();
  }

  public String getName() {
    return name;
  }

  public String getUser() {
    return user;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public Boolean isPrinted() {
    return printed;
  }

  public void setPrinted(Boolean printed) {
    this.printed = printed;
  }

  @Override
  public String toString() {
    return String.format(
        "[ Nome: %s, Usuário: %s, Data de criaçao: %s ]", this.name, this.user, this.timestamp.toString());
  }
}
