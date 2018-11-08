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
	static Map<String, Rectangle> regions = new HashMap<String, Rectangle>();

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
			PDDocument document = PDDocument.load(new File("/home/carlos/Documentos/pes/octo/NC2018-10-25.pdf"));
			exibirAreas(document);
		} catch (Exception e) {

		}
		return null;
	}

	private static void populateRegions() {
		regions.put("Nota", new Rectangle(425, 60, 45, 10));
		regions.put("Data", new Rectangle(520, 60, 45, 10));
		regions.put("ope", new Rectangle(93, 252, 450, 198));
		regions.put("Liq", new Rectangle(500, 481, 47, 10));
		regions.put("Corr", new Rectangle(500, 571, 47, 10));
		regions.put("iss", new Rectangle(500, 601, 47, 10));
		regions.put("ir", new Rectangle(500, 610, 47, 10));
		regions.put("tot", new Rectangle(500, 500, 47, 10));
	}

	private static void exibirAreas(PDDocument document) throws IOException {
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		stripper.setSortByPosition(true);
		PDPage firstPage = document.getPage(0);
		getRegions(stripper);
		stripper.extractRegions(firstPage);
		printRegions(stripper);
	}

	private static void getRegions(PDFTextStripperByArea stripper) {
		for (String key : regions.keySet()) {
			stripper.addRegion(key, regions.get(key));
		}
	}

	private static void printRegions(PDFTextStripperByArea stripper) {
		for (String key : regions.keySet()) {
			System.out.println("Text in the area:" + regions.get(key));
			String []value = null;
			if(key.equals("ope")) {
				value = stripper.getTextForRegion(key).split("\n");
			}
			else {
				value = stripper.getTextForRegion(key).replace("\n", "").split("\\|");
			}
			System.out.println("[" + key + ": ");
			for (String line : value) {
				System.out.println(line);
			}
			System.out.println("]");
		}
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
