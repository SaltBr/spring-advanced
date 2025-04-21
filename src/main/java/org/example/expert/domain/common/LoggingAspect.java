package org.example.expert.domain.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* org.example.expert.domain.comment.controller.CommentAdminController.deleteComment(long)) || execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(long, org.example.expert.domain.user.dto.request.UserRoleChangeRequest))")
    private void adminMethod(){
    }

    @Around("adminMethod()")
    public Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpResponse =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        logger.trace("정보?");
        Object result = proceedingJoinPoint.proceed();

        return result;
    }
}