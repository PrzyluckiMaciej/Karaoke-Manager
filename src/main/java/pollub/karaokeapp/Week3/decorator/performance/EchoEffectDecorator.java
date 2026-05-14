package pollub.karaokeapp.Week3.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający efekt echa do występu
 */
public class EchoEffectDecorator extends PerformanceDecorator {

    private static final int ECHO_SCORE_MULTIPLIER = 20;

    private int echoDelay;
    private float echoIntensity;

    public EchoEffectDecorator(Performance decoratedPerformance, int echoDelay, float echoIntensity) {
        super(decoratedPerformance);
        validateEchoParameters(echoDelay, echoIntensity);
        this.echoDelay = echoDelay;
        this.echoIntensity = echoIntensity;
    }

    private void validateEchoParameters(int delay, float intensity) {
        if (delay < 0) {
            throw new IllegalArgumentException("Echo delay cannot be negative");
        }
        if (intensity < 0 || intensity > 1.0f) {
            throw new IllegalArgumentException("Echo intensity must be between 0 and 1");
        }
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Echo: delay=" + echoDelay + "ms, intensywność=" + echoIntensity + "]";
    }

    @Override
    public int getScore() {
        return decoratedPerformance.getScore() + (int)(ECHO_SCORE_MULTIPLIER * echoIntensity);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1