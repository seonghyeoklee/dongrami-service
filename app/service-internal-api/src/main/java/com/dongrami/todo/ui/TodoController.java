package com.dongrami.todo.ui;

import com.dongrami.todo.application.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoService todoService;

}
