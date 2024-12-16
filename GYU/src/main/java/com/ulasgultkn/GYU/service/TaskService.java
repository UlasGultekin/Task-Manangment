package com.ulasgultkn.GYU.service;

import com.ulasgultkn.GYU.entity.Task;
import com.ulasgultkn.GYU.entity.User;
import com.ulasgultkn.GYU.repository.TaskRepository;
import com.ulasgultkn.GYU.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> createTask(Task task){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            taskRepository.save(task);
            hashMap.put("status",true);
            hashMap.put("message","Created new Task");
            hashMap.put("result",task);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        }catch (Exception ex){
            hashMap.put("status",false);
            hashMap.put("error","Not Created new Task");

            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }


    }
    public ResponseEntity<?> deleteTask(Long id){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            taskRepository.deleteById(id);
            hashMap.put("status",true);
            hashMap.put("message","Deleted Ok");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);



        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not delete");
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllTasks(){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            List<Task> taskList = taskRepository.findAll();
            hashMap.put("status",true);
            hashMap.put("result",taskList);

            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not found Task or Exception for Task");

            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> updateTask (Long id,Task taskDetails){
        HashMap<String,Object> hashMap = new HashMap<>();
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
        task.setTitle(taskDetails.getTitle());
        task.setStatus(taskDetails.getStatus());
        task.setDescription(task.getDescription());
        try {
            taskRepository.save(task);
            hashMap.put("status",true);
            hashMap.put("message","Updated task");
            hashMap.put("result",task);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not Updated task");
            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }
    }
    public ResponseEntity<?> assignTaskToUser(Long taskId,Long userId){
        Task task =taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        HashMap<String,Object> hashMap = new HashMap();
        try {
            taskRepository.save(task);
            hashMap.put("status",true);
            hashMap.put("message","Assign to User Okey");
            hashMap.put("result",task);

            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }catch (Exception e){
            hashMap.put("status",false);
            hashMap.put("error","Not Assign to User ");


            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }
    public  ResponseEntity<?> getTaskByUser(Long userId){
        HashMap<String,Object> hashMap = new HashMap<>();
        try {
            List<Task> taskList = taskRepository.findByUserId(userId);
            hashMap.put("status",true);
            hashMap.put("result",taskList);
            return new ResponseEntity<>(hashMap,HttpStatus.OK);

        }catch (Exception ex){
            hashMap.put("status",false);
            hashMap.put("error","Not User or Task");
            return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
        }
    }
}
