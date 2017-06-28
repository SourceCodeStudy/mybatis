package org.mybatis.example.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 *
 */
@Intercepts({
        @Signature(
                type = Executor.class, method = "query",
                args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type = Executor.class, method = "update",
                args = { MappedStatement.class, Object.class})
        })

public class SqlLogPlugin implements Interceptor {
    private Logger logger = Logger.getLogger(getClass());

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        String sql =  mappedStatement.getBoundSql(invocation.getArgs()[1]).getSql();
        logger.debug("sql->"+sql);
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
