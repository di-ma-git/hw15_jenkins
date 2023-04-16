package org.example.hw12.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private long id;
    private String bookName;
    private String description;
    private String publishDate;
    private String author;
    private long authorId;


}
