package com.chat.exception;

import com.chat.tools.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = Logger.getLogger(DefaultExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            httpServletResponse.getWriter().write(ResultUtil.error(1, e.getMessage()).toString());
        } catch (IOException ex) {
            logger.error("JSON error: " + ex.getMessage(), ex);
        }
        logger.info("error: " + e.getMessage());
        return mv;
    }
}
