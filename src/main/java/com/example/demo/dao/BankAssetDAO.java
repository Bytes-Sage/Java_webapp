package com.example.demo.dao;

import com.example.demo.model.BankAsset;
import com.example.demo.utils.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAssetDAO {
    
    public List<BankAsset> getAllAssets() {
        List<BankAsset> assets = new ArrayList<>();
        String query = "SELECT id, category_id, assets_name, assets_amount, duration_months, " +
                      "purchased_date, branch, last_dep_date, status FROM bank_fixed_assets " +
                      "ORDER BY purchased_date DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BankAsset asset = new BankAsset();
                asset.setId(rs.getInt("id"));
                asset.setCategoryId(rs.getString("category_id"));
                asset.setAssetsName(rs.getString("assets_name"));
                asset.setAssetsAmount(rs.getBigDecimal("assets_amount"));
                asset.setDurationMonths(rs.getInt("duration_months"));
                asset.setPurchasedDate(rs.getDate("purchased_date"));
                asset.setBranch(rs.getString("branch"));
                asset.setLastDepDate(rs.getDate("last_dep_date"));
                asset.setStatus(rs.getString("status"));
                
                assets.add(asset);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching assets: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return assets;
    }
    
    public List<BankAsset> getAssetsByBranch(String branch) {
        List<BankAsset> assets = new ArrayList<>();
        String query = "SELECT id, category_id, assets_name, assets_amount, duration_months, " +
                      "purchased_date, branch, last_dep_date, status FROM bank_fixed_assets " +
                      "WHERE branch = ? ORDER BY purchased_date DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, branch);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BankAsset asset = new BankAsset();
                asset.setId(rs.getInt("id"));
                asset.setCategoryId(rs.getString("category_id"));
                asset.setAssetsName(rs.getString("assets_name"));
                asset.setAssetsAmount(rs.getBigDecimal("assets_amount"));
                asset.setDurationMonths(rs.getInt("duration_months"));
                asset.setPurchasedDate(rs.getDate("purchased_date"));
                asset.setBranch(rs.getString("branch"));
                asset.setLastDepDate(rs.getDate("last_dep_date"));
                asset.setStatus(rs.getString("status"));
                
                assets.add(asset);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching assets by branch: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return assets;
    }
    
    public List<String> getAllBranches() {
        List<String> branches = new ArrayList<>();
        String query = "SELECT DISTINCT branch FROM bank_fixed_assets ORDER BY branch";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                branches.add(rs.getString("branch"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching branches: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return branches;
    }
    
    public BigDecimal getTotalAssetsValue() {
        BigDecimal total = BigDecimal.ZERO;
        String query = "SELECT SUM(assets_amount) as total FROM bank_fixed_assets";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                total = rs.getBigDecimal("total");
                if (total == null) {
                    total = BigDecimal.ZERO;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total assets value: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return total;
    }
    
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        DatabaseConnection.closeConnection(conn);
    }
}