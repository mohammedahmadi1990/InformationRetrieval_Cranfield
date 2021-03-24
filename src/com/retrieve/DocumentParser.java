package com.retrieve;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentParser
{

    //This variable will hold all terms of each document in an array.
    private List<String[]> termsDocsArray = new ArrayList<String[]>();
    private List<String> allTerms = new ArrayList<String>(); //to hold all terms
    private List<double[]> tfidfDocsVector = new ArrayList<double[]>();
    private List<String> tfidfDocsVectorNames = new ArrayList<String>();

    // Method to read files and store in array.
    // filePath : source file path --  Generally a folder with the required set of documents

    public void injectFiles(List<String[]> termsDocsArray, List<String> allTerms) throws FileNotFoundException, IOException
    {
        this.termsDocsArray = termsDocsArray;
        this.allTerms = allTerms;
    }

    public void tfIdfCalculator()
    {
        double tf; //term frequency
        double idf; //inverse document frequency
        double tfidf; //term frequency inverse document frequency
        for (String[] docTermsArray : termsDocsArray)
        {
            double[] tfidfvectors = new double[allTerms.size()];
            int count = 0;
            for (String terms : allTerms)
            {
                tf = new TfIdf().tfCalculator(docTermsArray, terms);
                idf = new TfIdf().idfCalculator(termsDocsArray, terms);
                tfidf = tf * idf;
                if (Double.isNaN(tfidf)){
                    tfidf=0;
                }
                tfidfvectors[count] = tfidf;
                count++;
            }
            tfidfDocsVector.add(tfidfvectors);  //storing document vectors;
        }

    }

    // Method to calculate cosine similarity between all the documents.

    public void getCosineSimilarity(int queryId)
    {
        for (int i = 0; i < tfidfDocsVector.size(); i++)
        {
            for (int j = 0; j < tfidfDocsVector.size(); j++)
            {
                if(i!=j) {
                    System.out.println("queryId_" + queryId + "  documentId_" + i + " CosineSimilarity: "+ new CosineSimilarity().cosineSimilarity(tfidfDocsVector.get(i),tfidfDocsVector.get(j)));
                    //System.out.println("between " + i + " and " + j + " CosineSimilarity: "+ new CosineSimilarity().cosineSimilarity (tfidfDocsVector.get(i),  tfidfDocsVector.get(j)));
                }
            }
        }
    }
}
