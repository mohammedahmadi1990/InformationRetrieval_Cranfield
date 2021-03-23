package com.retrieve;

import com.retrieve.entity.CranFieldDocument;
import com.retrieve.entity.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCranfieldData {

    public static List<CranFieldDocument> readCranFieldDocuments(String folderName) {
        List<CranFieldDocument> cranFieldDocuments = new ArrayList<CranFieldDocument>();
        File rep = new File(folderName);
        File[] files = rep.listFiles();
        try {
            for (File file : files) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File(file.toString()));
                document.getDocumentElement().normalize();
                Element root = document.getDocumentElement();
                cranFieldDocuments.add(new CranFieldDocument(

                        Integer.parseInt(document.getElementsByTagName("DOCNO").item(0).getTextContent().replace("\n", "")),
                        root.getElementsByTagName("TITLE").item(0).getTextContent().replace("\n", " ").replaceAll("\\d", "").trim().replaceAll(" +", " "),
                        root.getElementsByTagName("AUTHOR").item(0).getTextContent().replace("\n", " ").replaceAll("\\d", "").trim().replaceAll(" +", " "),
                        root.getElementsByTagName("BIBLIO").item(0).getTextContent().replace("\n", " ").replaceAll("\\d", "").trim().replaceAll(" +", " "),
                        root.getElementsByTagName("TEXT").item(0).getTextContent().replace("\n", " ").replaceAll("\\d", "").trim().replaceAll(" +", " ")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cranFieldDocuments;
    }

    public static List<Query> readQueries(String filename) {
        List<Query> queries = new ArrayList<Query>();
        int i = 1;
        String query;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((query = br.readLine()) != null) {
                queries.add(new Query(i, query));
                i = i + 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return queries;
    }

}
