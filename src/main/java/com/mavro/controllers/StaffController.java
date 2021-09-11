package com.mavro.controllers;

import com.mavro.dto.StaffDetails;
import com.mavro.entities.Staff;
import com.mavro.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Staff>> getAllStaffMembers() {

        return ResponseEntity.status(HttpStatus.OK).body(staffService.getAllStaffMembers());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStaffMember(@RequestBody StaffDetails staffDetails) {
        staffService.addStaffMember(staffDetails);
        return new ResponseEntity<>("Staff member created",HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStaffMember(@RequestBody Staff staff) {
        staffService.updateStaffMember(staff);
        return new ResponseEntity<>("Staff member updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<String> deleteStaffMemberById(@PathVariable("staffId") int staffId) {
        staffService.deleteStaffMemberById(staffId);
        return new ResponseEntity<>("Staff member deleted", HttpStatus.OK);
    }
}
