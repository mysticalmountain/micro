package com.andx.micro.support.web.dispatch;

import com.alibaba.fastjson.JSON;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.dto.Response;
import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.execute.ExecuteTemplate;
import com.andx.micro.api.core.module.service.*;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import com.andx.micro.core.log.slf4j.Slf4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-3-31.
 */
@Component
public class GenericRequestExecute implements RequestExecute {

    private Log log = Slf4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired(required = false)
    private List<ComplexService> complexServices;
    @Autowired(required = false)
    private List<SampleService> sampleServices;

    @Autowired(required = false)
    @Qualifier("getExecuteSample")
    private ExecuteTemplate getExecuteSample;
    @Autowired(required = false)
    @Qualifier("getExecuteComplex")
    private ExecuteTemplate getExecuteComplex;

    @Autowired(required = false)
    @Qualifier("deleteExecuteSample")
    private ExecuteTemplate deleteExecuteSample;
    @Autowired(required = false)
    @Qualifier("deleteExecuteComplex")
    private ExecuteTemplate deleteExecuteComplex;

    @Autowired(required = false)
    @Qualifier("postExecuteSample")
    private ExecuteTemplate postExecuteSample;
    @Autowired(required = false)
    @Qualifier("postExecuteComplex")
    private ExecuteTemplate postExecuteComplex;

    @Autowired(required = false)
    @Qualifier("patchExecuteSample")
    private ExecuteTemplate patchExecuteSample;
    @Autowired(required = false)
    @Qualifier("patchExecuteComplex")
    private ExecuteTemplate patchExecuteComplex;

    @Override
    public String execute(Object... args) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) args[0];
        String method = (String) args[1];
        String path = (String) args[2];
        for (final ComplexService complexService : complexServices) {
            Service service = getService(complexService);
            if (this.isMatch(service, path) && method.equals(service.method())) {
                if ("GET".equals(service.method())) {
                    Map<String, String[]> params = (Map<String, String[]>) args[3];
                    Response response = (Response) getExecuteComplex.execute(params, complexService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("POST".equals(service.method())) {
                    String requestData = (String) args[3];
                    Request request = this.json2Request(requestData, complexService);
                    Response response = (Response) postExecuteComplex.execute(request, complexService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("DELETE".equals(service.method())) {
                    Response response = (Response) deleteExecuteComplex.execute(null, complexService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("PUT".equals(service.method()) || "PATCH".equals(service.method())) {
                    String requestData = (String) args[3];
                    Request request = this.json2Request(requestData, complexService);
                    Response response = (Response) patchExecuteComplex.execute(request, complexService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                }
            }
        }

        for (final SampleService sampleService : sampleServices) {
            Service service = getService(sampleService);
            if (isMatch(service, path) && method.equals(service.method())) {
                if ("GET".equals(service.method())) {
                    Map<String, String[]> params = (Map<String, String[]>) args[3];
                    params.put("path", new String[]{path});
                    Response response = (Response) getExecuteSample.execute(params, sampleService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("POST".equals(service.method())) {
                    String requestData = (String) args[3];
                    Request request = json2Request(requestData, sampleService);
                    Response response = (Response) postExecuteSample.execute(request, sampleService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("DELETE".equals(service.method())) {
                    Response response = (Response) deleteExecuteSample.execute(null, sampleService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                } else if ("PUT".equals(service.method()) || "PATCH".equals(service.method())) {
                    String requestData = (String) args[3];
                    Request request = json2Request(requestData, sampleService);
                    Response response = (Response) patchExecuteSample.execute(request, sampleService, httpServletRequest, path);
                    return JSON.toJSONString(response);
                }
            }
        }
        throw new DispatchException("未找到资源");
    }

    private Request json2Request(String json, Object obj) throws Exception {
        Object target = AopTargetUtils.getTarget(obj);
        Optional<Type> superType = Optional.of(target.getClass().getGenericSuperclass()); //父类为class
        if (superType == null) { // 父类为interface
            Type[] types = target.getClass().getSuperclass().getGenericInterfaces();
            superType = Arrays.asList(types).stream().filter(p -> p.getTypeName().startsWith(SampleService.class.getName())).findFirst();
            if (superType == null) {
                superType = Arrays.asList(types).stream().filter(p -> p.getTypeName().startsWith(ComplexService.class.getName())).findFirst();
            }
        }
        Type type = ((ParameterizedType) superType.get()).getActualTypeArguments()[0];
        String sourceDate = null; //URLDecoder.decode(json, "utf-8");
        if (json.startsWith("%")) {
            sourceDate = URLDecoder.decode(json, "utf-8");
        } else {
            sourceDate = json;
        }
        if (sourceDate.charAt(sourceDate.length() - 1) == '=') {
            sourceDate = sourceDate.substring(0, sourceDate.length() - 1);
        }
        Request request = JSON.parseObject(sourceDate, type);
        return request;
    }

    private Boolean isMatch(Service service, String path) {
        if (service.path() == null || service.path().equals("")) {
            return false;
        }
        String regex = "^/service" + service.path() + "$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        return matcher.find();
    }

    private Service getService(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Service service = obj.getClass().getAnnotation(Service.class);
        if (service == null) {
            Method getTargetSource = obj.getClass().getMethod("getTargetSource");
            TargetSource ts = (TargetSource) getTargetSource.invoke(obj);
            Class targetClass = ts.getTargetClass();
            service = (Service) targetClass.getAnnotation(Service.class);
        }
        return service;
    }

}
