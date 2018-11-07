package com.berga.PdfReader;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 * Hello world!
 *
 */
public class App {
	/**
	 * Le o conteudo (texto) de um arquivo pdf
	 *
	 */
	static Map <String, Rectangle> regions = new HashMap<String, Rectangle>();

	public static String extraiTextoDoPDF(File caminho) {
		PDDocument pdfDocument = null;
		try {
			pdfDocument = PDDocument.load(caminho);
			PDFTextStripper stripper = new PDFTextStripper();
			String texto = stripper.getText(pdfDocument);
			return texto;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (pdfDocument != null)
				try {
					pdfDocument.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
	}

	public static String getTextArea() {
		try {
			PDDocument document = PDDocument.load(new File("/home/carlos/Documentos/pes/octo/NC2018-09-26.pdf"));
			exibirAreas(document);
		} catch (Exception e) {

		}
		return null;
	}

	private static void exibirAreas(PDDocument document) throws IOException {
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);
		PDPage firstPage = document.getPage(0);
		getRegion(stripper);
		stripper.extractRegions(firstPage);
		System.out.println("Text in the area:" + regions.get("NrNota"));
		System.out.println(stripper.getTextForRegion("NrNota"));
	}

	private static Rectangle getRegion(PDFTextStripperByArea stripper) {
		Rectangle rect = regions.get("NrNota");
		stripper.addRegion("NrNota", rect);
		return regions.get("NrNota");
	}

	private static void populateRegions() {
//		regions.put("NrNota", new Rectangle(849, 115, 80, 19));
		regions.put("NrNota", new Rectangle(0, 40, 1800, 80));
	}
	/**
	 *
	 * Extrai o conteudo do arquivo indicado
	 *
	 */
	public static void main(String[] args) {
		populateRegions();
		getTextArea();
	}


	private static void lerTextoDeArquivo() {
		File arquivo = new File("/home/carlos/Documentos/pes/octo/NC2018-10-25.pdf");
		File arquivo2 = new File("/home/carlos/Documentos/pes/octo/NC2018-09-26.pdf");
		File arquivo3 = new File("/home/carlos/Documentos/pes/octo/NC2018-09-27.pdf");
		String texto = extraiTextoDoPDF(arquivo3);
		System.out.println(texto);
	}
}
