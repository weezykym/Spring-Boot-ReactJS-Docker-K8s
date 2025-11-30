package com.kimutai.bookmarker_api_k8s.api;

import com.kimutai.bookmarker_api_k8s.domain.BookmarksPageDTO;
import com.kimutai.bookmarker_api_k8s.domain.BookmarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }
    @GetMapping()
    public BookmarksPageDTO getAllBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return bookmarkService.getAllBookmarks(page);
    }
}
