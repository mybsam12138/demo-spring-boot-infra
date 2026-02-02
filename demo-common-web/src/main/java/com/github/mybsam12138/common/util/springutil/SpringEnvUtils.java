package com.github.mybsam12138.common.util.springutil;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEnvUtils {

    private final Environment environment;

    public boolean isProd() {
        return environment.acceptsProfiles(Profiles.of("prod"));
    }

    public String[] activeProfiles() {
        return environment.getActiveProfiles();
    }
}