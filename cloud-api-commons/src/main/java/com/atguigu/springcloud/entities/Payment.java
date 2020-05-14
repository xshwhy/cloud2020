package com.atguigu.springcloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Payment
 * @Description TODO
 * @Author xsh
 * @Date 2020-04-24 17:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = -2214092998831800672L;

    private Long id;
    // 支付流水号

    private String serial;
    // 点赞数

    private Integer num;
    // 时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create;

}
