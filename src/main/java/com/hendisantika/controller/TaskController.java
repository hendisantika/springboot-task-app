package com.hendisantika.controller;

import com.hendisantika.model.Status;
import com.hendisantika.model.Task;
import com.hendisantika.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-task-app
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/09/21
 * Time: 07.52
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * GET all tasks from Database
     *
     * @return template view for all tasks
     */
    @GetMapping(value = {"/tasks", "/"})
    public String dashboard(Model model) {
        //display all Tasks
        Set<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        //newTask Form
        Task newTask = new Task();
        model.addAttribute("newTask", newTask);

        Set<Status> statusList = new HashSet<>();
        Status.stream().forEach(statusList::add);
        model.addAttribute("statusList", statusList);

        return "index";
    }

    /**
     * Shows Tasks by Status
     *
     * @param model      contains TaskObject
     * @param taskStatus may have the values "open/closed/reopened"
     * @return Set of Tasks with specific status
     */
    @GetMapping(value = "/{status}")
    public String displayByStatus(Model model, @PathVariable("status") String taskStatus) {

        if (taskStatus.equals(Status.OPEN.getTypeOfStatus())) {
            model.addAttribute("tasks", taskService.getTasksByStatus(Status.OPEN));
        } else if (taskStatus.equals(Status.CLOSED.getTypeOfStatus())) {
            model.addAttribute("tasks", taskService.getTasksByStatus(Status.CLOSED));
        } else if (taskStatus.equals(Status.REOPENED.getTypeOfStatus())) {
            model.addAttribute("tasks", taskService.getTasksByStatus(Status.REOPENED));
        }

        //for newTask Form
        Task newTask = new Task();
        model.addAttribute("newTask", newTask);

        Set<Status> statusList = new HashSet<>();
        Status.stream().forEach(statusList::add);
        model.addAttribute("statusList", statusList);

        return "index";
    }

    /**
     * handles Status Changes
     *
     * @param taskId  Task Id
     * @param action  may contain "close/open/reopen"
     * @param request helps redirect to previous site
     * @return redirection
     */
    @GetMapping(value = "/task/{id}/{action}")
    public String handleStatus(@PathVariable("id") Long taskId,
                               @PathVariable("action") String action,
                               HttpServletRequest request) {
        Task task = taskService.findById(taskId);

        if (action.equals("close")) {
            if (task.getStatus() == Status.OPEN) {
                taskService.closeTask(taskId);
            }
            if (task.getStatus() == Status.REOPENED) {
                taskService.closeTask(taskId);
            }
        }
        if (action.equals("reopen") && task.getStatus() == Status.CLOSED) {
            taskService.reopenTask(taskId);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    /**
     * Save NEW Task in Database
     *
     * @param taskDetails field values
     * @return redirect to Dashboard
     */
    @PostMapping(path = "/task/create")
    public String createTask(Task taskDetails) {
        Task newTask = taskService.createTask(taskDetails);
        return "redirect:/";
    }

}
