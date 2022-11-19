package com.erent.mapper;

import com.erent.pojo.House;
import com.erent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 根据用户名和密码查询用户对象
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username,
                @Param("password")  String password);

    /**
     * 根据id返回用户信息
     * @param id
     * @return
     */
    @Select("select * from tb_user where id = #{id};")
    User selectById(int id);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into tb_user values (null, #{username},#{password},#{type},#{addr},#{phone},#{gender},#{birth})")
    void add(User user);

    /**
     * 根据用户名查查询
     * @param username
     * @return
     */
    @Select("select * from tb_user where username = #{username};")
    User selectByUsername(String username);
}
