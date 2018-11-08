package com.berga.PdfReader.service;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class RegionExtractor {

	private static Map<String, String> infoMap = new HashMap<String, String>();
	
	public static Map<String, String> extractFromResource(String resourceName, Map<String, Rectangle> regionsMap, Integer pageNumber) throws InvalidPasswordException, IOException{
		InputStream is = RegionExtractor.class.getResourceAsStream(resourceName);
		PDDocument document = PDDocument.load(IOUtils.toByteArray(is));
		return getInfoMap(regionsMap, pageNumber, document);
	}
	
	public static Map<String, String> extractFromFile(String fileName, Map<String, Rectangle> regionsMap, Integer pageNumber) throws InvalidPasswordException, IOException{
		PDDocument document = PDDocument.load(new File(fileName));
		return getInfoMap(regionsMap, pageNumber, document);
	}

	private static Map<String, String> getInfoMap(Map<String, Rectangle> regionsMap, Integer pageNumber,
			PDDocument document) throws IOException {
		PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		PDPage page = document.getPage(pageNumber==null? 0 : pageNumber);

		for (String key : regionsMap.keySet()) {
			stripper.addRegion(key, regionsMap.get(key));
		}
		stripper.extractRegions(page);

		for (String key : regionsMap.keySet()) {
			infoMap.put(key, stripper.getTextForRegion(key));
		}
		return infoMap;
	}

}
