package com.demo.springboot.controller;

import com.demo.springboot.common.Constants;
import com.demo.springboot.common.lang.MyCommonResult;
import com.demo.springboot.common.pagation.PageList;
import com.demo.springboot.domain.IncomeMaterielInfo;
import com.demo.springboot.service.IncomeMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

/**
 * 来料管理
 * @Author: wh
 * @Date: 2020/2/8
 */
@Controller
@RequestMapping("/income/")
public class IncomeMaterielController {

    @Autowired
    private IncomeMaterielService incomeMaterielService;

    @RequestMapping("init")
    public String init() {
        return "income";
    }

    /**
     * 查询来料信息
     * @param limit
     * @param offset
     * @param info
     * @return
     */
    @RequestMapping("listIncomeMateriel")
    @ResponseBody
    public PageList<IncomeMaterielInfo> listIncomeMateriel(Integer limit, Integer offset, IncomeMaterielInfo info) {

        Integer tOffset = Optional.ofNullable(offset).orElse(Constants.DEFAULT_PAGE);
        Integer tSize = Optional.ofNullable(limit).orElse(Constants.PAGE_SIZE);

        return incomeMaterielService.listMaterielStock(tSize, tOffset, info);
    }

    @RequestMapping("add")
    @ResponseBody
    public MyCommonResult add(IncomeMaterielInfo info) {
        info.setIncomeDate(new Date());
        return incomeMaterielService.add(info);
    }

    @RequestMapping("remove")
    @ResponseBody
    public MyCommonResult remove(@RequestParam("ids[]") Integer[] ids) {
        return incomeMaterielService.remove(ids);
    }
}