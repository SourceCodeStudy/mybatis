import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.junit.Test;
import org.mybatis.example.entity.Blog;
import org.mybatis.example.handler.ExampleTypeHandler;
import org.mybatis.example.mapper.BlogMapper;
import org.mybatis.example.plugins.SqlLogPlugin;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * 使用JAVA代码操作mybatis
 * @author Roger
 * @version 1.0.0 2017/6/25
 */
public class MyBatis2JAVA {

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
    public void testInsertBlog(){

        SqlSession sqlSession = getSqlSession();
        try {
            BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = new Blog();
            blog.setId(UUID.randomUUID().toString().replaceAll("-",""));
            blog.setName("Test");
            blogMapper.insert(blog);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 获得SqlSession对象
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {

        //DataSource dataSource = getDataSource();

        // 取得数据源
        DataSource dataSource = getDataSourceByProperties();

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        // 自定义TypeHandler
        configuration.getTypeHandlerRegistry().register(new ExampleTypeHandler());
        // 自定义拦截器
        configuration.addInterceptor(new SqlLogPlugin());

       // configuration.setLazyLoadingEnabled();
        //  configuration.setProxyFactory(new CglibProxyFactory());
//org.apache.ibatis.executor.resultset.DefaultResultSetHandler#getPropertyMappingValue
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /**
     * Java 中配置datasource
     * @return DataSource
     */
    public static DataSource getDataSourceByJava() {
        DruidDataSource dataSource =  new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;

    }

    /**
     * 通过配置取得DataSource
     * @return DataSource
     */
    public static DataSource getDataSourceByProperties() {
        String resource = "application.properties";
        InputStream inputStream = MyBatis2JAVA.class.getResourceAsStream(resource);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
