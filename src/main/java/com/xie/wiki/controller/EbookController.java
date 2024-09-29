package com.xie.wiki.controller;

import com.xie.wiki.req.EbookReq;
import com.xie.wiki.resp.CommonResp;
import com.xie.wiki.resp.EbookResp;
import com.xie.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.findAll(req);
        resp.setContent(list);
        return resp;
    }

}
