package com.project.JavaEE.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isFullyAuthenticated()")
public class CommentController {

}
