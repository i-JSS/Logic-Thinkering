package com.logic_thinkering;

import java.util.ArrayList;
import java.util.List;

// Composite
class BookChapter implements BookComponent {
    private final String title;
    private final List<BookComponent> components = new ArrayList<>();

    public BookChapter(String title) {
        this.title = title;
    }

    public void addComponent(BookComponent component) {
        components.add(component);
    }

    public List<BookComponent> getComponents() {
        return components;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        StringBuilder text = new StringBuilder();
        for (BookComponent component : components) {
            text.append(component.getText()).append("\n");
        }
        return text.toString();
    }

    @Override
    public String getImagePath() {
        return ""; // Capítulos que n têm imagens
    }
}