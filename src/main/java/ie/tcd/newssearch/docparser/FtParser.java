package ie.tcd.newssearch.docparser;

import ie.tcd.newssearch.consts.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FtParser {
	public static void main(String[] args) throws IOException {

		File baseDir = new File(Constants.PARSED_DOCUMENT_LOCATION + "ft_docs/");
		baseDir.mkdirs();

		File[] file = new File(Constants.DATASET_LOCATION + "ft/").listFiles();
		System.out.println(file);
		ArrayList<String> files1 = new ArrayList<String>();
		for (File files : file) {
			if (files.isDirectory()) {
				System.out.println(files.getPath());
				System.out.println();
				for (File f : files.listFiles()) {
					files1.add(f.getAbsolutePath());

				}
			}
		}

		for (String f : files1) {
			try {
				System.out.println(f);
				File input = new File(f);
				Document doc = Jsoup.parse(input, "UTF-8", "");

				doc.select("docid").remove();

				Elements docs = doc.select("doc");

				for (Element e : docs) {

					String DocNo = e.getElementsByTag("Docno").text();
					File result = new File(Constants.PARSED_DOCUMENT_LOCATION + "ft_docs/" + DocNo);
					PrintWriter writer = new PrintWriter(result, "UTF-8");

					writer.println(e.text());
					writer.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}