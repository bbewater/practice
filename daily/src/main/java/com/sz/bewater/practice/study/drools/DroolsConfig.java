package com.sz.bewater.practice.study.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Project: practice
 * Description: 描述这个文件的作用
 *
 * @Author: zhoudun
 * @Date: 2025/4/17
 */
@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();

        // 加载所有 rules 目录下的 .drl 文件
        try {
            Files.walk(Paths.get("src/main/resources/rules"))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            String content = new String(Files.readAllBytes(path));
                            String filePath = "src/main/resources/rules/" + path.getFileName();
                            kfs.write(filePath, content);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("加载 .drl 文件失败", e);
        }

        KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
        return ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
    }
}
