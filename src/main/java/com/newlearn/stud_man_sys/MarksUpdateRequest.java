package com.newlearn.stud_man_sys;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarksUpdateRequest {

    @NotNull(message = "Marks required")
    @Min(value=0,message = "Marks cannot be negative")
    @Max(value=100,message = "Marks cannot be more than 100")
    private Double marks;
}
