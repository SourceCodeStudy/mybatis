# Mybatis

What is MyBatis?  
MyBatis is a first class persistence framework with support for custom SQL, stored procedures and advanced mappings.   
MyBatis eliminates almost all of the JDBC code and manual setting of parameters and retrieval of results.   
MyBatis can use simple XML or Annotations for configuration and map primitives, Map interfaces and Java POJOs (Plain Old Java Objects) to database records.   
MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。     
MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。    
MyBatis 可以对配置和原生Map使用简单的 XML 或注解，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

SqlSessionFactory\SqlSession\Mapper推荐作用域    

作用域                      Scope      
SqlSessionFactoryBuiler     method    
SqlSessionFactory           application    
SqlSession                  request/method(线程级)    
Mapper                      method    





