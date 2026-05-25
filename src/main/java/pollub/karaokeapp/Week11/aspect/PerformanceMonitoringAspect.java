package pollub.karaokeapp.Week11.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 11, Wzorzec AspectJ - Aspect 2
 * PerformanceMonitoringAspect mierzy czas wykonania metod,
 * rejestruje wydajność i ostrzega o metodach wymagających długo.
 * Wspomaga optymalizację aplikacji poprzez analitykę wydajności.
 */
@Aspect
public class PerformanceMonitoringAspect {

    private final LoggerSingleton logger = LoggerSingleton.getInstance();
    private static final long SLOW_METHOD_THRESHOLD_MS = 100;

    @Pointcut(
            "execution(public * pollub.karaokeapp..*(..)) && " +
                    "!within(pollub.karaokeapp.Week11.aspect..*) && " +
                    "!within(pollub.karaokeapp.Week2.singleton..*)"
    )
    public void monitoredMethods() {}

    @Around("monitoredMethods()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = getClassName(joinPoint);

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

            if (executionTime > SLOW_METHOD_THRESHOLD_MS) {
                logger.log("[ASPECT-PERF] WOLNA METODA: " + className + "." +
                        methodName + " - czas: " + executionTime + "ms");
            } else {
                logger.log("[ASPECT-PERF] OK: " + className + "." + methodName +
                        " - czas: " + executionTime + "ms");
            }

            return result;
        } catch (Throwable e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.log("[ASPECT-PERF] BŁĄD: " + className + "." + methodName +
                    " - czas: " + executionTime + "ms - " + e.getClass().getSimpleName());
            throw e;
        }
    }

    /**
     * Metoda pomocnicza - bezpiecznie pobiera klasę z joinPoint,
     * obsługując sytuacje gdzie getTarget() zwraca null
     */
    private String getClassName(ProceedingJoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        if (target != null) {
            return target.getClass().getSimpleName();
        }
        return joinPoint.getSignature().getDeclaringTypeName();
    }
}
// Koniec, Tydzień 11, Wzorzec AspectJ - Aspect 2
