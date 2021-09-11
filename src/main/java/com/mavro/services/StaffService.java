package com.mavro.services;

import com.mavro.dto.StaffDetails;
import com.mavro.entities.Staff;
import com.mavro.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaffMembers() {
        return staffRepository.findAll();
    }

    public void addStaffMember(StaffDetails staffDetails) {
        Staff staffMember = new Staff();

        staffMember.setName(staffDetails.getName());
        staffMember.setRole(staffDetails.getRole());
        staffMember.setAge(staffDetails.getAge());

        staffRepository.save(staffMember);
    }

    public void updateStaffMember(Staff staff) {
         staffRepository.save(staff);
    }

    public void deleteStaffMemberById(int id) {
        staffRepository.deleteById(id);
    }
}
