package com.guru2azure.service;

import com.guru2azure.entity.Blog;
import com.guru2azure.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found Project with id = " + id));
        return blog;
    }

    public Blog insertBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}
