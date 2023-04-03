package com.myapps.csvupload.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoogleCredentialsConfig {
  private String type;
  private String privateKeyId;
  private String privateKey;
  private String clientEmail;
  private String clientId;
  private String authUri;
  private String tokenUri;
  private String authProviderX509CertUrl;
  private String clientX509CertUrl;

  // getters and setters
}
