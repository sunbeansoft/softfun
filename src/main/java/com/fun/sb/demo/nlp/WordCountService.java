package com.fun.sb.demo.nlp;

import com.fun.sb.demo.nlp.Util.MD5Util;

/**
 * Created by shangbin01 on 2015/12/16.
 */
public class WordCountService {

    private WordCount[] wordCounts = new WordCount[37];

    public WordCountService() {
        for (int i = 0; i < 37; i++) {
            wordCounts[i] = new WordCount();
        }
    }

    public String addLine(Integer index, String line) {
        return wordCounts[index].newLine(line, MD5Util.MD5(line));
    }

    /**
     * 包含且属于
     *
     * @param word 词
     * @param type 类别
     * @return
     */
    public Integer calculateCountContainBelong(String word, Integer type) {
        CountModel countModel = wordCounts[type].getMap().get(word);
        return countModel == null ? 0 : countModel.getDocDistinctCount();
    }

    /**
     * 包含不属于的文档数
     *
     * @param word 词
     * @param type 类别
     * @return
     */
    public Integer calculateCountContainNotBelong(String word, Integer type) {
        int sum = 0;
        for (int i = 0; i < 37; i++) {
            if (type == i) {
                continue;
            }
            CountModel countModel = wordCounts[i].getMap().get(word);
            if (countModel != null) {
                sum = sum + countModel.getDocDistinctCount();
            }
        }
        return sum;
    }

    /**
     * 不包含属于的文档数
     *
     * @param word 词
     * @param type 类别
     * @return
     */
    public Integer calculateCountNotContainBelong(String word, Integer type) {
        CountModel countModel = wordCounts[type].getMap().get(word);
        if (countModel == null) {
            return 0;
        }
        return wordCounts[type].getDocSum() - countModel.getDocDistinctCount();
    }

    /**
     * 不包含不属于的文档数
     *
     * @param word 词
     * @param type 类别
     * @return
     */
    public Integer calculateCountNotContainNotBelong(String word, Integer type) {
        int sum = 0;
        for (int i = 0; i < 37; i++) {
            if (type == i) {
                continue;
            }
            CountModel countModel = wordCounts[i].getMap().get(word);
            if (countModel != null) {
                continue;
            }
            sum = sum + wordCounts[i].getDocSum();
        }
        return sum;
    }

    public WordCount getWordCounts(Integer index) {
        return wordCounts[index];
    }

}
