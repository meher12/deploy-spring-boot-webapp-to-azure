package com.guru2azure.controller;

import com.guru2azure.entity.Blog;
import com.guru2azure.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Hi Microsoft Azure ", HttpStatus.OK);
    }

    @GetMapping("blogs")
    public ResponseEntity<List<Blog>> getBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Use it to test ModelAndViewAssert.assertViewName
    /*@GetMapping("/your-endpoint")
    public ModelAndView yourEndpoint() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("someAttribute", "someValue");
        return modelAndView;
    }*/

    @GetMapping("blog/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable("id") Long id) {
        Blog blog = blogService.getBlog(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PostMapping("create-blog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blogRequest) {
        Blog blog = blogService.insertBlog(blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PutMapping("update-blog/{id}")
    public ResponseEntity<Blog> createBlog(@PathVariable("id") Long id, @RequestBody Blog blogRequest) throws Exception {
        Blog blog = blogService.updateBlog(id, blogRequest);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping("blog/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable("id") Long id) throws Exception {
        if (!blogService.deleteBlogBoolean(id)) {
            return ResponseEntity.notFound().build();
        }
        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    }

}
