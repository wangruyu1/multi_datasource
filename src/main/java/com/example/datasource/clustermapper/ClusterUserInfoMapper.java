package com.example.datasource.clustermapper;

import com.example.datasource.entity.UserInfo;

public interface ClusterUserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 16 19:17:56 CST 2017
     */
    int updateByPrimaryKey(UserInfo record);
}