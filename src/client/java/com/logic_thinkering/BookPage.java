package com.logic_thinkering;

// Leaf
class BookPage implements BookComponent {
    private final String title;
    private final String text;
    private final String imagePath;

    public BookPage(String title, String text, String imagePath) {
        this.title = title;
        this.text = text;
        this.imagePath = imagePath == null ? "" : imagePath;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }
}