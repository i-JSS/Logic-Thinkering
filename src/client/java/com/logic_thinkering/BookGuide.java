package com.logic_thinkering;

import java.util.List;

// Produto final
class BookGuide {
    private final List<BookPage> components;
    private int currentPageIndex = 0;

    public BookGuide(List<BookPage> components) {
        this.components = components;
    }

    public BookComponent getCurrentComponent() {
        return components.get(currentPageIndex);
    }

    public List<BookPage> getComponents() {
        return components;
    }

    public boolean hasNextComponent() {
        return currentPageIndex < components.size() - 1;
    }

    public boolean hasPreviousComponent() {
        return currentPageIndex > 0;
    }

    public void nextComponent() {
        if (hasNextComponent()) {
            currentPageIndex++;
        }
    }

    public void previousComponent() {
        if (hasPreviousComponent()) {
            currentPageIndex--;
        }
    }
}