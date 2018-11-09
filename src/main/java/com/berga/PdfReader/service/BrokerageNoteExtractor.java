package com.berga.PdfReader.service;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class BrokerageNoteExtractor {

	private Map<String, Rectangle> regions = new HashMap<String, Rectangle>();
	private Map<String, String> infos;
	
	public BrokerageNoteExtractor(String file) throws InvalidPasswordException, IOException {
		populateRegions();
		infos = RegionExtractor.extractFromFile(file, regions, 0);
	}
	
	public Map<String, String> getInfoMap() {
		return infos;
	}
	
	public String getNoteInfo() {
		return infos.toString();
	}

	private void populateRegions() {
		regions.put("Nota", new Rectangle(425, 60, 45, 10));
		regions.put("Data", new Rectangle(520, 60, 45, 10));
		regions.put("Oper", new Rectangle(93, 252, 450, 198));
		regions.put("Liq", new Rectangle(500, 481, 47, 10));
		regions.put("Corr", new Rectangle(500, 571, 47, 10));
		regions.put("iss", new Rectangle(500, 601, 47, 10));
		regions.put("ir", new Rectangle(500, 610, 47, 10));
		regions.put("tot", new Rectangle(500, 500, 47, 10));
	}
}
