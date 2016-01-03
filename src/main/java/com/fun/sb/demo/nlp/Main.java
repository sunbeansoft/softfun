package com.fun.sb.demo.nlp;

import com.fun.sb.demo.nlp.chisquare.ChiSquare;
import com.fun.sb.demo.nlp.hanlp.HanlpTest;
import com.fun.sb.demo.nlp.html.HtmlUtil;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by shangbin01 on 2015/12/15.
 */
public class Main {

    private HtmlUtil htmlUtil = new HtmlUtil();

    private WordCountService wordCountService = new WordCountService();

    private ChiSquare chiSquare = new ChiSquare();
    private HanlpTest hanlpTest = new HanlpTest();


    public Main() throws IOException {

        String str = null;
        File file = new File("d:\\ml\\report.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
        String temp = new String();

        BufferedWriter[] bws = new BufferedWriter[37];
        BufferedWriter[] bwsw = new BufferedWriter[37];
//        WordCount[] wordCounts = new WordCount[37];
        BufferedWriter[] count = new BufferedWriter[37];
        File f1 = new File("d:\\ml\\line");
        File f2 = new File("d:\\ml\\word");
        File f3 = new File("d:\\ml\\count");
        File all = new File("d:\\ml\\result_word.txt");
        all.createNewFile();
        FileWriter fw1 = new FileWriter(all);
        BufferedWriter allWriter = new BufferedWriter(fw1);

        f1.mkdir();
        f2.mkdir();
        f3.mkdir();
        for (int i = 0; i < 37; i++) {
            File file1 = new File("d:\\ml\\word\\" + i);
            file1.mkdir();
        }

        for (int i = 0; i <= 36; i++) {
            File writeFile = new File("d:\\ml\\line\\result" + i + ".txt");
            File wordFile = new File("d:\\ml\\word\\" + i + "\\result" + i + ".txt");
            File wordCountFile = new File("d:\\ml\\count\\result" + i + ".csv");
            writeFile.createNewFile();
            wordFile.createNewFile();
            wordCountFile.createNewFile();
            FileWriter fw = new FileWriter(writeFile);
            FileWriter fww = new FileWriter(wordFile);
            FileWriter fwCount = new FileWriter(wordCountFile);
            bws[i] = new BufferedWriter(fw);
            bwsw[i] = new BufferedWriter(fww);
            count[i] = new BufferedWriter(fwCount);
//            wordCounts[i] = new WordCount();
        }
        while ((str = br.readLine()) != null) {
            if (str.endsWith("</p>")) {
                temp = temp + str;
                String[] list = temp.split("\t");
                String result = htmlUtil.getTextFromHtml(list[1]);
                int index = new Integer(list[0]);
                bws[index].write(result, 0, result.toString().length());
                bws[index].newLine();
                String words = wordCountService.addLine(index, result);
//                String words = wordCounts[index].newLine(result);
                System.out.println(result);
                System.out.println(words);
                bwsw[index].write(words, 0, words.length());
                bwsw[index].newLine();
                allWriter.write(words, 0, words.length());
                allWriter.newLine();
                temp = new String();
            } else {
                temp = temp + str;
            }
        }
        for (int i = 0; i <= 36; i++) {
            bws[i].close();
            bwsw[i].close();
        }

        for (int i = 0; i <= 36; i++) {
            Set<String> keySet = wordCountService.getWordCounts(i).getMap().keySet();
            count[i].write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
            for (String key : keySet) {
                String cnt = key + "," + wordCountService.getWordCounts(i).getMap().get(key).getDocDistinctCount() + "," + wordCountService.getWordCounts(i).getMap().get(key).getDocCount();
                count[i].write(cnt, 0, cnt.length());
                count[i].newLine();
            }
            count[i].close();
            System.out.println(i + "===================================");
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("2输入字符");
        String s = null;
        while (true) {
            try {
                s = bf.readLine();
                List<Term> list = hanlpTest.getWrodWithoutStopwordStr(s);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 38; i++) {
                    double sum = 0;
                    for (Term term : list) {
                        ChiSquareResult result = chiSquare.calculate(term.word, i, wordCountService);
                        System.out.println("\t" + term.word + "结果:" + result.getCalculateResult());
                        System.out.println("\t" + "getContainAndBelong:" + result.getContainAndBelong());
                        System.out.println("\t" + "getContainAndNotBelong:" + result.getContainAndNotBelong());
                        System.out.println("\t" + "getNotContainAndBelong:" + result.getNotContainAndBelong());
                        System.out.println("\t" + "getNotContainAndNotBelong:" + result.getNotContainAndNotBelong());
                        sum = sum + result.getCalculateResult();
                    }
                    System.out.println("与" + i + "相关度:" + sum);
                    sb.append("与" + i + "相关度:" + sum + "\n");
                }
                System.out.println("=======\n" + sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("没有相关数据" + e.getMessage());
            }
        }

    }


    public static void main(String[] args) throws IOException {
        new Main();
    }
}
