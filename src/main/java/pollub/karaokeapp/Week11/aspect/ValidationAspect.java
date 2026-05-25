package pollub.karaokeapp.Week11.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import pollub.karaokeapp.Week2.singleton.LoggerSingleton;

/**
 * Tydzień 11, Wzorzec AspectJ - Aspect 3
 * ValidationAspect waliduje parametry metod przed ich wykonaniem.
 * Zapewnia integralność danych poprzez automatyczną walidację.
 * Sprawdza null, zakresy wartości i konsystencję danych karaoke.
 */
@Aspect
public class ValidationAspect {

    private final LoggerSingleton logger = LoggerSingleton.getInstance();

    @Pointcut("execution(public * pollub.karaokeapp.model.song.Song.set*(..)) || " +
            "execution(public * pollub.karaokeapp.model.user.User.set*(..)) || " +
            "execution(public * pollub.karaokeapp.model.performance.Performance.set*(..)) || " +
            "execution(public * pollub.karaokeapp.model.playlist.Playlist.set*(..))")
    public void setterMethods() {}

    @Pointcut("execution(public * pollub.karaokeapp.Week2.builder.*.*.build(..))")
    public void builderMethods() {}

    @Before("setterMethods()")
    public void validateSetters(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();

        if (target == null) {
            return;
        }

        if (args.length > 0) {
            Object value = args[0];

            // Walidacja null
            if (value == null && !isNullableField(methodName)) {
                throw new IllegalArgumentException(
                        "[VALIDATION] Null nie jest dozwolony w " + target.getClass().getSimpleName() +
                                "." + methodName);
            }

            // Walidacja stringów
            if (value instanceof String) {
                String strValue = (String) value;
                if (strValue.isEmpty() && (methodName.contains("Title") ||
                        methodName.contains("Name") || methodName.contains("Nick"))) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] String nie może być pusty w " + methodName);
                }
            }

            // Walidacja liczb
            if (value instanceof Integer) {
                int intValue = (Integer) value;

                // Walidacja poziomu (0-10)
                if (methodName.contains("Level") && (intValue < 0 || intValue > 10)) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] Level musi być w zakresie 0-10, otrzymano: " + intValue);
                }

                // Walidacja punktów (≥ 0)
                if (methodName.contains("Points") && intValue < 0) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] Points nie mogą być ujemne, otrzymano: " + intValue);
                }

                // Walidacja czasu trwania (> 0)
                if (methodName.contains("Duration") && intValue <= 0) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] Duration musi być > 0, otrzymano: " + intValue);
                }

                // Walidacja trudności (1-10)
                if (methodName.contains("Difficulty") && (intValue < 1 || intValue > 10)) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] Difficulty musi być w zakresie 1-10, otrzymano: " + intValue);
                }

                // Walidacja wyniku (0-1000)
                if (methodName.contains("Score") && (intValue < 0 || intValue > 1000)) {
                    throw new IllegalArgumentException(
                            "[VALIDATION] Score musi być w zakresie 0-1000, otrzymano: " + intValue);
                }
            }
        }

        if (target != null) {
            logger.log("[ASPECT-VALIDATION] Walidacja sukces: " + target.getClass().getSimpleName() +
                    "." + methodName + "(" + (args.length > 0 ? args[0] : "brak") + ")");
        }
    }

    @Before("builderMethods()")
    public void validateBuilderMethods(JoinPoint joinPoint) {
        Object builder = joinPoint.getTarget();
        if (builder != null) {
            String className = builder.getClass().getSimpleName();
            logger.log("[ASPECT-VALIDATION] Builder walidacja przed build(): " + className);
        }
    }

    /**
     * Metoda pomocnicza - określa czy pole może przyjąć wartość null
     */
    private boolean isNullableField(String methodName) {
        return methodName.contains("Genre") ||
                methodName.contains("Artist") ||
                methodName.contains("Description");
    }
}
// Koniec, Tydzień 11, Wzorzec AspectJ - Aspect 3
