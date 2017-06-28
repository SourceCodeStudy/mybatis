package org.mybatis.example.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * 自定义字符串解析器
 */
@MappedJdbcTypes(VARCHAR)
@MappedTypes(String.class)
public class ExampleTypeHandler extends BaseTypeHandler {

    private Logger logger = Logger.getLogger(getClass());

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

        logger.debug("=======================================");

        preparedStatement.setString(i,o.toString().concat("测试"));



    }

    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {

        logger.debug("=======================================");

        return null;
    }

    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
