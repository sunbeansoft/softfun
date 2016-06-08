package com.fun.sb.demo.temp;

import com.google.common.collect.Maps;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.*;
import java.util.*;

/**
 * ${类描述}
 * User: shangbin01
 * Date: 16/3/9
 * Time: 下午1:45
 */
public class SplitString {

    private Map<String, Integer> addressMap = Maps.newHashMap();
    private Map<String, Integer> nameMap = Maps.newHashMap();

    public SplitString() {
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process() throws IOException {
//        File name = new File("/Users/baidu/Downloads/Downloads/name.txt");
        int addressSum = 0;
        int nameSum = 0;

        FileReader address = new FileReader("/Users/baidu/Downloads/Downloads/address.txt");
        BufferedReader br = new BufferedReader(address);
        String str = null;
        while ((str = br.readLine()) != null) {
            List<Term> terms = NlpAnalysis.parse(str);
            for (Term term : terms) {
                if (addressMap.containsKey(term.getName())) {
                    Integer sum = addressMap.get(term.getName());
                    sum++;
                    addressMap.put(term.getName(), sum);
                } else {
                    addressMap.put(term.getName(), 1);
                }
            }
            addressSum++;
        }
        br.close();
        address.close();


        FileReader name = new FileReader("/Users/baidu/Downloads/Downloads/name.txt");
        BufferedReader nameBr = new BufferedReader(name);
        String namestr = null;


        while ((namestr = nameBr.readLine()) != null) {
            List<Term> terms = NlpAnalysis.parse(namestr);
            for (Term term : terms) {
                if (nameMap.containsKey(term.getName())) {
                    Integer sum = nameMap.get(term.getName());
                    sum++;
                    nameMap.put(term.getName(), sum);
                } else {
                    nameMap.put(term.getName(), 1);
                }
                nameSum++;
            }
        }

        nameBr.close();
        name.close();

//        addressMap = sortByValue(addressMap, true);
//        nameMap = sortByValue(nameMap, true);

        System.out.println("address:" + addressSum + "," + addressMap.size());

        int i = 0;
        Iterator<Map.Entry<String, Integer>> it = addressMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("    " + entry.getKey() + "," + entry.getValue());
            i++;
            if (i > 20) break;
        }

        System.out.println("name:" + nameSum + "," + nameMap.size());

        i = 0;
        it = nameMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("    " + entry.getKey() + "," + entry.getValue());
            i++;
            if (i > 20) break;
        }
        System.out.println("done");
//        address(addressSum, nameSum);
//        name(addressSum, nameSum);
        queryName(addressSum, nameSum);

        Scanner in = new Scanner(System.in);
        while (true) {
            String readLine = in.nextLine();
            System.out.println("属于address" + isAddress(readLine, addressSum, nameSum, true));
        }
    }

    private void queryName(int addressSum, int nameSum) throws IOException {

        FileReader address = new FileReader("/Users/baidu/Downloads/Downloads/query.txt");
        BufferedReader br = new BufferedReader(address);
        String str = null;
        int wrone = 0;

        FileWriter addressWrong = new FileWriter("/Users/baidu/Downloads/Downloads/query-wrong.txt");
        BufferedWriter addressWrongB = new BufferedWriter(addressWrong);

        while ((str = br.readLine()) != null) {
            if (isAddress(str, addressSum, nameSum, true)) {
                addressWrongB.write(str);
                addressWrongB.newLine();
                System.out.println(str);
                wrone++;
            }
        }
        System.out.println("address wrone:" + wrone);
        br.close();
        address.close();
        addressWrongB.close();
        addressWrong.close();
    }

    private void address(int addressSum, int nameSum) throws IOException {
        FileReader address = new FileReader("/Users/baidu/Downloads/Downloads/address.txt");
        BufferedReader br = new BufferedReader(address);
        String str = null;
        int wrone = 0;

        FileWriter addressWrong = new FileWriter("/Users/baidu/Downloads/Downloads/address-wrong.txt");
        BufferedWriter addressWrongB = new BufferedWriter(addressWrong);

        while ((str = br.readLine()) != null) {
            if (!isAddress(str, addressSum, nameSum, false)) {
                addressWrongB.write(str);
                addressWrongB.newLine();
                System.out.println(str);
                wrone++;
            }
        }
        System.out.println("address wrone:" + wrone);
        br.close();
        address.close();
        addressWrongB.close();
        addressWrong.close();
    }

    private void name(int addressSum, int nameSum) throws IOException {
        FileReader name = new FileReader("/Users/baidu/Downloads/Downloads/name.txt");
        BufferedReader nameBr = new BufferedReader(name);
        String namestr = null;
        FileWriter nameWrong = new FileWriter("/Users/baidu/Downloads/Downloads/name-wrong.txt");
        BufferedWriter nameWrongB = new BufferedWriter(nameWrong);
        int wrone = 0;
        while ((namestr = nameBr.readLine()) != null) {
            if (isAddress(namestr, addressSum, nameSum, false)) {
                nameWrongB.write(namestr);
                nameWrongB.newLine();
                System.out.println(namestr);
                wrone++;
            }
        }
        System.out.println("name wrone:" + wrone);
        nameBr.close();
        name.close();
        nameWrongB.close();
        nameWrong.close();
    }

    private boolean isAddress(String readLine, int addressSum, int nameSum, boolean showDetail) {

        List<Term> terms = NlpAnalysis.parse(readLine);
        double addressCount = 1.0;
        double addressCount1 = 1.0;
        double nameCount = 1.0;
        double nameCount1 = 1.0;

        Map<String, Double> addressM = Maps.newHashMap();
        Map<String, Double> nameM = Maps.newHashMap();
        for (Term term : terms) {
            double addressp = 1.0;
            if (!addressMap.containsKey(term.getName())) {
                addressp = 0.4;
            } else {
                addressp = addressMap.get(term.getName());
            }

            double namep = 1.0;
            if (!nameMap.containsKey(term.getName())) {
                namep = 0.4;
            } else {
                namep = nameMap.get(term.getName());
            }

            double p = Double.valueOf(addressp) / Double.valueOf(addressSum + nameSum);
            double p1 = Double.valueOf(namep) / Double.valueOf(addressSum + nameSum);
            addressM.put(term.getName(), p / (p + p1));
            nameM.put(term.getName(), p1 / (p + p1));
            if (showDetail) {
                System.out.println(term.getName() + "属于address概率:" + p / (p + p1));
                System.out.println(term.getName() + "属于name概率:" + p1 / (p + p1));
            }
        }
        for (double p : addressM.values()) {
            addressCount = addressCount * p;
            addressCount1 = addressCount1 * (1 - p);
        }
        for (double p : nameM.values()) {
            nameCount = nameCount * p;
            nameCount1 = nameCount1 * (1 - p);
        }
        if (showDetail) {
            System.out.println(readLine + "属于address概率:" + addressCount / (addressCount + addressCount1));
            System.out.println(readLine + "属于name概率:" + nameCount / (nameCount + nameCount1));
        }
        return addressCount / (addressCount + addressCount1) > nameCount / (nameCount + nameCount1);
    }

    public static Map sortByValue(Map map, final boolean reverse) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                if (reverse) {
                    return -((Comparable) ((Map.Entry) (o1)).getValue())
                            .compareTo(((Map.Entry) (o2)).getValue());
                }
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    public static void main(String[] args) {
        new SplitString();
    }
}
