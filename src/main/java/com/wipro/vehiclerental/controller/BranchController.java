package com.wipro.vehiclerental.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vehiclerental.entity.Branch;
import com.wipro.vehiclerental.service.BranchService;

@RestController
@RequestMapping("/api/v1/branches")
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // Get all branches
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();//jpa repository method to get all branches
    }

    // Get branch by ID 
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable String id) {
        return branchService.getBranchById(id)
                .orElseThrow(() ->
                        new RuntimeException("Branch not found with id: " + id));   
    }

    // Add branch
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.saveBranch(branch);
    }

    // Update branch
    @PutMapping("/{id}")
    public Branch updateBranch(@PathVariable String id,
                               @RequestBody Branch branch) {

        Branch existingBranch = branchService.getBranchById(id)
                .orElseThrow(() ->
                        new RuntimeException("Branch not found with id: " + id));

        existingBranch.setBranchName(branch.getBranchName());
        existingBranch.setCity(branch.getCity());

        return branchService.saveBranch(existingBranch);
    }

    // Delete branch
    @DeleteMapping("/{id}")
    public String deleteBranch(@PathVariable String id) {
        branchService.deleteBranch(id);
        return "Branch deleted successfully";
    }

    // Find branches by city
    @GetMapping("/city/{city}")
    public List<Branch> getBranchesByCity(@PathVariable String city) {
        return branchService.getBranchesByCity(city);
    }

    // Find branches by name
    @GetMapping("/name/{name}")
    public List<Branch> getBranchesByName(@PathVariable String name) {
        return branchService.getBranchesByName("%" + name + "%");
    }

    // Sort branches by name
    @GetMapping("/sort")
    public List<Branch> getBranchesOrderByName() {
        return branchService.getBranchesOrderByName();
    }

    // Count branches by city
    @GetMapping("/count/city")
    public List<Object[]> countBranchesByCity() {
        return branchService.countBranchesByCity();
    }

    // Count total branches
    @GetMapping("/count")
    public Long countBranches() {
        return branchService.countBranches();
    }
}