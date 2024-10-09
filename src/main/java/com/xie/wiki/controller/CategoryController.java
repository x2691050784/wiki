package com.xie.wiki.controller;

import com.xie.wiki.req.CategoryQueryReq;
import com.xie.wiki.req.CategorySaveReq;
import com.xie.wiki.resp.CommonResp;
import com.xie.wiki.resp.CategoryQueryResp;
import com.xie.wiki.resp.PageResp;
import com.xie.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        System.out.println(id);
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }

}
