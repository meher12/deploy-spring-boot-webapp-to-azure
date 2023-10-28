package com.guru2azure.service;

import com.guru2azure.entity.Blog;
import com.guru2azure.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Blog updateBlog(Long blogId, Blog updatedBlog) throws Exception {
        Blog _blog = blogRepository.findById(blogId).orElseThrow(() -> new Exception("Not found blog with id = " + blogId));
        _blog.setTitle(updatedBlog.getTitle());
        _blog.setDescription(updatedBlog.getDescription());
        return blogRepository.save(_blog);
    }

    public Blog deleteBlog(Long blogId) throws Exception {
        Blog deletedBlog = blogRepository.findById(blogId).orElseThrow(() -> new Exception("Not found blog with id = " + blogId));
        blogRepository.deleteById(blogId);
        return deletedBlog;
    }

    public Boolean deleteBlogBoolean(Long blogId) throws Exception {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new Exception("Not found blog with id = " + blogId));
        if (blog == null) {
            return false;
        }
        blogRepository.deleteById(blogId);
        return true;

    }

}
