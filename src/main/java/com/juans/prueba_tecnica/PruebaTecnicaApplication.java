package com.juans.prueba_tecnica;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaTecnicaApplication {

	public static void main(String[] args) {
		// Carga .env de la raíz del proyecto para que mvn/IDE usen la misma URI que Docker (sin subir .env al repo).
		Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> {
			String key = entry.getKey();
			if (System.getenv(key) != null) {
				return;
			}
			if (System.getProperty(key) != null) {
				return;
			}
			System.setProperty(key, entry.getValue());
		});
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}
}
