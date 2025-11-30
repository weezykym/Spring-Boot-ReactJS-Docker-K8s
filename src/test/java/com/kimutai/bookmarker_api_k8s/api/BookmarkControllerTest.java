package com.kimutai.bookmarker_api_k8s.api;

import com.kimutai.bookmarker_api_k8s.domain.Bookmark;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url= jdbc:tc:postgresql:14-alpine:///bookmarks-kim"
})
class BookmarkControllerTest {
    @Autowired
    private MockMvc mvc;

    private List<Bookmark> bookmarks;

    @ParameterizedTest
    @CsvSource({
            "1, 12, 5, 3, 1, true, false, true, false",
            "2, 12, 5, 3, 2, false, false, true, true",
            "3, 12, 2, 3, 3, false, true, false, true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements,
                            int currentElements, int totalPages,
                            int currentPage, boolean isFirst,
                            boolean isLast, boolean hasNext,boolean hasPrevious) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentElements", CoreMatchers.equalTo(currentElements)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));
    }
}