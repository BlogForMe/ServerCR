package org.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("poi_table")
public class Poi {
    public Integer id;
    public String name;
    public String description;
    public Float lng;
    public Float lat;
    public String coverUrl;
}
