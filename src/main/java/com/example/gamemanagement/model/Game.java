package com.example.gamemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createDate;
    private String active;
}
