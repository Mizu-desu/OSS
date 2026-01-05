package cn.edu.zjut.oss.storagenode;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableFileStorage
public class OssStorageNodeBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssStorageNodeBizApplication.class, args);
    }
}
