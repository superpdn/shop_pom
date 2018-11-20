package com.qf.shop_service_provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.shop.dao.IGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author pdn
 * @Time 2018/11/19 18:51
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public List<Goods> queryAll() {
        return goodsDao.queryAll();
    }

    @Override
    public Goods addGoods(Goods goods) {
        goodsDao.addGoods(goods);
        return goods;
    }

    @Override
    public List<Goods> queryNewGoods() {
        return goodsDao.queryNewGoods();
    }
}
