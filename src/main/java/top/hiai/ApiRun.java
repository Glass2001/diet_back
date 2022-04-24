package top.hiai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"top.hiai.mapper"})
public class ApiRun {

    public static void main(String[] args) {
        SpringApplication.run(ApiRun.class, args);
    }

}
