import java.util.Date;

public class Arquivo {
  private String name;
  private String user;
  private Date timestamp;

  public Arquivo(String name, String user) {
    this.name = name;
    this.user = user;
    this.timestamp = new Date();
  }

  @Override
  public String toString() {
    return String.format(
        "[ Nome: %s, Usuário: %s, Data de criação: %s ]", this.name, this.user, this.timestamp.toString());
  }
}
