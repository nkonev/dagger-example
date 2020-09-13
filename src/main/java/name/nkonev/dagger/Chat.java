package name.nkonev.dagger;

import java.util.Objects;

public class Chat {
  private Long id;
  private String title;

  public Chat() {
  }

  public Chat(Long id, String title) {
    this.id = id;
    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chat chat = (Chat) o;
    return Objects.equals(id, chat.id) &&
        Objects.equals(title, chat.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title);
  }

  @Override
  public String toString() {
    return "Chat{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }
}
