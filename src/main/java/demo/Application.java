package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	public static final CatFacePerfect sampleFace = new CatFacePerfect(CatFaceConfig.perfect_face_file); 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
