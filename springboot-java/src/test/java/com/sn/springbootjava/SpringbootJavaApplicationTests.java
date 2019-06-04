package com.sn.springbootjava;

import com.sn.springbootjava.config.ServerProperties;
import com.sn.springbootjava.entity.TestSerializable;
import com.sn.springbootjava.entity.TestSynchronized;
import com.sn.springbootjava.service.impl.TestSynchronized2ServiceImpl;
import com.sn.springbootjava.service.impl.TestSynchronizedServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootJavaApplicationTests {

    @Autowired
    private ServerProperties serverProperties;

    private File file = new File("D:\\haiyan-data\\se.txt");
    private TestSerializable testSerializable = new TestSerializable();

    @Test
    public void serializable() {
        testSerializable.setValue("songNing");
        // 持久化到硬盘文件
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(testSerializable);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void desSerializable() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TestSerializable demo = (TestSerializable) objectInputStream.readObject();
            log.info(demo.toString());
            log.info("是否相等: " + (demo == testSerializable));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSynchronized() {
        TestSynchronized testSynchronized = new TestSynchronized();
        TestSynchronizedServiceImpl testSynchronizedService = new TestSynchronizedServiceImpl(testSynchronized);
        testSynchronizedService.run();
        TestSynchronized2ServiceImpl testSynchronized2Service = new TestSynchronized2ServiceImpl(testSynchronized);
        testSynchronized2Service.run();
    }

    /**
     * 获取spring-boot yml中的值
     */
    @Test
    public void testProperties() {
        log.info(this.serverProperties.getApp().getName());
        log.info(this.serverProperties.getApp().getThreadCount());
        log.info(this.serverProperties.getApp().getUsers().get(0));
    }

}
