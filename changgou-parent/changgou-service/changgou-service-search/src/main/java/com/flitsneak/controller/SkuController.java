package com.flitsneak.controller;

import com.flitsneak.entity.Result;
import com.flitsneak.entity.StatusCode;
import com.flitsneak.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 导入数据
     * @return
     */
    @GetMapping("/import")
    public Result search(){
        skuService.importSku();
        return new Result(true, StatusCode.OK,"导入数据到索引库中成功！");
    }
    /**
     * 搜索商品数据
     * @param searchMap
     * @return
     */
    @GetMapping
    public Map search(@RequestParam(required = false) Map searchMap){
        return  skuService.search(searchMap);
    }
}

