package cn.cw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.cw.dao")  //扫描dao层
@SpringBootApplication      //SpringBoot启动2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

