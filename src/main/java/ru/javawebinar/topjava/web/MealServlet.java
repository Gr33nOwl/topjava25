package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import static ru.javawebinar.topjava.util.MealsUtil.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        request.setAttribute("meals", mealsTo);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }

}
