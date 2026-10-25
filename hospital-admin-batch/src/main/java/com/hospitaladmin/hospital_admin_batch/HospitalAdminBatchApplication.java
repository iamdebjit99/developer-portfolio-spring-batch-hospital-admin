package com.hospitaladmin.hospital_admin_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HospitalAdminBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAdminBatchApplication.class, args);
	}

}
