package com.example.todoapp.models;

import java.util.Date;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Todo.
 */
@Document(collection = "todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true) // ignores createdAt field during deserialization.
public class Todo {

    @Id
    private String id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique = true)
    private String title; // creates a unique index on the title field

    private Boolean completed = false;

    private Date createdAt = new Date();

    /**
     * Instantiates a new Todo.
     */
    public Todo() {
        super();
    }

    /**
     * Instantiates a new Todo.
     *
     * @param title the title
     */
    public Todo(String title) {
        this.title = title;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets completed.
     *
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, title='%s', completed='%s']",
                id, title, completed);
    }
}
