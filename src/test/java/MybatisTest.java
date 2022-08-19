import com.chey.mapper.CityMapper;
import com.chey.mapper.PersonMapper;
import com.chey.pojo.City;
import com.chey.pojo.Person;
import com.chey.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Author chey
 * @Date 2022-07-19 9:40
 * @Describe
 */
public class MybatisTest {

    /**
     * @Author: chey
     * @Date: 2022/7/19 13:51
     * @Param: []
     * @Return: void
     * @Description:
     */
    @Test
    public void insertTest(){
        try {
            //加载核心配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //获得SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //获得SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            //获得session  自动提交事务为true
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            //获得实体类mapper  反射机制  代理模式
            PersonMapper mapper = sqlSession.getMapper  (PersonMapper.class);
            int i = mapper.insertPerson();
            //提交事务
//            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author: chey
     * @Date: 2022/7/19 19:51
     * @Param:
     * @Return:
     * @Description:
     */
    @Test
    public void deleteTest(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            PersonMapper mapper = sqlSession.getMapper  (PersonMapper.class);
            int i = mapper.deletePerson();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author: chey
     * @Date: 2022/7/19 19:51
     * @Param:
     * @Return:
     * @Description:
     */
    @Test
    public void updateTest(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            PersonMapper mapper = sqlSession.getMapper  (PersonMapper.class);
            int i = mapper.updatePerson();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author: chey
     * @Date: 2022/7/19 19:51
     * @Param:
     * @Return:
     * @Description:
     */
    @Test
    public void queryTest(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            PersonMapper mapper = sqlSession.getMapper  (PersonMapper.class);

            Person person = mapper.queryPerson();
            System.out.println(person);
            System.out.println("-----");

            for (Person person1 : mapper.queryPersonList()) {
                System.out.println(person1);
            }
            System.out.println("-----");

            List<Person> personList = mapper.queryPersonList();
            personList.forEach(person1 -> System.out.println(person1));
            System.out.println("-----");

            personList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author: chey
     * @Date: 2022/7/20 14:07
     * @Param: []
     * @Return: void
     * @Description: 工具类获得sqlSession   查询
     */
    @Test
    public void sqlUtilTest(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);


        System.out.println("----无参  用list接收多个返回值 单个类型为实体类-----");
        List<Person> peopleList = mapper.queryPersonList();
        peopleList.forEach(System.out::println);
        System.out.println("----单个参数 用实体类接收-----");
        Person person = mapper.queryPersonByName("zxx");
        System.out.println(person);
        System.out.println("----多个参数 用实体类接收-----");
        Person person2 = mapper.queryPersonByNameAndAge("chey",22);
        System.out.println(person2);
        System.out.println("----参数为map 用实体类接收-----");
        HashMap personMap = new HashMap();
        personMap.put("name","chey");
        personMap.put("age",22);
        Person person3 = mapper.queryPersonByMap(personMap);
        System.out.println(person3);
        System.out.println("----参数为实体类 用实体类接收-----");
        Person person4 = mapper.queryPersonByClass(person3);
        System.out.println(person4);
        System.out.println("----带@param注解的参数 用实体类接收-----");
        Person person5 = mapper.queryPersonByParam("chey",22);
        System.out.println(person5);
        System.out.println("----用map接收-----");
        Map idToMap = mapper.queryPersonByIdToMap(3);
        System.out.println(idToMap);
        System.out.println("----用整型接收-----");
        Integer countNum = mapper.queryPersonCountById(3);
        System.out.println(countNum);
        System.out.println("----用list接收多个返回值 单个类型为map-----");
        List<Map<String,Object>> mapList  = mapper.queryPersonMapListByAge(22);
        System.out.println(mapList);
        System.out.println("----用map接收多个返回值 用mapkey注解-----");
        Map<String,Object> mapKey  = mapper.queryPersonMapKeyByAge(22);
        System.out.println(mapKey);
        System.out.println("----like模糊搜索-----");
        List<Person> personLike  = mapper.queryPersonUseLike(22);
        System.out.println(personLike);
        System.out.println("----批量删除-----");
        mapper.deletePersonBatch("22,33");
        System.out.println("删除成功");
        System.out.println("----动态设置表名-----");
        List<Map<String,Object>> map = mapper.setTableName("City");
        System.out.println(map);
        System.out.println("----批量删除-----");


    }

    /**
     * @Author: chey
     * @Date: 2022/8/7 21:05
     * @Param: []
     * @Return: void
     * @Description:
     */
    @Test
    public void sqlUtilTest2(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        Person person1 = new Person(null, "jj", null,null);
        Person person2 = new Person(null, "jrj", null,null);
        mapper.insertPersonByParam(person1);
        System.out.println(person1);
        mapper.insertPersonAutoByParam(person2);
        System.out.println(person2);


    }

    /**
     * @Author: chey
     * @Date: 2022/8/7 22:14
     * @Param: []
     * @Return: void
     * @Description:
     */
    @Test
    public void sqlUtilResultMapTest(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CityMapper mapper1 = sqlSession.getMapper(CityMapper.class);
//        List<City> cities = mapper1.queryCityList();
//        System.out.println(cities);
//        System.out.println("---------");
//        cities.forEach(System.out::println);
        PersonMapper mapper2 = sqlSession.getMapper(PersonMapper.class);
        for (Person person : mapper2.queryPersonList()) {
            System.out.println(person);
        }
    }

    /**
     * @Author: chey
     * @Date: 2022/8/10 22:34
     * @Param:
     * @Return:
     * @Description: 处理多对一  查询员工 多个员工对应一个部门
     *                  法一级联 sql中关联表
     *                  法二resultMap中嵌套association标签  两个表的字段名不能一样
     *                  法三resultMap中嵌套association标签 再嵌套select 相当于分步查询
     *                      延迟加载
     */
    @Test
    public void sqlUtilResultMapTest2(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        List<Person> list1 = mapper.queryPersonAndCity(2);
        list1.forEach(System.out::println);
        System.out.println("______");
        List<Person> list2 = mapper.queryPersonAndCityAsso(10);
        list2.forEach(System.out::println);
        System.out.println("______");
        List<Person> list3 = mapper.queryPersonThenCity(10);
//        list3.forEach(System.out::println);
        System.out.println("-------开启延迟加载  查询第一步-------");
        System.out.println(list3.get(0).getAge());
        System.out.println("-------查询第二步-------");
        System.out.println(list3.get(0).getCity().getDistrict());

    }


    /**
     * @Author: chey
     * @Date: 2022/8/12 17:38
     * @Param:
     * @Return:
     * @Description: 处理一对多  查询部门 一个部门对应多个员工
     *                  法一 collection标签
     *                  法二 collection标签 分步查询
     */
    @Test
    public void sqlUtilResultMapTest3(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CityMapper mapper = sqlSession.getMapper(CityMapper.class);
        List<City> list1 = mapper.queryCityAndPerson(2);
        list1.forEach(System.out::println);
        System.out.println("----------------");
        List<City> list2 = mapper.queryCityThenPerson(2);
        list2.forEach(System.out::println);
        System.out.println("----------------");
        System.out.println(list2.get(0).getDistrict());
        System.out.println("----------------");
        System.out.println(list2.get(0).getPersons().get(0).getName());

    }

    /**
     * @Author: chey
     * @Date: 2022/8/15 11:00
     * @Param: []
     * @Return: void
     * @Description: 动态sql
     */
    @Test
    public void sqlUtilDynamicTest(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(null, null, null, null);
        List<Person> list1 = mapper.queryPersonListDynamicUseTest(person);
        list1.forEach(System.out::println);
        System.out.println("-----------");
        List<Person> list2 = mapper.queryPersonListDynamicUseWhere(person);
        list2.forEach(System.out::println);
        System.out.println("-----------");
        List<Person> list3 = mapper.queryPersonListDynamicUseTrim(person);
        list3.forEach(System.out::println);
        System.out.println("-----------");
        List<Person> list4 = mapper.queryPersonListDynamicUsechoose(person);
        list4.forEach(System.out::println);
        System.out.println("-----------");
        System.out.println(mapper.queryPersonListDynamicUseforeachDel1(new Integer[]{19, 20}));
        System.out.println("-----------");
        System.out.println(mapper.queryPersonListDynamicUseforeachDel2(new Integer[]{17, 28}));
        System.out.println("-----------");
        Person person1 = new Person(null, "yuyu", 33, null);
        Person person2 = new Person(null, "yutyu", 33, null);
        Person person3 = new Person(null, "yuuyu", 33, null);
        List<Person> peoples = Arrays.asList(person1, person2, person3);
        System.out.println(mapper.queryPersonListDynamicUseforeachInsert(peoples));
        System.out.println("-----------");

        List<Person> list5 = mapper.queryPersonListDynamicUseSql(5);
        list5.forEach(System.out::println);
    }
}