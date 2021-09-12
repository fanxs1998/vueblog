package com.code.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.common.lang.Result;
import com.code.entity.Blog;
import com.code.service.IBlogService;
import com.code.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanxs
 * @since 2021-09-05
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    IBlogService blogService;

    @GetMapping
    public Result blogs(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage, 5);

        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.success(pageData);
    }

    @GetMapping("/{blogId}")
    public Result detail(@PathVariable(name = "blogId") Long blogId) {

        Blog blog = blogService.getById(blogId);

        Assert.notNull(blog, "该博客不存在!");

        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp;

        if(blog.getBlogId() != null) {
            temp = blogService.getById(blog.getBlogId());
            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getUserId(), "没有权限编辑!");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getUserId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);

        }
        BeanUtils.copyProperties(blog, temp, "blogId", "userId", "created", "status");
        blogService.saveOrUpdate(temp);

        return Result.success("操作成功",null);
    }

}
