package parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import au.com.bytecode.opencsv.CSVWriter;



public class Main {
	public static void main(String[] args) throws IOException {
		//output
		CSVWriter writer = new CSVWriter(new FileWriter(new File("/Users/inaba.jun/Desktop", "aws.csv")));
		//input
		Path path = FileSystems.getDefault().getPath("/Users/inaba.jun/Desktop", "aws");

		byte[] resultLines = Files.readAllBytes(path);
		String content = new String(resultLines, Charset.defaultCharset());
		
		Document doc = Jsoup.parse(content);
		Element parent = doc.getElementById("searchResult");
		Elements schedules = parent.getElementsByAttributeValueStarting("id", "session_");
		
		for(Element schedule : schedules){
			String id = schedule.getElementsByClass("abbreviation").text();
			String title = schedule.getElementsByClass("title").text();
			String description = schedule.getElementsByClass("abstract truncatedTxt").text();
			String type = schedule.getElementsByClass("type").text();
			String room = schedule.getElementsByClass("sessionRoom").text();
			String time = schedule.getElementsByClass("availableSessions sessionTimeList").get(0).text();
			time = time.replace("Registration is disabled for this user. ", "");
			time = time.replaceAll(" – Mirage.*", "");
			time = time.replaceAll(" – MGM.*", "");
			time = time.replaceAll(" – Aria.*", "");
			time = time.replaceAll(" – Venetian.*", "");
			
			String[] line = {id,title, description, type, room, time};
			writer.writeNext(line);
		}
		writer.flush();

	}
}
