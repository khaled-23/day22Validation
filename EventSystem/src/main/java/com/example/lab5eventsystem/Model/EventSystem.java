package com.example.lab5eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventSystem {
    @NotEmpty(message = "ID should not be empty")
    @Size(min = 3,message = "minimum id of 3 characters")
    private String ID;
    @NotEmpty(message = "description should not be empty")
    @Size(min = 16, message = "description should be at least 16 characters")
    private String description;
    @NotNull(message = "age should not be  empty")
    @Min(value = 26,message = "capacity should be at least 26")
    private Integer capacity;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
