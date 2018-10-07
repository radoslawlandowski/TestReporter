package api.controller;

import api.model.models.TestGroup;
import api.repository.TestGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
public class TestGroupController {

    @Autowired
    TestGroupRepository testGroupRepository;

    @RequestMapping(value = "/test-groups/{testGroupName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity byName(@PathVariable("testGroupName") String testGroupName) {
        return ResponseEntity.status(HttpStatus.OK).body(testGroupRepository.findByName(testGroupName));
    }

    @RequestMapping(value = "/test-groups",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity all() {
        return ResponseEntity.status(HttpStatus.OK).body(testGroupRepository.findAll());
    }

    @RequestMapping(value = "/test-groups",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody TestGroup testGroup) throws IOException {
        return ResponseEntity.ok().body(testGroupRepository.save(testGroup));
    }
}
