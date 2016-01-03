package com.fun.sb.demo.nlp;

import com.fun.sb.demo.nlp.hanlp.HanlpTest;
import com.hankcs.hanlp.seg.common.Term;

import java.util.*;

/**
 * Created by shangbin01 on 2015/12/15.
 */
public class WordCount {

    private Map<String, CountModel> map;

    private int docSum = 0;
    private HanlpTest hanlpTest = new HanlpTest();

    public WordCount() {
        map = new HashMap<String, CountModel>();
    }

    public List<Term> newLine(String line, String lineIndex) {
        docSum = docSum + 1;
        List<Term> terms = hanlpTest.getWrodWithoutStopwordStr(line);
        for (Term term : terms) {
            addWrod(term.word, lineIndex);
        }
        return terms;
    }

    public String newLine(List<Term> terms, String lineIndex) {
        docSum = docSum + 1;
        StringBuffer sb = new StringBuffer();
        for (Term term : terms) {
            sb.append(term.word).append(" ");
            addWrod(term.word, lineIndex);
        }
        return sb.toString();
    }

    private boolean addWrod(String word, String lineMD5) {
        if (map.containsKey(word)) {
            CountModel countModel = map.get(word);
            //如果一行中有两个重复的词 会触发这个条件
            if (lineMD5.equals(countModel.getLastMD5())) {
                countModel.addDocDistinctCount();
            }
            countModel.addDocCount();
            countModel.setLastMD5(lineMD5);
        } else {
            CountModel countModel = new CountModel(word, lineMD5, 1, 1);
            map.put(word, countModel);
        }
        return true;
    }

    public Map<String, CountModel> getMap() {
        return map;
    }

    public int getDocSum() {
        return docSum;
    }

    public void setDocSum(int docSum) {
        this.docSum = docSum;
    }
}
