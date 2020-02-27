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

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        // Let Spring handle the error first
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        // Format and update timestamp
        Object timestamp = errorAttributes.get("timestamp");
        if (timestamp == null) {
            errorAttributes.put("timestamp", dateFormat.format(new Date()));
        } else {
            errorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
        }

        // Remove trace
        errorAttributes.remove("trace");

        // Version for the app
        errorAttributes.put("version", "1.0");

        return errorAttributes;
    }
}
