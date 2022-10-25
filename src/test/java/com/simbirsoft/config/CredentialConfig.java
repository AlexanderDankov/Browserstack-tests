package com.simbirsoft.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credential.properties"
})
public interface CredentialConfig extends Config {

    String remote_user();
    String remote_key();
    String remote_url();
}
