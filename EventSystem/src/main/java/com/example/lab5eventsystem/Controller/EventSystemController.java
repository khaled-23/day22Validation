package com.example.lab5eventsystem.Controller;

import com.example.lab5eventsystem.ApiResponse.ApiResponse;
import com.example.lab5eventsystem.Model.EventSystem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-tracker")
public class EventSystemController {
    ArrayList<EventSystem> events = new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity createEvent(@RequestBody @Valid EventSystem event, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body("event added");
    }
    @GetMapping("/events")
    public ResponseEntity displayAll(){
        if(events.isEmpty()){
            return ResponseEntity.status(400).body("no available event");
        }else return ResponseEntity.status(200).body(events);
    }


    @GetMapping("/{id}/get")
    public ResponseEntity getEventByID(@PathVariable String id){
        for(EventSystem e:events){
            if(e.getID().equalsIgnoreCase(id)){
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(400).body("no event by that id");
    }
    @PutMapping("/{id}/update")
    public ResponseEntity updateEvent(@PathVariable String id,@RequestBody @Valid EventSystem event, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        for(int i=0; i<events.size();i++){
            if(events.get(i).getID().equalsIgnoreCase(id)){
                events.set(i,event);
                return ResponseEntity.status(200).body("event updated");
            }
        }
        return ResponseEntity.status(400).body("there is no event by that id");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteEvent(@PathVariable String id){
        for(int i = 0; i<events.size(); i++){
            if(events.get(i).getID().equalsIgnoreCase(id)){
                events.remove(i);
                return ResponseEntity.status(200).body("event deleted");
            }
        }
        return ResponseEntity.status(400).body("there is no event by that id");
    }

    @PutMapping("/{id}/{capacity}/change-capacity")
    public ResponseEntity changeCapacity(@PathVariable String id, @PathVariable @Valid @Min(26) Integer capacity){

        for (EventSystem event : events) {
            if (event.getID().equalsIgnoreCase(id)) {
                event.setCapacity(capacity);
                return ResponseEntity.status(200).body("capacity changed");
            }
        }
        return ResponseEntity.status(400).body("there is no event by that id");
    }
}
