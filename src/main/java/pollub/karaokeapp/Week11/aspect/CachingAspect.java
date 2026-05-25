package pollub.karaokeapp.Week11.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tydzień 11, Wzorzec AspectJ - Aspect 4
 * CachingAspect - prosty cache dla kosztownych metod (np. obliczania wyniku).
 * Zwraca wynik z cache jeśli takie same parametry były już obliczone.
 */
@Aspect
public class CachingAspect {

    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    // Pointcut dotyczy metod które są cache'owane
    @Pointcut("execution(public * pollub.karaokeapp.Week4.facade.ScoringSystemFacade.calculatePerformanceScore(..)) || " +
            "execution(public * pollub.karaokeapp.service.*.*(..))")
    public void cacheableMethods() {}

    @Around("cacheableMethods()")
    public Object aroundCache(ProceedingJoinPoint pjp) throws Throwable {
        String key = createCacheKey(pjp);
        if (cache.containsKey(key)) {
            logger.log("[ASPECT-CACHE]  Cache HIT: " + key);
            return cache.get(key);
        }

        Object result = pjp.proceed();

        // Cache'ujemy wynik (jeśli nie null)
        if (result != null) {
            cache.put(key, result);
            logger.log("[ASPECT-CACHE] Cache PUT: " + key);
        }

        return result;
    }

    private String createCacheKey(ProceedingJoinPoint pjp) {
        String method = pjp.getSignature().toShortString();
        Object[] args = pjp.getArgs();
        String argsString = Arrays.deepToString(args);
        return method + "#" + argsString;
    }

    /**
     * Opcjonalna metoda pomocnicza do czyszczenia cache z poziomu kodu,
     */
    public void clearCache() {
        cache.clear();
        logger.log("[ASPECT-CACHE] Cache wyczyszczony");
    }
}
// Koniec, Tydzień 11, Wzorzec AspectJ - Aspect 4
