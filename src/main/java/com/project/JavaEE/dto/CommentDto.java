package com.project.JavaEE.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentDto {

    private Integer id;

    @NotNull
    private Integer ticketId;

    @NotEmpty(message = "Author cannot be empty")
    private String authorUsername;

    @NotEmpty(message = "Body text cannot be empty")
    private String bodyText;
}
