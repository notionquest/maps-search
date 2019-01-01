package com.test.maps.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public class MapsUtil {
    private static Logger logger = LoggerFactory.getLogger(MapsUtil.class);

    public static String readJson(String path) {
        String jsonString = "";
        try {
            jsonString = FileCopyUtils.copyToString(new InputStreamReader(MapsUtil.class.getResourceAsStream(path)));
        } catch(IOException e) {
            logger.error("Couldn't read the json file", e);
        }
        return jsonString;
    }
}
