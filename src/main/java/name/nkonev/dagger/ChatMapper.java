package name.nkonev.dagger;

import org.apache.ibatis.annotations.Select;

public interface ChatMapper {
  @Select("SELECT * FROM chat WHERE id = #{id}")
  Chat selectChat(int id);
}
