package fr.florianpal.hypercraftjoinmessage.configurations;

import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConfig {


    private String driver;
    private String url;
    private String user;
    private String password;

    public void load(Configuration config) {

        driver = config.getString("database.driver");
        url = config.getString("database.url");
        user = config.getString("database.user");
        password = config.getString("database.password");
    }

    public void save(Configuration config) {

        config.set("database.driver", driver);
        config.set("database.url", url);
        config.set("database.user", user);
        config.set("database.password", password);
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


}
