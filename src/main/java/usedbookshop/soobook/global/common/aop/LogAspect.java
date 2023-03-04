package usedbookshop.soobook.global.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("Start: {}", joinPoint.toString());

        try {
            //메서드 시작 시간 측정 시작
            return joinPoint.proceed();
        } finally {

            //끝난 시간과 시작 시간을 계산하여 총 소요 시간 출력.
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("End: {}, {}ms", joinPoint.toString(), timeMs );
        }
    }
}
