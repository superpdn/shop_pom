package com.qf.shop_search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSearchApplicationTests {

    @Autowired
    private SolrClient solrClient;

    /**
     * 添加索引库
     */
    @Test
    public void contextLoads() throws IOException, SolrServerException {

        //相当于插入数据库的一条记录
        for (int i=0;i<20;i++){
            SolrInputDocument document=new SolrInputDocument();
            document.addField("id",i);
            document.addField("gtitle","华为手机"+i);
            document.addField("ginfo","这是中国的果茶手机"+i);
            document.addField("gprice","222");
            document.addField("gcount","200");
            document.addField("gimage","http://www.baidu.com");

            solrClient.add(document);
        }
        solrClient.commit();
    }

    /**
     * 修改索引库
     */
    @Test
    public void updateIndex() throws IOException, SolrServerException {
        SolrInputDocument document=new SolrInputDocument();
        document.addField("id",1);
        document.addField("gtitle","华为手机");
        document.addField("ginfo","这是中国的手机");
        document.addField("gprice","222");
        document.addField("gcount","200");
        document.addField("gimage","http://www.jd.com");
        solrClient.add(document);
        solrClient.commit();
    }

    /**
     * 删除索引库中的值
     */
    @Test
    public void deleteIndex() throws IOException, SolrServerException {
//        solrClient.deleteById("0");
//        solrClient.commit();
        solrClient.deleteByQuery("gtitle:华为");
        solrClient.commit();

    }

    /**
     * 查询索引库
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void  query() throws IOException, SolrServerException {
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("gtitle:苹果 || ginfo:中国");
        QueryResponse response = solrClient.query(solrQuery);
        SolrDocumentList results = response.getResults();
        for (SolrDocument result : results) {
            String id = (String) result.get("id");
            String gtitle = (String) result.get("gtitle");
            String ginfo = (String) result.get("ginfo");
            float gprice = (float) result.get("gprice");
            float gcount = (float) result.get("gcount");
            String gimage = (String) result.get("gimage");
            System.out.println(id+","+gtitle+","+ginfo+","+gprice+","+gcount+","+gimage);
        }
    }


}
