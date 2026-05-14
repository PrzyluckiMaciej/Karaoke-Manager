package pollub.karaokeapp.Week5.iterator;

import java.util.List;

public abstract class AbstractKaraokeIterator<T> implements KaraokeIterator<T> {
    protected int currentIndex = 0;
    protected List<T> items;

    protected AbstractKaraokeIterator(List<T> items) {
        this.items = items;
    }

    protected void validateHasNext() {
        if (!hasNext()) {
            throw new KaraokeIteratorException(getNoElementsMessage());
        }
    }

    protected abstract String getNoElementsMessage();

    @Override
    public T next() {
        validateHasNext();
        return items.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}