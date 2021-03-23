package com.retrieve;


import com.retrieve.entity.Query;
import com.retrieve.entity.WordList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QueryPreprocess {

    private List<Query> queries = new ArrayList<Query>();
    private List<String> allWords = new ArrayList<String>();
    private List<WordList> rankedList = new ArrayList<WordList>();

    public QueryPreprocess(List<Query> queries) {
        this.queries = queries;
    }

    public void removeStopWords(String filename) {
        List<String> stopWords = new ArrayList<String>();
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

        for (Query query : queries) {
            allWords.add(query.getQuery());
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
            rankedList.add(new WordList(i, stem));
        }
        //System.out.println(rankedList.get(3).getWord());
        return rankedList;
    }


    public List<String> getAllWords() {
        return allWords;
    }

    public void setRankedList(List<String> allWords) {
        this.allWords = allWords;
    }
}

