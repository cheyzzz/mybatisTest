package com.chey.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author chey
 * @Date 2022-07-18 18:43
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class city {
    private Integer id;
    private String name;
    private String countryCode;
    private String district;
    private long population;

}
