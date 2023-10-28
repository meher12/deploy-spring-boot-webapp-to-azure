/*
Development process with five steps:
    1. Add annotation @AutoConfigureMockMvc
    2. Inject the MockMvc
    3. Perform web requests
    4. Define expectations
    5. Assert results

 */

package com.guru2azure.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guru2azure.entity.Blog;
import com.guru2azure.service.BlogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//1. Add annotation @AutoConfigureMockMvc
@AutoConfigureMockMvc
@SpringBootTest
public class BlogControllerTests {


    public static final Logger LOGGER = LoggerFactory.getLogger(BlogControllerTests.class);
    //  2. Inject the MockMvc
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BlogService blogService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Get all blog Http request")
    public void testGetAllBlogsHttpRequest() throws Exception {

        // 3. Perform web requests
        // First approach  without index page as result
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/blogs"))
                // 4. Define expectations
                .andExpect(status().isOk())
                .andReturn()
                // without index page as result
                .getModelAndView();

        // Second approach  with index page as result i have used thymeleaf
       /*
       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/blogs"))
                // 4. Define expectations
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav, "index");
        */
    }

    @Test
    @DisplayName("Get blog by id Http request")
    public void testGetBlogById() throws Exception {
        // Define your expectations
        Blog mockBlog = new Blog(1L, "Sample Blog", "Sample Blog Description");
        when(blogService.getBlog(1L)).thenReturn(mockBlog);

        // Perform a web request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/blog/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Sample Blog")))
                .andExpect(jsonPath("$.description", is("Sample Blog Description")));

        // Verify expectations
        verify(blogService, times(1)).getBlog(1L);
    }

    /* Use the ArgumentCaptor class to capture the argument that is passed to the insertBlog() method:
    In this modified test, we use an ArgumentCaptor to capture the actual Blog object passed to the insertBlog method.
     Then, we compare the attributes of the captured Blog object with the expected newBlog object to ensure they match.
    This approach allows you to inspect the actual arguments passed to the insertBlog method and verify that they match your expectations,
     helping to diagnose and resolve any discrepancies leading to the error.

     */
    @Test
    @DisplayName("Test create blog http request")
    public void testCreateBlog() throws Exception {
        // Define a sample blog object for creating a new blog.
        Blog newBlog = new Blog(1L, "New Blog Title", "New Blog Content");

        // Capture the actual arguments passed to the insertBlog method
        ArgumentCaptor<Blog> blogCaptor = ArgumentCaptor.forClass(Blog.class);

        // Define your expectations for creating a new blog.
        when(blogService.insertBlog(blogCaptor.capture())).thenReturn(newBlog);

        // Perform a web request to create a new blog.
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/create-blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBlog)))
                .andExpect(status().isCreated()) // Assuming you return 201 CREATED on success.
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("New Blog Title")))
                .andExpect(jsonPath("$.description", is("New Blog Content")));

        // Verify expectations for creating a new blog.
        verify(blogService, times(1)).insertBlog(blogCaptor.getValue());

        // Now you can access the captured argument and assert its attributes
        Blog capturedBlog = blogCaptor.getValue();
        assertEquals(newBlog.getId(), capturedBlog.getId());
        assertEquals(newBlog.getTitle(), capturedBlog.getTitle());
        assertEquals(newBlog.getDescription(), capturedBlog.getDescription());

        // assert not null object
        assertNotNull(capturedBlog, "Blog should be valid.");
    }

    /*
     1. We create `ArgumentCaptor` instances for both the ID (`idCaptor`) and the `Blog` object (`blogCaptor`) to capture the arguments passed to the `updateBlog` method.
    2. We configure the `when` method to capture the arguments using these `ArgumentCaptor` instances.
    3. After performing the web request and verifying the update, you can use `idCaptor.getValue()` and `blogCaptor.getValue()` to
        retrieve the captured arguments and ensure that they match the expected values.
    This approach helps ensure that the arguments passed to the `updateBlog` method are captured correctly and can be verified against your expectations.
     */
    @Test
    @DisplayName("Test update blog http request")
    public void testUpdateBlog() throws Exception {
        // Define a sample blog object for updating an existing blog.
        Blog updatedBlog = new Blog(1L, "Updated Blog Title", "Updated Blog Content");

        // Create an ArgumentCaptor to capture the arguments passed to updateBlog.
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Blog> blogCaptor = ArgumentCaptor.forClass(Blog.class);

        // Define your expectations for updating an existing blog.
        when(blogService.updateBlog(idCaptor.capture(), blogCaptor.capture())).thenReturn(updatedBlog);

        // Perform a web request to update the existing blog.
        mockMvc.perform(put("/api/v1/update-blog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBlog)))
                .andExpect(status().isOk());

        // Verify expectations for updating an existing blog.
        verify(blogService, times(1)).updateBlog(idCaptor.getValue(), blogCaptor.getValue());

        // Additional assertions
        assertNotNull(idCaptor.getValue());
        assertNotNull(blogCaptor.getValue());
        assertEquals(1L, idCaptor.getValue().longValue());
        assertEquals("Updated Blog Title", blogCaptor.getValue().getTitle());
        assertEquals("Updated Blog Content", blogCaptor.getValue().getDescription());
    }


    @Test
    @DisplayName("Test delete blog http request")
    public void testDeleteBlog() throws Exception {
        // Mock the behavior of your service method for deleting a blog
        when(blogService.deleteBlogBoolean(1L)).thenReturn(true);
        when(blogService.deleteBlogBoolean(2L)).thenReturn(false); // Mock a failure scenario

        // Perform a web request to delete a blog with ID 1
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/blog/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Assuming you return 200 OK on successful deletion

        // Verify that the deleteBlog method was called with the correct ID
        Mockito.verify(blogService, Mockito.times(1)).deleteBlogBoolean(1L);

        // Perform a web request to delete a blog with ID 2 (for a failure scenario)
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/blog/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Assuming you return 404 Not Found on failure

        // Verify that the deleteBlog method was called with the correct ID
        verify(blogService, Mockito.times(1)).deleteBlogBoolean(2L);

        // Assert that the deleteBlog method returned false for a failure scenario
        assertFalse(blogService.deleteBlogBoolean(2L));
    }
}

