package com.myapps.csvupload.config;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Configuration
@ConditionalOnProperty(value = "cloud.service", havingValue = "gcp")
public class GCPConfig {

    @Autowired
    private Environment environment;


    /**
     * This method should be used if we want to use gcp_credentials.json
     * for GCP Credentials(make sure gcp_credentials.json should present in
     * resource folder)
     *
     * @return Storage
     * @throws IOException
     */

//    @Bean
//    public Storage storage() throws IOException {
//        InputStream inputStream = getClass().getResourceAsStream("/gcp_credentials.json");
//        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
//        StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials)
//                .build();
//        return options.getService();
//
//    }

    /**
     * This method should be used if we don't want to place gcp_credentials.json
     * in resource folder. For this, we need to pass the environment variable as
     *  GOOGLE_APPLICATION_CREDENTIALS=/path/to/service-account-key.json
     * @return
     * @throws IOException
     */
    @Bean
    public Storage storage() throws IOException{
        return StorageOptions.getDefaultInstance().getService();
    }



}
