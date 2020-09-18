package com.flitsneak.service.impl;

import com.alibaba.fastjson.JSON;
import com.flitsneak.entity.Result;
import com.flitsneak.feign.CategoryFeign;
import com.flitsneak.feign.SkuFeign;
import com.flitsneak.feign.SpuFeign;
import com.flitsneak.goods.pojo.Sku;
import com.flitsneak.goods.pojo.Spu;
import com.flitsneak.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageserviceImpl implements PageService {
    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private TemplateEngine templateEngine;
    @Value("${pagepath}")
    private String pagepath;

    /**
     * 构建数据模型
     * @param spuId
     * @return
     */
    private Map<String,Object> buildDataModel(Long spuId){
        //构建数据模型
        Map<String,Object> dataMap = new HashMap<>();
        //获取spu 和sku列表
        Result<Spu> result = spuFeign.findById(spuId);
        Spu spu = result.getData();
        //获取分类信息
        dataMap.put("category1",categoryFeign.findById(spu.getCategory1Id()).getData());
        dataMap.put("category2",categoryFeign.findById(spu.getCategory2Id()).getData());
        dataMap.put("category3",categoryFeign.findById(spu.getCategory3Id()).getData());
        if (spu.getImages()!=null){
            dataMap.put("imageList",spu.getImages().split(","));
        }
        dataMap.put("specificationList", JSON.parseObject(spu.getSpecItems(),Map.class));
        dataMap.put("spu",spu);
        //根据spuId查询sku集合
        Sku skuCondition = new Sku();
        skuCondition.setSpuId(spu.getId());
        Result<List<Sku>> resultSku = skuFeign.findList(skuCondition);
        return dataMap;
    }

    /**
     * 生成静态页
     * @param spuId
     */
    @Override
    public void createPageHtml(Long spuId) {
        //1.上下文
        Context context =new Context();
        Map<String,Object> dataModel1 = buildDataModel(spuId);
        context.setVariables(dataModel1);
        //2.准备文件
        File dir = new File(pagepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dest = new File(dir, spuId + ".html");
        // 3.生成页面
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
