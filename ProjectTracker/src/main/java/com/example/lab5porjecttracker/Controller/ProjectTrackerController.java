package com.example.lab5porjecttracker.Controller;

import com.example.lab5porjecttracker.ApiResponse.ApiResponse;
import com.example.lab5porjecttracker.Model.ProjectTracker;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/project-tracker")
public class ProjectTrackerController {
    ArrayList<ProjectTracker> projects = new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid ProjectTracker project, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body("project added");
    }
    @GetMapping("/projects")
    public ResponseEntity displayAll(){
        if(projects.isEmpty()){
            return ResponseEntity.status(400).body("there is no project");
        }else return ResponseEntity.status(200).body(projects);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity update(@PathVariable String id, @RequestBody @Valid ProjectTracker project, Errors errors){


        for(int i=0; i<projects.size();i++){
            if(projects.get(i).getID().equalsIgnoreCase(id)){
                projects.set(i,project);
                return ResponseEntity.status(200).body("project updated");
            }
        }
        return ResponseEntity.status(400).body("no project by that id");
    }
    @DeleteMapping("/{id}/remove")
    public ResponseEntity remove(@PathVariable String id){

        for(int i=0; i<projects.size(); i++){
            if(projects.get(i).getID().equals(id)){
                projects.remove(i);
                return ResponseEntity.status(200).body("project removed");
            }
        }
        return ResponseEntity.status(400).body("project doesn't exists");
    }
    @PutMapping("/{id}/change-status")
    public ResponseEntity changeStatus(@PathVariable String id){

            for(ProjectTracker p:projects){
                if(p.getID().equalsIgnoreCase(id)){
                    if(p.getStatus().equals("Complete")){
                        return ResponseEntity.status(200).body("status already complete");
                    }
                }
            }
        return ResponseEntity.status(200).body("project doesn't exists");
    }
    @GetMapping("/{title}/search")
    public ResponseEntity searchProject(@PathVariable String title){
        for(ProjectTracker p:projects){
            if(p.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(p);
            }
        }
        return ResponseEntity.status(400).body("project doesn't exists");
    }
    @GetMapping("/{company}/search-by-company")
    public ResponseEntity searchByCompany(@PathVariable String company){
        ArrayList<ProjectTracker> projectsByCompany = new ArrayList<>();
        for(ProjectTracker p:projects){
            if(p.getCompanyName().equalsIgnoreCase(company)){
                projectsByCompany.add(p);
            }
        }
        if(projectsByCompany.isEmpty()){
            return ResponseEntity.status(400).body("project doesn't exists");
        }else return ResponseEntity.status(200).body(projectsByCompany);
    }

}
