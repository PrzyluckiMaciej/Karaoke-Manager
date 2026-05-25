package pollub.karaokeapp.Week11.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 11, Wzorzec AspectJ - Aspect 1
 * LoggingAspect automatycznie loguje wszystkie publiczne metody
 * w pakietach karaoke. Rejestruje nazwę metody, argumenty i wynik.
 */
@Aspect
public class LoggingAspect {

    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    @Pointcut(
            "execution(public * pollub.karaokeapp..*(..)) && " +
                    "!within(pollub.karaokeapp.Week11.aspect..*) && " +
                    "!within(pollub.karaokeapp.Week2.singleton..*)"
    )
    public void karaokeOperations() {}

    @Before("karaokeOperations()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = getClassName(joinPoint);
        Object[] args = joinPoint.getArgs();

        StringBuilder argString = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            argString.append(args[i]);
            if (i < args.length - 1) argString.append(", ");
        }

        logger.log("[ASPECT-LOGGING] Wywołanie: " + className + "." + methodName +
                "(" + argString + ")");
    }

    @AfterReturning(pointcut = "karaokeOperations()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = getClassName(joinPoint);

        logger.log("[ASPECT-LOGGING] Sukces: " + className + "." + methodName +
                " -> wynik: " + result);
    }

    @AfterThrowing(pointcut = "karaokeOperations()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = getClassName(joinPoint);

        logger.log("[ASPECT-LOGGING] Błąd w: " + className + "." + methodName +
                " -> " + exception.getClass().getSimpleName() + ": " + exception.getMessage());
    }

    /**
     * Metoda pomocnicza - bezpiecznie pobiera klasę z joinPoint,
     * obsługując sytuacje gdzie getTarget() zwraca null
     */
    private String getClassName(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        if (target != null) {
            return target.getClass().getSimpleName();
        }
        return joinPoint.getSignature().getDeclaringTypeName();
    }
}
// Koniec, Tydzień 11, Wzorzec AspectJ - Aspect 1
