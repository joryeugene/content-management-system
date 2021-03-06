package com.teamexcalibur.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Post {

    private int id;
    private User author;
    @NotNull(message = "Title cannot be empty")
    @NotEmpty(message = "You must supply a title.")
    @Length(max = 80, message = "Title must be no more than 80 characters in length.")
    private String title;
    private String content;
    private int numViews;
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String stringStartDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String stringEndDate;
    private Category category;
    private List<String> hashtags;
    private boolean queued;

    public Post() {
    }

    public Post(User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<String> hashtags, boolean queued) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.numViews = numViews;
        this.setStringEndDate(stringEndDate);
        this.setStringStartDate(stringStartDate);
        this.category = category;
        this.hashtags = hashtags;
        this.queued = queued;
    }

    public Post(int id, User author, String title, String content, int numViews, String stringStartDate, String stringEndDate, Category category, List<String> hashtags, boolean queued) {
        this(author, title, content, numViews, stringStartDate, stringEndDate, category, hashtags, queued);
        this.id = id;
    }
    
   public Post(LocalDate startDate, LocalDate endDate){
       this.setStartDate(startDate);
       this.setEndDate(endDate);
   }

    public static Comparator<Post> PostViewsComparator
                          = new Comparator<Post>() {

	    public int compare(Post post1, Post post2) {
	      //ascending order
	      return post1.numViews - post2.numViews;
	    }
    };
            
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.author == null) != (other.author == null))
            return false;
        if (this.author != null && other.author != null
                && this.author.getId() != other.author.getId()) {
            return false;
        }
        if (!this.title.equals(other.title)) {
            return false;
        }
        if (!this.content.equals(other.content)) {
            return false;
        }
        if (this.numViews != other.numViews) {
            return false;
        }
        if ((this.stringStartDate == null) != (other.stringStartDate == null))
            return false;
        if (this.startDate != null && other.startDate != null
                && !this.getStringStartDate().equals(other.getStringStartDate())) {
            return false;
        }
        if ((this.stringEndDate == null) != (other.stringEndDate == null))
            return false;
        if (this.endDate != null && other.endDate != null
                && !this.getStringEndDate().equals(other.getStringEndDate())) {
            return false;
        }
        if (this.category.getId() != other.category.getId()) {
            return false;
        }
        if (this.hashtags.size() != other.hashtags.size()) {
            return false;
        }
        if (this.queued != other.queued) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            this.startDate = LocalDate.now();
        } else {
            this.startDate = startDate;
        }
        stringStartDate = startDate.toString();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate == null)
            this.endDate = (LocalDate.now().plusYears(100));
        else
            this.endDate = endDate;
        stringEndDate = endDate.toString();
    }

    public String getStringStartDate() {
        if (startDate == null)
            return LocalDate.now().toString();
        return startDate.toString();
    }

    public void setStringStartDate(String stringStartDate) {
        if (stringStartDate == null || stringStartDate.isEmpty()) {
            this.setStartDate(LocalDate.now());
            return;
        }
        this.setStartDate(LocalDate.parse(stringStartDate.replaceAll("/", "-")));
    }

    public String getStringEndDate() {
        if (endDate == null)
            return LocalDate.now().plusYears(100).toString();
        return endDate.toString();
    }

    public void setStringEndDate(String stringEndDate) {
        if (stringEndDate == null || stringEndDate.isEmpty()) {
            this.setEndDate(LocalDate.now().plusYears(100));
            return;
        }
        this.setEndDate(LocalDate.parse(stringEndDate.replaceAll("/", "-")));
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public boolean isQueued() {
        return queued;
    }

    public void setQueued(boolean queued) {
        this.queued = queued;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
