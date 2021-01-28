package com.example.list_Of_Customers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/home")
public class ServletDSKH extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Customer> arrayList=new ArrayList<>();
        Customer customer = new Customer("Mai Van Hoang", "19/5/1998","Hue","images.jfif");
        Customer customer1 = new Customer("Nguyen Van A", "20/5/1995","Ha Noi","1.jpg");
        Customer customer2 = new Customer("Nguyen Van B", "19/9/2000","Vung Tau","2.jpg");
        Customer customer3 = new Customer("Nguyen Van C", "01/5/1997","Binh Dinh","3.jpg");
        Customer customer4 = new Customer("Mai Van Dung", "19/12/1998","Hue","1.jpg");
        Customer customer5 = new Customer("Mai Do Huong", "19/1/2001","Hue","2.jpg");
        arrayList.add(customer);
        arrayList.add(customer1);
        arrayList.add(customer2);
        arrayList.add(customer3);
        arrayList.add(customer4);
        arrayList.add(customer5);
        request.setAttribute("arrayList", arrayList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
