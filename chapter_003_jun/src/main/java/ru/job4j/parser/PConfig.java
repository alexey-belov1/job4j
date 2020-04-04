package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PConfig {
    private static final Logger LOG = LogManager.getLogger(PConfig.class.getName());

    private final Properties values = new Properties();

    public PConfig() {
        try (InputStream in = PConfig.class.getClassLoader().getResourceAsStream("parser.properties")) {
            values.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
