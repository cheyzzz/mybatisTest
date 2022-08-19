package com.chey.mapper;

import com.chey.pojo.City;
import com.chey.pojo.Person;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chey
 * @Date 2022-07-19 14:37
 * @Describe  面向接口编程
 */
public interface CityMapper {

    List<City> queryCityList();
    List<City> queryPersonThenCityByStep(@Param("id") Integer id);

    List<City> queryCityAndPerson(@Param("id") Integer id);
    List<City> queryCityThenPerson(@Param("id") Integer id);
}
