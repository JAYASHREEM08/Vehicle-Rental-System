package com.wipro.vehiclerental.service;

import com.wipro.vehiclerental.entity.Branch;
import com.wipro.vehiclerental.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Get all branches
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Get branch by ID
    public Optional<Branch> getBranchById(String id) {
        return branchRepository.findById(id);
    }

    // Add / Update branch
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    // Delete branch
    public void deleteBranch(String id) {
        branchRepository.deleteById(id);
    }

    // Find branches by city
    public List<Branch> getBranchesByCity(String city) {
        return branchRepository.findBranchesByCity(city);
    }

    // Find branches by name
    public List<Branch> getBranchesByName(String name) {
        return branchRepository.findBranchesByName(name);
    }

    // Sort branches by name
    public List<Branch> getBranchesOrderByName() {
        return branchRepository.findBranchesOrderByName();
    }

    // Count branches in each city
    public List<Object[]> countBranchesByCity() {
        return branchRepository.countBranchesByCity();
    }

    // Count total branches
    public Long countBranches() {
        return branchRepository.countBranches();
    }
}