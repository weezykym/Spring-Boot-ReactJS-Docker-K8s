package com.kimutai.bookmarker_api_k8s.domain;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookmarkService {
    private final BookmarkRepository bookmarkerRepository;

    public BookmarkService(BookmarkRepository bookmarkerRepository, BookmarkMapper bookmarkMapper) {
        this.bookmarkerRepository = bookmarkerRepository;
    }

    @Transactional()
    public BookmarksPageDTO getAllBookmarks(Integer page) {
        int pageNo = page < 1 ? 0 : page-1;
        PageRequest pageable = PageRequest.of(pageNo, 5, Sort.Direction.DESC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = bookmarkerRepository.findBookmarks(pageable);
        return new BookmarksPageDTO(bookmarkPage);
    }
}
