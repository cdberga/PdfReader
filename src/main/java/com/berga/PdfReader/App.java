package com.berga.PdfReader;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.berga.PdfReader.service.BrokerageNoteExtractor;
import com.berga.PdfReader.service.TextExtractor;

/**
 * Hello world!
 *
 */
public class App {
	/**
	 * Le o conteudo (texto) de um arquivo pdf
	 *
	 */
	

	/**
	 *
	 * Extrai o conteudo do arquivo indicado
	 * @throws IOException 
	 * @throws InvalidPasswordException 
	 *
	 */
	public static void main(String[] args) throws InvalidPasswordException, IOException {
		BrokerageNoteExtractor bne = new BrokerageNoteExtractor("/home/carlos/Documentos/pes/octo/NC2018-10-25.pdf");
		System.out.println(bne.getNoteInfo());
//		lerTextoDeArquivo();
	}

	private static void lerTextoDeArquivo() {
		String arquivo3 = "/home/carlos/Documentos/pes/octo/NC2018-09-27.pdf";
		String texto = TextExtractor.extractFromFile(arquivo3);
		System.out.println(texto);
	}
}
