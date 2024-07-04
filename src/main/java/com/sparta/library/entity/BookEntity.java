package com.sparta.library.entity;

import com.sparta.library.book.dto.BookRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@SuperBuilder
@Data
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 50)
    private String author;

    @Column(length = 50)
    private String language;

    @Column(length = 50)
    private String publisher;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;

    public static BookEntity from(BookRequestDTO bookRequestDto) {
        return BookEntity.builder()
                .id(bookRequestDto.id())
                .title(bookRequestDto.title())
                .author(bookRequestDto.author())
                .language(bookRequestDto.language())
                .publisher(bookRequestDto.publisher())
                .build();
    }
}