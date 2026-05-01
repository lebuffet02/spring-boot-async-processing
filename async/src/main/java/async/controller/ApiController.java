package async.controller;

import async.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final TaskService taskService;

    public ApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/sem-paralelismo")
    public ResponseEntity<String> semParalelismoController() {
       return ResponseEntity.ok().body(taskService.semParalelismo());
    }

    @GetMapping("/com-paralelismo")
    public ResponseEntity<String> comParalelismoController() {
        return ResponseEntity.ok().body(taskService.comParalelismo());
    }
}