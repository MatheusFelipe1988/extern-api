package com.api.extern.service.error;

import com.api.extern.business.configuration.FakeMailService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class NotificationErrorAspect {
    private final FakeMailService mailService;

    @Pointcut("@within(com.api.extern.service.error.NotificationError) || @annotation(com.api.extern.service.error.NotificationError)")
    public void NotificationErroPointcut() {}

    @AfterThrowing(pointcut = "NotificationErroPointcut()", throwing = "e")
    public void notificationError(final Exception e){
        mailService.postMailException(e);
    }
}
