package com.janitha.todo.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private Long id;

    @Size(max = 2048)
    private String text;

    @NotNull
    private Boolean done;
}
