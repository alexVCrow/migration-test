package ua.alexcrow.flyway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class LoggingServiceImpl implements LoggingService {

    private final ObjectMapper objectMapper;

    public LoggingServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        if (body != null) {
            try {
                stringBuilder.append("body=[").append(objectMapper.writeValueAsString(body)).append("]");
            } catch (JsonProcessingException e) {
                log.error("LOG REQUEST ERROR {}", stringBuilder.toString(), e);
            }
        }
        log.info(stringBuilder.toString());
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        String stringBuilder = null;
        try {
            stringBuilder = "RESPONSE " +
                    "method=[" + httpServletRequest.getMethod() + "] " +
                    "path=[" + httpServletRequest.getRequestURI() + "] " +
                    "responseHeaders=[" + buildHeadersMap(httpServletResponse) + "] " +
                    "responseBody=[" + objectMapper.writeValueAsString(body) + "] ";
        } catch (JsonProcessingException e) {
            log.error("LOG RESPONSE ERROR {}", httpServletRequest.getRequestURI(), e);
        }
        log.info(stringBuilder);
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        return map;
    }

}
