package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

public interface ISearchService {

    int addIndex(Goods goods);

    List<Goods> queryIndex(String keyword);
}
