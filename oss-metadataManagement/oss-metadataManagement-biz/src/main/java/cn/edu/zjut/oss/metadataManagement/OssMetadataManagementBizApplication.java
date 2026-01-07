package cn.edu.zjut.oss.metadataManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OssMetadataManagementBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssMetadataManagementBizApplication.class, args);
    }
}
