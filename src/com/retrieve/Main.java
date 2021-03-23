package com.retrieve;

import com.retrieve.entity.CranFieldDocument;
import com.retrieve.entity.Query;
import com.retrieve.entity.WordList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main
{

    public static void main(String args[]) throws FileNotFoundException, IOException
    {

        List<String[]> termsDocsArray = new ArrayList<String[]>();
        List<String> allTerms = new ArrayList<String>(); //to hold all terms

        String cranfieldDocsFile = "D:\\cranfieldDocs";
        String stopWordsFile = "D:\\stopwords.txt";
        String queriesFile = "D:\\queries.txt";

        // Read CranFieldDocument DataSet 1400  (XML >> Object)
        List<CranFieldDocument> cranFieldDocuments = ReadCranfieldData.readCranFieldDocuments(cranfieldDocsFile);

        // Preprocess
        // Read & Remove Stop words
        // Normalize using Porter
        // Tokenize
        // Return as a List
        Preprocess preDoc = new Preprocess(cranFieldDocuments);
        preDoc.removeStopWords(stopWordsFile);
        List<WordList> docList = preDoc.tokenizeNormalize();

        // Read Queries
        // Preprocess
        // Read & Remove Stop words
        // Normalize using Porter
        //Return as a List
        List<Query> queries = ReadCranfieldData.readQueries(queriesFile);
        QueryPreprocess preQuery = new QueryPreprocess(queries);
        preQuery.removeStopWords(stopWordsFile);
        List<WordList> queryList = preQuery.tokenizeNormalize();

        DocumentParser dp = new DocumentParser();
        for (int i = 0; i <docList.size() ; i++) {
            termsDocsArray.add(docList.get(i).getWordSeparate());
        }

        for (int j = 0; j <queryList.size() ; j++) {
            allTerms = queryList.get(j).getWord();
            dp.injectFiles(termsDocsArray, allTerms);
            dp.tfIdfCalculator();
            dp.getCosineSimilarity(j); //calculates cosine similarity
        }



    }
}
