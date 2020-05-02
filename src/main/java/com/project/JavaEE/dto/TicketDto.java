package com.project.JavaEE.dto;

import com.project.JavaEE.entities.CommentEntity;
import com.project.JavaEE.entities.type.Case;
import com.project.JavaEE.entities.type.Priority;
import com.project.JavaEE.entities.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TicketDto {

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Body text cannot be empty")
    private String bodyText;

    @NotEmpty(message = "State cannot be empty")
    private State state;

    @NotEmpty(message = "Priority cannot be empty")
    private Priority priority;

    @NotEmpty(message = "Case cannot be empty")
    private Case casetype;

    @NotEmpty(message = "Creation date cannot be empty")
    private Date creationDate;

    private Date etaDate;

    private Date nextstepDate;

    private String nextstepNote;

    @NotEmpty(message = "Firm cannot be empty")
    private String firm;

    private Set<CommentEntity> comments;
}
