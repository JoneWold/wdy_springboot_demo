package com.wdy.springboot.service.impl;

import com.wdy.springboot.entity.BlogEntity;
import com.wdy.springboot.mapper.BlogMapper;
import com.wdy.springboot.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdy
 * @since 2021-02-03
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, BlogEntity> implements IBlogService {

}
