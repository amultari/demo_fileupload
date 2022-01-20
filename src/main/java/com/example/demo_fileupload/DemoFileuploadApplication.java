package com.example.demo_fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoFileuploadApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoFileuploadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//questo test mi serve per capire se la lettura e la copia di un file funzionano e 
		//per aver lo stringone base64 da usare magari un un json per provare il controller
		//testLeggiEScriviFile();
	}

	private void testLeggiEScriviFile() throws IOException {
		// ATTENZIONE!!! PRIMA DI ESEGUIRE IL TEST LANCIRE LA BUILD OPPURE CREARE I FILE
		// IN target\classes
		// prova pdf
		System.out.println("Leggo il file da src/main/resources....doc_di_prova.pdf");
		letturaScritturaFile("doc_di_prova.pdf", "doc_di_prova_target.pdf");

		// provo immagine
		System.out.println("Leggo il file da src/main/resources....jpg_di_prova.jpg");
		letturaScritturaFile("jpg_di_prova.jpg", "jpg_di_prova_target.jpg");

		// provo docx
		System.out.println("Leggo il file da src/main/resources....doc_di_prova.docx");
		letturaScritturaFile("doc_di_prova.docx", "doc_di_prova_target.docx");
	}

	private void letturaScritturaFile(String inputFilePath, String outputFilePath) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File filePdfDiProva = new File(classLoader.getResource(inputFilePath).getFile());
		byte[] fileContent = Files.readAllBytes(filePdfDiProva.toPath());
		String encodedString = Base64.getEncoder().encodeToString(fileContent);

		// mi salvo la stringa in un txt così posso usarla con postman
		// viene salvato in
		// C:\appoggio\wdir\ws_formazione_2021_09\demo_fileupload\target\classes\....txt
		File fileTxtDiProvaTarget = new File(
				filePdfDiProva.getParentFile().getAbsolutePath() + "/" + outputFilePath + ".base64.txt");
		FileOutputStream fosTxt = new FileOutputStream(fileTxtDiProvaTarget);
		fosTxt.write(encodedString.getBytes());
		fosTxt.flush();
		fosTxt.close();

		// e lo scrivo su un file nuovo per vedere se funziona
		byte[] decoded = Base64.getDecoder().decode(encodedString);

		// viene salvato in
		// C:\appoggio\wdir\ws_formazione_2021_09\demo_fileupload\target\classes\doc_di_prova_target.pdf

		File filePdfDiProvaTarget = new File(filePdfDiProva.getParentFile().getAbsolutePath() + "/" + outputFilePath);
		FileOutputStream fos = new FileOutputStream(filePdfDiProvaTarget);
		fos.write(decoded);
		fos.flush();
		fos.close();
		if (filePdfDiProvaTarget.exists())
			System.out.println("Ho scritto....doc_di_prova_target.pdf..." + filePdfDiProvaTarget.getAbsolutePath());
		else
			System.out.println("Non ho scritto nulla");
	}

}
