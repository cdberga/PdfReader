package com.berga.PdfReader.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class TextExtractor {

	public static String extractFromFile(String path) {
		PDDocument pdfDocument = null;
		try {
			pdfDocument = PDDocument.load(new File(path));
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(pdfDocument);
			return text;
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
}
