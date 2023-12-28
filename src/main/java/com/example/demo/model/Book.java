package com.example.demo.model;

public class Book {
    private Long id;

    private String title;
    private String author;
    private Long categoryId;
    private String releaseDate;
    private Double price;
    private String imgCover;

    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getImgCover() {
        return this.imgCover;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public void setAuthor( String author) {
        this.author = author;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImgCover(String imgCover) {
        this.imgCover = imgCover;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categoryId=" + categoryId +
                ", releaseDate='" + releaseDate + '\'' +
                ", price=" + price +
                ", imgCover='" + imgCover + '\'' +
                '}';
    }

}
