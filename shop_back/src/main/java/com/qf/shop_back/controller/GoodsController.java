package com.qf.shop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author pdn
 * @Time 2018/11/19 17:57
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${image.path}")
    private String spath;

    /**
     * 展示商品信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String goodsList(Model model){
        List<Goods> goods = goodsService.queryAll();
        model.addAttribute("goods",goods);
        model.addAttribute("spath",spath);
        return "goodslist";
    }

    /**
     * 图片的上传与商品的添加
     * @param goods
     * @param gfile
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/add")
    public String goodsAdd(Goods goods, MultipartFile gfile) throws IOException {

        StorePath result = fastFileStorageClient.uploadImageAndCrtThumbImage(
                gfile.getInputStream(),
                gfile.getSize(),
                "jpg", null);
        String path = result.getFullPath();
        //成功 上传到数据库
        goods.setGimage(path);
        //调用service保存商品
        goods=goodsService.addGoods(goods);
        //调用搜索工程的接口，同步到索引库中
        HttpClientUtil.sendJson("http://localhost:8082/search/add",new Gson().toJson(goods));
        return "redirect:/goods/list";
    }

    /**
     * 展示最新添加的商品
     * @return
     */
//    @RequestMapping(value = "/querynew")
//    @ResponseBody
//    public String queryNewGoods(boolean flag){
//        List<Goods> goods = goodsService.queryNewGoods();
//        System.out.println("查询新品列表："+goods);
//        return flag ? "hello("+new Gson().toJson(goods)+")" : new Gson().toJson(goods) ;
//    }


    /**
     * 第二种新品展示
     * @return
     */
    @RequestMapping(value = "/querynew")
    @ResponseBody
    @CrossOrigin
    public  List<Goods> queryNewGoods(){
        List<Goods> goods = goodsService.queryNewGoods();
        System.out.println("查询新品列表："+goods);
        return goods ;
    }
}
