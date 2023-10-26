package com.guru2azure.service.test;

import com.guru2azure.entity.Blog;
import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource("/application-test.properties")
public class BlogServiceDBTest {

    @Autowired
    private BlogService blogService;

    @Sql("/insertBlogs.sql")
    @Test
    public void testInsertMultipleBlogs() {
        // Your test logic to verify the inserted blog entries
        List<Blog> blogs = blogService.getAllBlogs(); // Assuming you have a method to retrieve all blogs

        // Assertions to verify the inserted data
        assertEquals(2, blogs.size()); // Assuming you inserted two blog entries
        assertEquals("Blog Title 1", blogs.get(0).getTitle());
        assertEquals("Blog Title 2", blogs.get(1).getTitle());
        // Add more assertions as needed
    }
}
