package io.pivotal.pal.tracker;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/time-entries")
@RestController
public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;

    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

        HttpStatus status;

        TimeEntry timeEntryResp = timeEntryRepository.find(timeEntryId);

        if(timeEntryResp == null || timeEntryResp.getId() == 0){
            status  = HttpStatus.NOT_FOUND;
        }
        else{
            status = HttpStatus.OK;
        }
        return new ResponseEntity(timeEntryResp,status);

    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);



        return new ResponseEntity(timeEntry,HttpStatus.CREATED);

    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry)
    {
        HttpStatus status;
        TimeEntry timeEntryResp = timeEntryRepository.update(timeEntryId,timeEntry);

        if(timeEntryResp == null || timeEntryResp.getId() == 0){
            status  = HttpStatus.NOT_FOUND;
        }
        else{
            status = HttpStatus.OK;
        }

        return new ResponseEntity(timeEntryResp,status);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
            timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(),HttpStatus.OK);
    }
}
