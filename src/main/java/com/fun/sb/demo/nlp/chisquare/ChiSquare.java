package com.fun.sb.demo.nlp.chisquare;

import com.fun.sb.demo.nlp.ChiSquareResult;
import com.fun.sb.demo.nlp.WordCountService;

/**
 * Created by shangbin01 on 2015/12/16.
 */
public class ChiSquare {


    /**
     * 卡方校验
     *
     * @param containAndBelong
     * @param containAndNotBelong
     * @param notContainAndBelong
     * @param notContainAndNotBelong
     * @return
     */
    public Double calculate(Integer containAndBelong, Integer containAndNotBelong, Integer notContainAndBelong, Integer notContainAndNotBelong) {
        int all = containAndBelong + containAndNotBelong + notContainAndBelong + notContainAndNotBelong;
        int contain = containAndBelong + containAndNotBelong;
        int belong = containAndBelong + notContainAndBelong;
        int notBelong = containAndNotBelong + notContainAndNotBelong;
        int notContain = notContainAndBelong + notContainAndNotBelong;

        double result = all * Math.pow(containAndBelong * notContainAndNotBelong - containAndNotBelong * notContainAndBelong, 2) / contain * belong * notBelong * notContain;
        return result;
    }

    public ChiSquareResult calculate(String word, Integer index, WordCountService wordCountService) {
        Integer containAndBelong = wordCountService.calculateCountContainBelong(word, index);
        Integer containAndNotBelong = wordCountService.calculateCountContainNotBelong(word, index);
        Integer notContainAndBelong = wordCountService.calculateCountNotContainBelong(word, index);
        Integer notContainAndNotBelong = wordCountService.calculateCountNotContainNotBelong(word, index);
        double calculateResult = calculate(containAndBelong, containAndNotBelong, notContainAndBelong, notContainAndNotBelong);
        ChiSquareResult chiSquareResult = new ChiSquareResult();
        chiSquareResult.setContainAndBelong(containAndBelong);
        chiSquareResult.setContainAndNotBelong(containAndNotBelong);
        chiSquareResult.setNotContainAndBelong(notContainAndBelong);
        chiSquareResult.setNotContainAndNotBelong(notContainAndNotBelong);
        chiSquareResult.setCalculateResult(calculateResult);
        return chiSquareResult;
    }

}
