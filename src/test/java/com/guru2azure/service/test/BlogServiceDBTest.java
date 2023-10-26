package com.guru2azure.service.test;

import com.guru2azure.entity.Blog;
import com.guru2azure.repository.BlogRepository;
import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestPropertySource("/application-test.yml")
public class BlogServiceDBTest {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private long testBlogId; // Store the ID of the test blog for later retrieval

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("INSERT INTO blogs(id, title, description) VALUES(3,'Test Blog 3', 'Test Content 3')");
        // Store the ID of the inserted blog
        testBlogId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM blogs", Long.class);
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM blogs");
    }

    /*@Sql("/insertBlogs.sql")
    @Test
    @DisplayName("Test using @Sql")
    public void testInsertMultipleBlogs() {
        // Your test logic to verify the inserted blog entries
        List<Blog> blogs = blogService.getAllBlogs(); // Assuming you have a method to retrieve all blogs

        // Assertions to verify the inserted data
        assertEquals(2, blogs.size()); // Assuming you inserted two blog entries
        assertEquals("Blog Title 1", blogs.get(0).getTitle());
        assertEquals("Blog Title 2", blogs.get(1).getTitle());
    }*/

    @Test
    @DisplayName("Test Using jdbcTemplate")
    public void testFindBlogById() {
        // Use the BlogRepository to find the blog by ID
        Optional<Blog> retrievedBlog = blogRepository.findById(testBlogId);

        // Assertions to verify the retrieved blog
        assertNotNull(retrievedBlog);
        assertEquals("Test Blog 3", retrievedBlog.get().getTitle());
        assertEquals("Test Content 3", retrievedBlog.get().getDescription());
    }
}
