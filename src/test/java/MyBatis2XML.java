import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.example.BlogMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 使用配置文件操作mybatis
 * @author Roger
 * @version 1.0.0 2017/6/25
 */
public class MyBatis2XML {

    @Test
    public void testCountAll() {

        SqlSession sqlSession = getSqlSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Long totalCount = blogMapper.countAll();
        }finally {
             sqlSession.close();
        }
    }

    @Test
    public void testCountBlogById(){
        SqlSession sqlSession = getSqlSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Long totalCount = blogMapper.countBlogById("1");
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 获得SqlSession对象
     * @return SqlSession
     */
    public  SqlSession getSqlSession() {

        String resource = "mybatis-config.xml";
        InputStream inputStream = MyBatis2XML.class.getResourceAsStream(resource);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,getProperties());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /**
     * 获取属性配置文件
     * @return Properties
     */
    public  Properties getProperties(){

        Properties pro = new Properties();

        try {
            String resource = "config.properties";
            InputStream inputStream = MyBatis2XML.class.getResourceAsStream(resource);
            pro.load(inputStream);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }


}
