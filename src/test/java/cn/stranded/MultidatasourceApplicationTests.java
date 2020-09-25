package cn.stranded;

import cn.stranded.entity.User;
import cn.stranded.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class MultidatasourceApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(MultidatasourceApplicationTests.class);

    @Autowired
    private UserService userService;

    /**
     * 写库进行写入
     */
    @Test
    public void testWrite() {
        User user = new User();
        user.setUserName("王五");
        user.setAge(30);
        int insert = userService.insert(user);
        logger.info("插入的ID="+String.valueOf(insert));
    }

    /**
     * 读库（slave1和slave1随机）进行读取
     */
    @Test
    public void testRead() {
        User user = userService.selectByPrimaryKey(1);
        logger.info("user="+user.toString());
    }

    /**
     * 写库进行写入
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("赵六");
        user.setAge(40);
        int save = userService.save(user);
        logger.info("插入的ID="+String.valueOf(save));
    }

    /**
     * 写库进行读取
     */
    @Test
    public void testReadFromMaster() {
        User user = userService.getById(2);
        logger.info("user="+user.toString());
    }

}
