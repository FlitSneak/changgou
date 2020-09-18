package com.flitsneak.controller;

import com.flitsneak.entity.Result;
import com.flitsneak.entity.StatusCode;
import com.flitsneak.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {
    @Autowired
    private PageService pageService;

    /**
     * 生产静态页
     *
     */
    @RequestMapping("/createHtml/{id}")
    public Result createHtml(@PathVariable(name = "id") Long id){
        pageService.createPageHtml(id);
        return new Result(true, StatusCode.OK,"ok");

    }
}
