package com.logic_thinkering;

import java.util.ArrayList;
import java.util.List;

// Builder
class StandardBookGuideBuilder implements BookGuideBuilder {
    private final List<BookPage> pages = new ArrayList<>();

    @Override
    public void addPage(String title, String text, String imagePath) {
        pages.add(new BookPage(title, text, imagePath));
    }

    @Override
    public BookGuide build() {
        return new BookGuide(pages);
    }
}
