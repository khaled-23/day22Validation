package com.example.lab5porjecttracker.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectTracker {
    @NotEmpty(message = "id should not be empty")
    @Size(min = 3,message = "id should be at least 3 characters")
    private String ID;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 9,message = "title should be at least 9 characters")
    private String title;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 16,message = "description should be at least 16 characters")
    private String description;
    @NotEmpty(message = "Status can't be empty")
    @Pattern(regexp = "^(Not Started|In Progress|Completed)", message = "a status can only be Not Started, in Progress or Completed.")
    private String status;
    @NotEmpty(message = "company name should not be empty")
    @Size(min = 7,message = "company name requires at least 7 characters")
    private String companyName;
}
