package com.retrieve;

import com.retrieve.entity.CranFieldDocument;
import com.retrieve.entity.WordList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Preprocess {

    private List<CranFieldDocument> cranFieldDocuments;
    private List<String> allWords = new ArrayList();
    private List<WordList> wordList = new ArrayList<WordList>();

    public Preprocess(List<CranFieldDocument> cranFieldDocuments) {
        this.cranFieldDocuments = cranFieldDocuments;
    }

    public void removeStopWords(String filename) {

        List<String> stopWords = new ArrayList();
        String word;
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((word = br.readLine()) != null) {
                stopWords.add(word);
                i = i + 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (CranFieldDocument doc : cranFieldDocuments) {
            allWords.add(doc.getTitle()
                    + doc.getAuthor()
                    + doc.getBibliography()
                    + doc.getText());
        }

        for (int j = 0; j < allWords.size(); j++) {
            for (int k = 0; k < stopWords.size(); k++) {
                allWords.set(j, allWords.get(j).replaceAll(" " + stopWords.get(k) + " ", " "));
            }
        }
    }

    public List<WordList> tokenizeNormalize() {
        String[] text;
        Porter stemmer = new Porter();
        for (int i = 0; i < allWords.size(); i++) {
            List<String> stem = new ArrayList<String>();
            text = allWords.get(i).split(" ");
            for (int j = 0; j < text.length; j++) {
                if (!stemmer.stripAffixes(text[j]).equals("")) {
                    stem.add(stemmer.stripAffixes(text[j]));
                }
            }
            wordList.add(new WordList(i, stem));
        }
//        System.out.println(rankedList.get(10).getWord());
        return wordList;
    }


    public List<CranFieldDocument> getCranFieldDocuments() {
        return cranFieldDocuments;
    }

    public void setCranFieldDocuments(List<CranFieldDocument> cranFieldDocuments) {
        this.cranFieldDocuments = cranFieldDocuments;
    }

    public List<String> getAllWords() {
        return allWords;
    }

    public void setRankedList(List<String> allWords) {
        this.allWords = allWords;
    }
}
