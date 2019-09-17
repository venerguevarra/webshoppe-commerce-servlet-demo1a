package com.webshoppe.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshoppe.ecommerce.entity.Toy;
import com.webshoppe.ecommerce.jdbc.JdbcConnectionManager;
import com.webshoppe.ecommerce.repository.ToyRepository;
import com.webshoppe.ecommerce.service.ToyCatalogService;

@WebServlet("/toy-catalog")
public class ToyCatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ToyCatalogService toyCatalogService;

    @Override
    public void init() throws ServletException {
        final JdbcConnectionManager jdbcConnectionManager = new JdbcConnectionManager();
        final ToyRepository toyRepository = new ToyRepository(jdbcConnectionManager);
        toyCatalogService = new ToyCatalogService(toyRepository);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        final List<Toy> toys = toyCatalogService.getToyCatalog();
        final StringBuilder stringBuilder = new StringBuilder();
        if (toys.isEmpty()) {
            stringBuilder.append("<b>Toy Catalog Empty</b>");
        } else {
            stringBuilder.append("<table>");
            stringBuilder.append("<th>");
            stringBuilder.append("<td>ID</td>");
            stringBuilder.append("<td>Name</td>");
            stringBuilder.append("<td>Description</td>");
            stringBuilder.append("<td>Price</td>");
            stringBuilder.append("</th>");
            toys.forEach(e -> {
                stringBuilder.append("<th>");
                stringBuilder.append("<td>").append(e.getId()).append("</td>");
                stringBuilder.append("<td>").append(e.getName()).append("</td>");
                stringBuilder.append("<td>").append(e.getDescription()).append("</td>");
                stringBuilder.append("<td>").append(e.getPrice()).append("</td>");
                stringBuilder.append("</th>");
            });
            stringBuilder.append("</table>");
        }

        PrintWriter out = response.getWriter();
        out.println(stringBuilder.toString());
        out.flush();
        out.close();
    }

}
