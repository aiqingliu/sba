package com.sba;

import com.sba.model.excel.ExcelModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.management.ObjectName;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ExcelModel xm;

    @Test
    void contextLoads() {
    }

    @Test
    void test_con_jdbc() throws SQLException {
        // 查看数据源
        System.out.println(dataSource.getClass());
        // 获得数据库连接
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        // 使用 template 模板
        String s1 = conn.nativeSQL("select userid, username, orgid, idcard from sys_users where userid = '010001'");
        System.out.println(s1);
        List<Map<String, Object>> ListMap = jdbcTemplate.queryForList(s1);
        for (Map<String, Object> m : ListMap) {
            for (Map.Entry<String, Object> me : m.entrySet()) {
                System.out.println(me.getKey() + ":" + me.getValue().toString());
            }
        }
        // 关闭连接
        conn.close();
    }

    @Test
    void testYml() {
        System.out.println(xm);
    }

}
