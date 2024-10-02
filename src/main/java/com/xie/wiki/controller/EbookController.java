package com.xie.wiki.controller;

import com.xie.wiki.req.EbookQueryReq;
import com.xie.wiki.req.EbookReq;
import com.xie.wiki.req.EbookSaveReq;
import com.xie.wiki.resp.CommonResp;
import com.xie.wiki.resp.EbookQueryResp;
import com.xie.wiki.resp.EbookResp;
import com.xie.wiki.resp.PageResp;
import com.xie.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        System.out.println(id);
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }

}
