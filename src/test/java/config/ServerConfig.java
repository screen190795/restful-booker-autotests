package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;
@Sources({"${propertiesFile}", "classpath:application.properties"})
public interface ServerConfig extends Config {
    @Key("restful.booker.username")
    String restful_booker_username();

    @Key("restful.booker.password")
    String restful_booker_password();
}
