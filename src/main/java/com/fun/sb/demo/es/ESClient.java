package com.fun.sb.demo.es;

import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * ${类描述}
 * User: shangbin01
 * Date: 16/3/30
 * Time: 下午1:19
 */
public class ESClient {

    public void search() throws Exception {

        SearchSourceBuilder search = new SearchSourceBuilder();
//        FilterBuilders.geoHashCellFilter("localtion").geohash("1").precision(1).toString();

        System.out.println(FilterBuilders.geoHashCellFilter("localtion").geohash("1").precision(1).toString());

    }

    public static void main(String[] args) throws Exception {
        new ESClient().search();
    }
}
