package com.logic_thinkering;

// Interface
interface BookGuideBuilder {
    void addPage(String title, String text, String imagePath);
    BookGuide build();
}