package com.qf.shop_service_provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * @Author pdn
 * @Time 2018/11/20 22:49
 * @Version 1.0
 */
@Service
public class SearchServiceImpl implements ISearchService {


    @Autowired
    private SolrClient solrClient;

    /**
     * 添加索引
     * @param goods
     * @return
     */
    @Override
    public int addIndex(Goods goods) {
        try {
            SolrInputDocument solrInputFields=new SolrInputDocument();
            solrInputFields.addField("id",goods.getId());
            solrInputFields.addField("gtitle",goods.getTitle());
            solrInputFields.addField("ginfo",goods.getGinfo());
            solrInputFields.addField("gcount",goods.getGcount());
            solrInputFields.addField("gimage",goods.getGimage());
            solrInputFields.addField("gprice",goods.getPrice());
            solrClient.add(solrInputFields);
            solrClient.commit();
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Goods> queryIndex(String keyword) {
        return null;
    }
}
