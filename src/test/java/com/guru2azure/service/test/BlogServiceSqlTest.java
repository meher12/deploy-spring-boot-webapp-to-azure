package com.guru2azure.service.test;

import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class BlogServiceSqlTest {

    @Value("${sql.script.create.blogs}")
    private String sqlCreateBlog;

    @Value("${sql.script.delete.blogs}")
    private String sqlDeleteBlog;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BlogService blogService;


    @BeforeEach
    public void setupDataBase() {
        jdbcTemplate.execute(sqlCreateBlog);
    }

    @Test
    public void isStudentNullCheck() throws Exception {
        // return true because exist in the DB
        assertTrue(blogService.checkIfBlogIsNull(50L));
        // return false because not exist in the DB
        assertFalse(blogService.checkIfBlogIsNull(30L));
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.execute("DELETE FROM blogs");
    }
}
