package com.kimutai.bookmarker_api_k8s.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BookmarkDTO {
    private Long id;

    private String title;

    private String url;

    private Instant createdAt;
}
