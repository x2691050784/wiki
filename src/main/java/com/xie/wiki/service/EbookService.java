package com.xie.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xie.wiki.domain.Ebook;
import com.xie.wiki.domain.EbookExample;
import com.xie.wiki.mapper.EbookMapper;
import com.xie.wiki.req.EbookReq;
import com.xie.wiki.resp.EbookResp;
import com.xie.wiki.resp.PageResp;
import com.xie.wiki.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG= LoggerFactory.getLogger(EbookService.class);
    @Resource //可以用@Autowired
    private EbookMapper ebookMapper;
    public PageResp<EbookResp> findAll(EbookReq req) {
        PageHelper.startPage(req.getPage(),req.getSize());
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }


        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo=new PageInfo<>(ebookList);
        LOG.info("数据总数:{}",pageInfo.getTotal());
        LOG.info("分页数量:{}",pageInfo.getPages());

        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebookList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //     // 对象复制
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //
        //     respList.add(ebookResp);
        // }

        // 列表复制
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        PageResp<EbookResp> pageResp=new PageResp<>();
        pageResp.setList(list);

        return pageResp;
    }
}
