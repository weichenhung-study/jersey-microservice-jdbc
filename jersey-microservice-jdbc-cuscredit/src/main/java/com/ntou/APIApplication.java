package com.ntou;

import lombok.extern.log4j.Log4j2;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@Log4j2
@ApplicationPath("/cuscredit")
public class APIApplication extends ResourceConfig {
    public APIApplication() {
        packages("com.ntou");
    }
}
