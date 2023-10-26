package com.guru2azure.service.test;

import com.guru2azure.entity.Blog;
import com.guru2azure.exception.BlogNotFoundException;
import com.guru2azure.repository.BlogRepository;
import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/* **** MockBean: @MockBean instead of @Mock AND @Autowired instead of @InjectMocks ****
 * I Use @MockBean to mock beans and @Autowired to inject dependencies. Here's an example using @MockBean and @Autowired in a Spring Boot application:
 * I have a service class BlogService that depends on a repository BlogRepository.
 * I want to write an integration test for the service and mock the repository using @MockBean.
 *
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("/application-test.yml")
public class BlogServiceMockBeanTest {

    public final static Logger LOGGER = LoggerFactory.getLogger(BlogServiceMockBeanTest.class);

    @Autowired
    private BlogService blogService;

    @MockBean
    private BlogRepository blogRepository;


    @Test
    @DisplayName("Test Get All Blogs")
    @Order(1)
    void testGetAllBlogs() {
        // Arrange
        Blog blog1 = new Blog();
        blog1.setId(1L);
        blog1.setTitle("Blog 1");
        blog1.setDescription("Content 1");

        Blog blog2 = new Blog();
        blog2.setId(2L);
        blog2.setTitle("Blog 2");
        blog2.setDescription("Content 2");

        List<Blog> mockBlogs = Arrays.asList(blog1, blog2);

        // Define mock behavior and return a list of Blog as result
        when(blogRepository.findAll()).thenReturn(mockBlogs);

        // Act
        List<Blog> result = blogService.getAllBlogs();

        // Ensure the service method returned a non-null result
        assertNotNull(result);
        // Ensure the service method returned the correct result
        assertEquals(2, result.size());
        assertEquals("Blog 1", result.get(0).getTitle());
        assertEquals("Content 1", result.get(0).getDescription());
        assertEquals("Blog 2", result.get(1).getTitle());
        assertEquals("Content 2", result.get(1).getDescription());

        // Assert
        Mockito.verify(blogRepository).findAll(); // Verifying the method call
    }

    @Test
    @DisplayName("Test Insert blog")
    @Order(2)
    public void testInsertBlog() {
        // Arrange
        Blog blogToInsert = new Blog();
        blogToInsert.setTitle("New Blog");
        blogToInsert.setDescription("This is a new blog post");

        Blog savedBlog = new Blog();
        savedBlog.setId(1L); // Simulate the generated ID after insertion
        savedBlog.setTitle("New Blog");
        savedBlog.setDescription("This is a new blog post");

        Mockito.when(blogRepository.save(blogToInsert)).thenReturn(savedBlog);

        // Act
        Blog result = blogService.insertBlog(blogToInsert);

        // Assert
        Mockito.verify(blogRepository).save(blogToInsert); // Verify the save method was called

        assertNotNull(result); // Ensure that the result is not null

        // Validate the properties of the saved blog
        assertEquals(1L, result.getId());
        assertEquals("New Blog", result.getTitle());
        assertEquals("This is a new blog post", result.getDescription());
    }

    @Test
    @DisplayName("Test Update blog")
    @Order(3)
    public void testUpdateBlog() throws Exception {
        // Arrange
        Long blogId = 1L;

        // Create an updated blog entity
        Blog updatedBlog = new Blog();
        updatedBlog.setId(blogId);
        updatedBlog.setTitle("Updated Blog");
        updatedBlog.setDescription("This is the updated content");

        // Mock the behavior of blogRepository to return the updatedBlog when getById is called
        when(blogRepository.findById(blogId)).thenReturn(Optional.of(updatedBlog));

        // Mock the behavior of blogRepository to return the savedBlog when save is called
        when(blogRepository.save(updatedBlog)).thenReturn(updatedBlog);

        // Act
        Blog result = blogService.updateBlog(blogId, updatedBlog);

        // Assert
        assertNotNull(result); // Ensure that the result is not null


        // Validate the properties of the updated blog
        assertEquals(blogId, result.getId());
        assertEquals("Updated Blog", result.getTitle());
        assertEquals("This is the updated content", result.getDescription());

        verify(blogRepository).findById(blogId); // Verify that the getById method was called with the correct ID
        verify(blogRepository).save(updatedBlog); // Verify that the save method was called with the updated blog entity
    }

    @Test
    @DisplayName("Test delete blog")
    public void testDeleteBlog() throws Exception {
        Long blogId = 1L;

        // Create an deleted blog entity
        Blog deletedBlog = new Blog();
        deletedBlog.setId(blogId);
        deletedBlog.setTitle("Deleted Blog");
        deletedBlog.setDescription("This is the deleted blog");

        when(blogRepository.findById(blogId)).thenReturn(Optional.of(deletedBlog));

        Blog result = blogService.deleteBlog(blogId);
        assertNotNull(result);

        Optional<Blog> deletedBlg = blogRepository.findById(blogId);
        assertTrue(deletedBlg.isPresent());
    }

    @Test
    @DisplayName("Blog not found by Id")
    void testGetBlogByIdWithException() {
        // Arrange
        Long nonExistentBlogId = 1L;

        // Mock the behavior of blogRepository.findById to throw a BlogNotFoundException
        when(blogRepository.findById(nonExistentBlogId)).thenThrow(new BlogNotFoundException("Blog not found with ID: " + nonExistentBlogId));

        // Act and Assert
        assertThrows(BlogNotFoundException.class, () -> blogService.getBlog(nonExistentBlogId));
    }


}
