package com.fun.sb.demo.nlp;

/**
 * Created by shangbin01 on 2015/12/16.
 */
public class ChiSquareResult {
    private Integer containAndBelong;
    private Integer containAndNotBelong;
    private Integer notContainAndBelong;
    private Integer notContainAndNotBelong;
    private Double calculateResult;

    public Integer getContainAndBelong() {
        return containAndBelong;
    }

    public void setContainAndBelong(Integer containAndBelong) {
        this.containAndBelong = containAndBelong;
    }

    public Integer getContainAndNotBelong() {
        return containAndNotBelong;
    }

    public void setContainAndNotBelong(Integer containAndNotBelong) {
        this.containAndNotBelong = containAndNotBelong;
    }

    public Integer getNotContainAndBelong() {
        return notContainAndBelong;
    }

    public void setNotContainAndBelong(Integer notContainAndBelong) {
        this.notContainAndBelong = notContainAndBelong;
    }

    public Integer getNotContainAndNotBelong() {
        return notContainAndNotBelong;
    }

    public void setNotContainAndNotBelong(Integer notContainAndNotBelong) {
        this.notContainAndNotBelong = notContainAndNotBelong;
    }

    public Double getCalculateResult() {
        return calculateResult;
    }

    public void setCalculateResult(Double calculateResult) {
        this.calculateResult = calculateResult;
    }
}
