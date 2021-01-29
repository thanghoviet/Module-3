package com.example.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Calculator-Servlet",urlPatterns = "/calculater")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello");
        double firstOperand = Double.parseDouble(request.getParameter("first-operand"));
        System.out.println(firstOperand);
        double secondOperand = Double.parseDouble(request.getParameter("second-operand"));
        String operator = request.getParameter("operator");
        System.out.println(operator);
        double result ;


        switch (operator) {
            case "addition":
                result = firstOperand + secondOperand;
                break;
            case "subtraction":
                result = firstOperand - secondOperand;
                break;
            case "multiplication":
                result = firstOperand * secondOperand;
                break;
            case "division":
                if (secondOperand!=0) {
                    result = firstOperand / secondOperand;
                    break;
                }else {
                    System.out.println("Phép chia không thể có mẫu số bằng 0");
                    throw new IllegalStateException("Unexpected value: " + operator);
                }
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
        PrintWriter writer = response.getWriter();
        writer.println(firstOperand+"  "+operator+"  "+secondOperand+" = "+result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
