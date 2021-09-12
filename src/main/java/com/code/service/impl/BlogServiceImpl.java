package com.code.service.impl;

import com.code.entity.Blog;
import com.code.mapper.BlogMapper;
import com.code.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanxs
 * @since 2021-09-05
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
