package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;

import static ru.javawebinar.topjava.util.MealsUtil.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private MealRepository repository;

    public MealServlet() {
        super();
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        action = action == null ? "all" : action;
        switch (action.toLowerCase()) {
            case "delete":
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                log.debug("Delete meal");
                repository.delete(mealId);
                response.sendRedirect("meals");
                break;
            case "create":
                Meal newMeal = new Meal(null, null, 0);
                log.debug("Create new meal");
                request.setAttribute("meal", newMeal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                return;
            case "edit":
                mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = repository.getById(mealId);
                log.debug("edit meal");
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                return;
            case "all":
            default:
                List<MealTo> mealsTo = filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                log.debug("Show meal list");
                request.setAttribute("meals", mealsTo);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);
        if (mealId == 0) {
            repository.create(meal);
        } else {
            meal.setId(mealId);
            repository.update(meal);
        }
        response.sendRedirect("meals");
    }
}
