package pollub.karaokeapp.decorator.performance;

import pollub.karaokeapp.model.performance.Performance;

/**
 * Tydzień 3, Wzorzec Decorator 1
 * Dekorator dodający efekt echa do występu
 */
public class EchoEffectDecorator extends PerformanceDecorator {

    private int echoDelay;
    private float echoIntensity;

    public EchoEffectDecorator(Performance decoratedPerformance, int echoDelay, float echoIntensity) {
        super(decoratedPerformance);
        this.echoDelay = echoDelay;
        this.echoIntensity = echoIntensity;
    }

    @Override
    public String toString() {
        return decoratedPerformance.toString() +
                " [Echo: delay=" + echoDelay + "ms, intensywność=" + echoIntensity + "]";
    }

    @Override
    public int getScore() {
        return decoratedPerformance.getScore() + (int)(20 * echoIntensity);
    }
}
// Koniec, Tydzień 3, Wzorzec Decorator 1