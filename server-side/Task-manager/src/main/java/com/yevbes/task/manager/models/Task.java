package com.yevbes.task.manager.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "tasks")
public class Task {
    @Id
    @NotNull
    @ApiModelProperty(value = "the task's id", required = true)
    private String id;
    @ApiModelProperty(value = "the task's title")
    private String title;
    @ApiModelProperty(value = "the task's description")
    private String description;
    @ApiModelProperty(value = "the task's color")
    private int color;
    private boolean created;
    private boolean doing;
    private boolean finished;

    public Task(
            String title,
            String description,
            int color
    ) {
        this.title = title;
        this.description = description;
        this.color = color;
        setTaskToCreated();
    }

    public void setTaskToCreated() {
        doing = false;
        finished = false;
        created = true;
    }

    public void setTaskToDoing() {
        created = false;
        finished = false;
        doing = true;
    }

    public void setTaskToFinished() {
        created = false;
        doing = false;
        finished = true;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isDoing() {
        return doing;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", color=" + color +
                '}';
    }
}
