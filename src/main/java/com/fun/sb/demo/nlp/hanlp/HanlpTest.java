package com.fun.sb.demo.nlp.hanlp;

import com.fun.sb.demo.nlp.WordCount;
import com.google.common.collect.Lists;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.StopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

/**
 * Created by shangbin01 on 2015/12/14.
 */
public class HanlpTest {

    private CoreStopWordDictionary coreStopWordDictionary = new CoreStopWordDictionary();

    public HanlpTest() {
        CustomDictionary.add("单子ID");
        CustomDictionary.add("团单ID");
        CustomDictionary.add("T10");
        CustomDictionary.add("公私海");
        CustomDictionary.add("到店付");
        CoreStopWordDictionary.add("谢谢");
    }

    public List<String> getWrods(String str) {
        List<Term> terms = NLPTokenizer.segment(str);
        List<String> result = Lists.newArrayList();
        for (Term term : terms) {
            result.add(term.word);
        }
        return result;
    }

    public List<Term> getWrodWithoutStopwordStr(String str) {
        List<Term> terms = NLPTokenizer.segment(str);
        coreStopWordDictionary.apply(terms);
        return terms;
    }

}
