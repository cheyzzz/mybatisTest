package com.chey.mapper;

/**
 * @Author chey
 * @Date 2022-07-18 18:49
 * @Describe  mybatis面向接口编程
 *              映射文件mapper.xml的namespace要和mapper接口全类名保持一致
 *              映射文件mapper.xml的sql语句id要和mapper接口中的方法名一致
 */
public interface CityMapper {

    int insertCity();

}
