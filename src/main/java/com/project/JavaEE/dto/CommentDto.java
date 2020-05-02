package com.project.JavaEE.dto;

import com.project.JavaEE.entities.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentDto {

    @NotEmpty(message = "Parent ticket cannot be empty")
    private TicketEntity ticket;

    @NotEmpty(message = "Body text cannot be empty")
    private String bodyText;

    @NotEmpty(message = "Creation date cannot be empty")
    private Date creationDate;
}
