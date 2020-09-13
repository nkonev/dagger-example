package name.nkonev.dagger;

import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import javax.sql.DataSource;

@Module
public class JdbcDatasourceModule {

  @Provides @Singleton
  public DataSource datasource() {
    String url = "jdbc:postgresql://localhost:35432/chat?user=chat&password=chatPazZw0rd&ssl=false";
    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl(url);
    return ds;
  }
}
