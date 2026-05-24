package com.logic_thinkering;

import java.util.ArrayList;
import java.util.List;

// Diretor
public class BookGuideDirector {
    private BookGuideBuilder builder;

    public BookGuideDirector(BookGuideBuilder builder) {
        this.builder = builder;
    }

    public BookGuide construct() {
        return builder.build();
    }
}