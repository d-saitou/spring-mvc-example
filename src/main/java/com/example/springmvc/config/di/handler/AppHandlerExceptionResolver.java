package com.example.springmvc.config.di.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.springmvc.utility.CommonUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * Handler to handle application exceptions.
 */
@Component
@Slf4j
public class AppHandlerExceptionResolver implements HandlerExceptionResolver {

	/**
	 * Exception handler.
	 * @param req     HttpServletRequest object.
	 * @param res     HttpServletResponse object.
	 * @param handler object.
	 * @param e       Exception object.
	 * @return ModelAndView
	 */
	public ModelAndView resolveException(
			HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) {
		log.error("Exception occurred.", e);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		mav.addObject("stacktrace", CommonUtility.getStackTraceString(e));
		return mav;
	}

}
