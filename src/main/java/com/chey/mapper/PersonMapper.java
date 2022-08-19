package com.chey.mapper;

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
public interface PersonMapper {
    int insertPerson();
    int updatePerson();
    int deletePerson();
    Person queryPerson();
    List<Person> queryPersonList();

    Person queryPersonByName(String name);
    Person queryPersonByNameAndAge(String name,Integer age);
    Person queryPersonByMap(HashMap hashMap);
    Person queryPersonByClass(Person person);
    Person queryPersonByParam(@Param("userName") String name,@Param("ageNum") Integer age);
    Map<String,Object> queryPersonByIdToMap(@Param("id") Integer id);
    Integer queryPersonCountById(@Param("id") Integer id);
    List<Map<String,Object>> queryPersonMapListByAge(@Param("age") Integer age);
    @MapKey("id")
    Map<String,Object> queryPersonMapKeyByAge(@Param("age") Integer age);
    List<Person> queryPersonUseLike(@Param("age") Integer age);
    void deletePersonBatch(@Param("ages") String ages);
    List<Map<String,Object>> setTableName(@Param("tableName") String tableName);


    int insertPersonByParam(Person person);
    int insertPersonAutoByParam(Person person);
    List<Person> queryPersonAndCity(@Param("id") Integer id);
    List<Person> queryPersonAndCityAsso(@Param("id") Integer id);
    List<Person> queryPersonThenCity(@Param("id") Integer id);
    List<Person> queryCityThenPersonByStep(@Param("id") Integer id);


    List<Person> queryPersonListDynamicUseTest(Person person);
    List<Person> queryPersonListDynamicUseWhere(Person person);
    List<Person> queryPersonListDynamicUseTrim(Person person);
    List<Person> queryPersonListDynamicUsechoose(Person person);
    int queryPersonListDynamicUseforeachDel1(@Param("ids") Integer[] ids);
    int queryPersonListDynamicUseforeachDel2(@Param("ids") Integer[] ids);
    int queryPersonListDynamicUseforeachInsert(@Param("persons") List<Person> persons);
    List<Person> queryPersonListDynamicUseSql(@Param("id") Integer id);

}
