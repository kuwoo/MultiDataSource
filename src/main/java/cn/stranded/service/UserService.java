package cn.stranded.service;

import cn.stranded.annotation.Master;
import cn.stranded.entity.User;
import cn.stranded.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Master
    public int save(User user) {
        return userMapper.insert(user);
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Master
    public User getById(Integer id) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return userMapper.selectByPrimaryKey(id);
    }
}
