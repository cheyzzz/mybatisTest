package com.chey.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author chey
 * @Date 2022-08-07 22:10
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Integer cid;
    private String cname;
    private String countryCode;
    private String district;
    private String population;
    private List<Person> persons;

}
