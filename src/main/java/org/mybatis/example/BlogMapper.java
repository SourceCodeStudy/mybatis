package org.mybatis.example;

import org.apache.ibatis.annotations.Select;

/**
 * 操作数据对应的Mapper
 * @author Roger
 * @version 1.0.0 2017/6/25
 */
public interface BlogMapper {

    /**
     * 通过XML方式
     * @return
     */
    Long countAll();

    /**
     * 通过注解方式
     * @param id
     * @return
     */
    @Select("SELECT count(*) FROM blog WHERE id = #{id}")
    Long countBlogById(String id);
}
