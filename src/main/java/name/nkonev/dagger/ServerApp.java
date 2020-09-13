package name.nkonev.dagger;

import dagger.Component;
import io.undertow.Undertow;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ServerApp {

  @Singleton
  @Component(
      modules = {
          JdbcDatasourceModule.class,
          UndertowModule.class,
          MyBatisModule.class
      }
  )
  interface Launcher {

    DataSource dataSource();

    Undertow undertow();

    SqlSessionFactory sqlSessionFactory();
  }

  public static void main(String[] args) {
    Launcher launcher = DaggerServerApp_Launcher.builder().build();

    System.out.println("\nNow we select all chat names using plain JDBC");
    DataSource dataSource = launcher.dataSource();
    try {
      try (Connection connection = dataSource.getConnection();) {
        try (Statement statement = connection.createStatement();) {
          try (ResultSet execute = statement.executeQuery("SELECT * FROM chat");) {
            while (execute.next()) {
              String title = execute.getString("title");
              System.out.println(title);
            }
          }
        } catch (SQLException throwable) {
          throwable.printStackTrace();
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    System.out.println("\nNow we select one chat using MyBatis");
    try (SqlSession session = launcher.sqlSessionFactory().openSession()) {
      ChatMapper mapper = session.getMapper(ChatMapper.class);
      Chat chat = mapper.selectChat(2);
      System.out.println(chat);
    }

    launcher.undertow().start();
  }

}
