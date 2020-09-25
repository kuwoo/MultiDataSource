package cn.stranded.mapper;

import cn.stranded.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);

}
