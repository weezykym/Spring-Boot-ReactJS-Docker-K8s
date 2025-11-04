package com.kimutai.bookmarker_api_k8s;

import org.springframework.boot.SpringApplication;

public class TestBookmarkerApiK8sApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookmarkerApiK8sApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
