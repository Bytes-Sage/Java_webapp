package com.example.demo.controller;

import com.example.demo.dao.BankAssetDAO;
import com.example.demo.model.BankAsset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AssetController {
    
    private final BankAssetDAO assetDAO;
    
    public AssetController() {
        this.assetDAO = new BankAssetDAO();
    }
    
    @GetMapping("/")
    public String redirectToAssets() {
        return "redirect:/assets";
    }
    
    @GetMapping("/assets")
    public String getAssets(@RequestParam(value = "branch", required = false) String branch, Model model) {
        
        List<BankAsset> assets;
        
        if (branch != null && !branch.trim().isEmpty() && !"all".equals(branch)) {
            assets = assetDAO.getAssetsByBranch(branch);
        } else {
            assets = assetDAO.getAllAssets();
        }
        
        // Get all branches for the filter dropdown
        List<String> branches = assetDAO.getAllBranches();
        
        // Calculate total assets value
        BigDecimal totalValue = assetDAO.getTotalAssetsValue();
        
        // Add attributes to model
        model.addAttribute("assets", assets);
        model.addAttribute("branches", branches);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("selectedBranch", branch);
        
        return "assets"; // This will look for assets.html in templates folder
    }
    
    @GetMapping("/test")
    public String testDatabase(Model model) {
        try {
            List<BankAsset> assets = assetDAO.getAllAssets();
            model.addAttribute("message", "✅ Database connection successful!");
            model.addAttribute("count", assets.size());
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("message", "❌ Database connection failed: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "test";
    }
}