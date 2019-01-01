package com.test.maps;

import com.test.maps.util.MapsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.LinkedHashMap;

@SpringBootApplication
public class MapsApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VaultTemplate vaultTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MapsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        VaultResponse response = vaultTemplate.read(MapsConstants.MAP_API_SECRET);
        logger.info("Value of google key");
        logger.info("-------------------------------");
        LinkedHashMap<String, String> data = (LinkedHashMap) response.getData().values().stream().findFirst().get();
        logger.info("Key :" + data.get(MapsConstants.MAP_API_KEY));
        logger.info("-------------------------------");
    }
}