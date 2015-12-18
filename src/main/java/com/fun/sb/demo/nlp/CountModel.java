package com.fun.sb.demo.nlp;

/**
 * Created by shangbin01 on 2015/12/16.
 */
public class CountModel {

    /**
     * 词
     */
    private String word;

    /**
     * 最后一个反馈的Id
     */
    private String lastMD5;

    /**
     * 分类中总个数
     */
    private Integer docCount;

    /**
     * 分类中按文档去重数
     */
    private Integer docDistinctCount;

    public CountModel(String word, String lastMD5, Integer docCount, Integer docDistinctCount) {
        this.word = word;
        this.lastMD5 = lastMD5;
        this.docCount = docCount;
        this.docDistinctCount = docDistinctCount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getDocCount() {
        return docCount;
    }

    public void setDocCount(Integer docCount) {
        this.docCount = docCount;
    }

    public Integer getDocDistinctCount() {
        return docDistinctCount;
    }

    public void setDocDistinctCount(Integer docDistinctCount) {
        this.docDistinctCount = docDistinctCount;
    }

    public String getLastMD5() {
        return lastMD5;
    }

    public void setLastMD5(String lastMD5) {
        this.lastMD5 = lastMD5;
    }

    public void addDocDistinctCount() {
        docDistinctCount = docDistinctCount + 1;
    }

    public void addDocCount() {
        docCount = docCount + 1;
    }
}
