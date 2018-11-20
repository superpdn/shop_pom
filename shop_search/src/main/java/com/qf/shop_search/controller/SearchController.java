package com.qf.shop_search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author pdn
 * @Time 2018/11/20 20:14
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Reference
    private ISearchService searchService;
    /**
     * 搜素商品列表
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/list")
    public String searchAll(String keyword){
        System.out.println("搜索的关键字："+keyword);
        return "searchlist";
    }

    /**
     * 添加索引
     * @return
     */
    @RequestMapping(value = "/add")
    public String addIndex(@RequestBody Goods goods){
        int index = searchService.addIndex(goods);
        if(index==1){
            return "succ";
        }
        return "error";
    }
}
