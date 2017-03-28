package com.andx.micro.support.web.dispatch;

import com.alibaba.fastjson.JSON;
import com.andx.micro.api.core.Service;
import com.andx.micro.api.core.dto.Request;
import com.andx.micro.api.core.exception.ExceptionType;
import com.andx.micro.api.core.module.service.ServiceProcessor;
import com.andx.micro.api.log.Log;
import com.andx.micro.core.log.log4j.Log4jLogFactory;
import com.andx.micro.core.util.AopTargetUtils;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by andongxu on 9/11/16.
 */
@Component
public class ServiceDispatcher {

    private Log log = Log4jLogFactory.getLogFactory().getLog(this.getClass());

    @Autowired
    private ServiceVisitor serviceVisitor;
    @Autowired(required = false)
    private List<ServiceProcessor> services;

    public String execute(String serviceCode, final String requestData) throws Exception {
        log.info("request data --->" + requestData);
//        final String userId = (String) request.getSession().getAttribute("userId");
        for (final ServiceProcessor sampleService : services) {
            Service service = (Service) sampleService.getClass().getAnnotation(Service.class);
            if (service == null) {
                Method getTargetSource = sampleService.getClass().getMethod("getTargetSource");
                TargetSource ts = (TargetSource) getTargetSource.invoke(sampleService);
                Class targetClass = ts.getTargetClass();
                service = (Service) targetClass.getAnnotation(Service.class);
            }
            if (serviceCode.equals(service.code())) {
                Object result = serviceVisitor.visit(sampleService, new ServiceData() {
                    public Object get() throws DispatchException {
                        try {
                            Object target = AopTargetUtils.getTarget(sampleService);
                            Type[] types = target.getClass().getGenericInterfaces();
//                            Type[] types = sampleService.getClass().getGenericInterfaces();
                            Optional<Type> superType = Arrays.asList(types).stream().filter(p -> p.getTypeName().startsWith("com.andx.micro.api.core.module.service.ServiceProcessor")).findFirst();
                            Type type = ((ParameterizedType) superType.get()).getActualTypeArguments()[0];
                            String sourceDate = URLDecoder.decode(requestData, "utf-8");
                            Request request = JSON.parseObject(sourceDate, type);
                            request.setServiceId(serviceCode);
                            return request;
//                            return JSON.parseObject(sourceDate, type);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                            throw new DispatchException(ExceptionType.UNKNOWN, "数据格式化错误", e);
                        }
                    }
                });
                String responseData = JSON.toJSONString(result);
                log.info("response data --->" + responseData);
                return responseData;
            }
        }
        throw new DispatchException("数据格式化错误");
    }

    private Method getMethod(Class c, String methodName) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.getModifiers() == 1) {
                    return method;
                }
            }
        }
        return null;
    }
}
