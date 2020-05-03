package com.project.JavaEE.controllers;

import com.project.JavaEE.dto.CommentDto;
import com.project.JavaEE.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class CommentController {

}
