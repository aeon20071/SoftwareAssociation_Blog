package com.my.bbs.dao;

import com.my.bbs.entity.Admin;
import com.my.bbs.entity.BBSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BBSUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(BBSUser record);

    int insertSelective(BBSUser record);

    BBSUser selectByPrimaryKey(Long userId);

    List<BBSUser> selectByPrimaryKeys(@Param("userIds") List<Long> userIds);

    BBSUser selectByLoginName(String loginName);

    BBSUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    int updateByPrimaryKeySelective(BBSUser record);

    int updateByPrimaryKey(BBSUser record);

    Admin getAdmin(@Param("aname") String aname);

    List<BBSUser> getAllUser();

    int changeUserState(@Param("userId") Long userId,@Param("state") int state);

    int delUser(Long userId);

    List<BBSUser> getUserBySearch(@Param("value") String value);
}