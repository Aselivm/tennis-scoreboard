package org.primshic.stepan.controller;

import org.primshic.stepan.service.FinishedMatchesPersistenceService;
import org.primshic.stepan.service.PlayersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    protected final PlayersService playersService = new PlayersService();
    protected String pathToViews = "WEB-INF/views/";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
