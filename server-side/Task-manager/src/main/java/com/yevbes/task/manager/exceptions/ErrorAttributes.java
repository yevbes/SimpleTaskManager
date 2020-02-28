package com.yevbes.task.manager.exceptions;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        // Let Spring handle the error first
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        // Remove trace
        errorAttributes.remove("trace");

        // Version for the app
        errorAttributes.put("version", "1.0");

        return errorAttributes;
    }
}
