package com.example.demo.servlet;

import com.example.demo.dao.BankAssetDAO;
import com.example.demo.model.BankAsset;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/assets")
public class AssetServlet extends HttpServlet {
    
    private BankAssetDAO assetDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        assetDAO = new BankAssetDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String branch = request.getParameter("branch");
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
        
        // Set attributes for JSP
        request.setAttribute("assets", assets);
        request.setAttribute("branches", branches);
        request.setAttribute("totalValue", totalValue);
        request.setAttribute("selectedBranch", branch);
        
        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/assets.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}