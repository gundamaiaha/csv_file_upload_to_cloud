package com.myapps.csvupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CsvUploadToCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvUploadToCloudApplication.class, args);
	}

}
