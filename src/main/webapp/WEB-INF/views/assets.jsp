<%-- 
    Document   : assets
    Created on : Aug 7, 2025, 7:32:47 AM
    Author     : ifeoluwa
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Fixed Assets Management System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            background: rgba(255, 255, 255, 0.95);
            padding: 30px;
            border-radius: 15px;
            margin-bottom: 30px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            text-align: center;
        }

        .header h1 {
            color: #2c3e50;
            margin-bottom: 10px;
            font-size: 2.5em;
            font-weight: 700;
        }

        .header .subtitle {
            color: #7f8c8d;
            font-size: 1.2em;
            margin-bottom: 15px;
        }

        .header .location {
            color: #e74c3c;
            font-weight: 600;
            font-size: 1.1em;
        }

        .stats-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .stat-card {
            background: rgba(255, 255, 255, 0.95);
            padding: 25px;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            text-align: center;
            transition: transform 0.3s ease;
        }

        .stat-card:hover {
            transform: translateY(-5px);
        }

        .stat-card h3 {
            color: #2c3e50;
            margin-bottom: 10px;
            font-size: 1.1em;
        }

        .stat-card .value {
            color: #27ae60;
            font-size: 1.8em;
            font-weight: bold;
        }

        .filter-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 20px;
            border-radius: 15px;
            margin-bottom: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
        }

        .filter-form {
            display: flex;
            align-items: center;
            gap: 15px;
            flex-wrap: wrap;
        }

        .filter-form label {
            font-weight: 600;
            color: #2c3e50;
        }

        .filter-form select {
            padding: 10px 15px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            background: white;
            cursor: pointer;
            transition: border-color 0.3s ease;
        }

        .filter-form select:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .table-container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 0;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            overflow: hidden;
        }

        .table-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            text-align: center;
        }

        .table-header h2 {
            font-size: 1.5em;
            font-weight: 600;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        th {
            background: #f8f9fa;
            font-weight: 600;
            color: #2c3e50;
            position: sticky;
            top: 0;
        }

        tr:hover {
            background-color: #f1f3f4;
        }

        .status-open {
            background: #d4edda;
            color: #155724;
            padding: 4px 8px;
            border-radius: 4px;
            font-weight: 600;
            font-size: 0.85em;
        }

        .status-closed {
            background: #f8d7da;
            color: #721c24;
            padding: 4px 8px;
            border-radius: 4px;
            font-weight: 600;
            font-size: 0.85em;
        }

        .amount {
            font-weight: 600;
            color: #27ae60;
        }

        .category-badge {
            background: #e9ecef;
            color: #495057;
            padding: 4px 8px;
            border-radius: 4px;
            font-weight: 600;
            font-size: 0.8em;
        }

        .no-data {
            text-align: center;
            padding: 40px;
            color: #6c757d;
            font-style: italic;
        }

        .footer {
            text-align: center;
            padding: 20px;
            margin-top: 30px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            color: white;
            backdrop-filter: blur(10px);
        }

        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
            
            .header h1 {
                font-size: 2em;
            }
            
            .filter-form {
                flex-direction: column;
                align-items: stretch;
            }
            
            table {
                font-size: 0.9em;
            }
            
            th, td {
                padding: 8px 10px;
            }
        }

        .table-wrapper {
            max-height: 70vh;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Header Section -->
        <div class="header">
            <h1>Bank Fixed Assets Management System</h1>
            <div class="subtitle">Comprehensive Asset Tracking & Management</div>
            <div class="location">📍 Abuja, FCT, Nigeria</div>
        </div>

        <!-- Statistics Cards -->
        <div class="stats-container">
            <div class="stat-card">
                <h3>Total Assets</h3>
                <div class="value">${assets.size()}</div>
            </div>
            <div class="stat-card">
                <h3>Total Value</h3>
                <div class="value">
                    ₦<fmt:formatNumber value="${totalValue}" type="number" pattern="#,##0.00"/>
                </div>
            </div>
            <div class="stat-card">
                <h3>Total Branches</h3>
                <div class="value">${branches.size()}</div>
            </div>
        </div>

        <!-- Filter Section -->
        <div class="filter-container">
            <form class="filter-form" action="assets" method="GET">
                <label for="branch">Filter by Branch:</label>
                <select name="branch" id="branch">
                    <option value="all" ${selectedBranch == null || selectedBranch == 'all' ? 'selected' : ''}>All Branches</option>
                    <c:forEach var="branchName" items="${branches}">
                        <option value="${branchName}" ${selectedBranch == branchName ? 'selected' : ''}>${branchName}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn">Filter Assets</button>
            </form>
        </div>

        <!-- Assets Table -->
        <div class="table-container">
            <div class="table-header">
                <h2>Fixed Assets Inventory</h2>
            </div>
            
            <c:choose>
                <c:when test="${not empty assets}">
                    <div class="table-wrapper">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Category</th>
                                    <th>Asset Name</th>
                                    <th>Amount (₦)</th>
                                    <th>Duration (Months)</th>
                                    <th>Purchase Date</th>
                                    <th>Branch</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="asset" items="${assets}">
                                    <tr>
                                        <td>${asset.id}</td>
                                        <td><span class="category-badge">${asset.categoryId}</span></td>
                                        <td>${asset.assetsName}</td>
                                        <td class="amount">
                                            <fmt:formatNumber value="${asset.assetsAmount}" type="number" pattern="#,##0.00"/>
                                        </td>
                                        <td>${asset.durationMonths}</td>
                                        <td>
                                            <fmt:formatDate value="${asset.purchasedDate}" pattern="dd/MM/yyyy"/>
                                        </td>
                                        <td>${asset.branch}</td>
                                        <td>
                                            <span class="status-${asset.status.toLowerCase()}">${asset.status}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="no-data">
                        <h3>No assets found</h3>
                        <p>No assets match the selected criteria.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>&copy; 2024 Bank Fixed Assets Management System | Developed by [Your Name] | Abuja, Nigeria</p>
        </div>
    </div>
</body>
</html>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="Hello World!"/></h1>
        </body>
    </html>
</f:view>--%>
