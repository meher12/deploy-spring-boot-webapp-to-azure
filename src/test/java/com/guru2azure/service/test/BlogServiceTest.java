package com.guru2azure.service.test;

import com.guru2azure.entity.Blog;
import com.guru2azure.repository.BlogRepository;
import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BlogServiceTest {

    //@Mock is used to create a mock of the BlogRepository.
    @Mock
    BlogRepository blogRepository;

    //@InjectMocks is used to inject the BlogService
    @InjectMocks
    BlogService blogService;


    @Test
    @DisplayName("Test blog by id")
    public void testGetBlogById() {

        Blog sampleBlog = new Blog();
        Long blogId = 1L;
        sampleBlog.setId(blogId);
        sampleBlog.setTitle("Sample Blog");
        sampleBlog.setDescription("This is a sample blog post.");

        // Define mock behavior and return a Blog as result
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(sampleBlog));

        // Test the service method
        Blog result = blogService.getBlog(blogId);

        // Ensure the service method returned a non-null result
        assertNotNull(result);

        // Verify the result
        // Ensure the service method returned the correct result
        assertEquals(blogId, result.getId(), "Id must be equals");
        assertEquals("Sample Blog", result.getTitle(), "Title must be equals");
        assertEquals("This is a sample blog post.", result.getDescription(), "Description must be equals");

        // Verify that the method on the mock repository was called
        // this step is optional
        verify(blogRepository).findById(blogId);

    }
}
