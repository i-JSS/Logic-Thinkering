package com.logic_thinkering;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BookGui extends LightweightGuiDescription {

    private final BookGuide book;
    private final WButton previousButton;
    private final WButton nextButton;
    private final List<WLabel> textLabels = new ArrayList<>();
    private final List<WSprite> icons = new ArrayList<>();

    public BookGui(BookGuide book) {
        this.book = book;

        // Painel principal
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 300);

        WSprite background = new WSprite(Identifier.of("logic_thinkering", "textures/client/background.png"));
        root.add(background, 0, 0, 17, 17);

        // Título
        WLabel titleLabel = new WLabel(Text.literal(book.getCurrentComponent().getTitle()));
        root.add(titleLabel, 1, 1);

        // texto
        addPageText(root, book.getCurrentComponent().getText(), 48, 3);

        // Imagem
        updatePageImage(root, book.getCurrentComponent().getImagePath());

        // Botão Anterior
        previousButton = new WButton(Text.literal("Anterior"));
        previousButton.setEnabled(book.hasPreviousComponent());
        previousButton.setOnClick(() -> {
            book.previousComponent();
            updatePage(root, titleLabel);
        });
        root.add(previousButton, 1, 14, 4, 1);

        // Botão Próximo
        nextButton = new WButton(Text.literal("Próximo"));
        nextButton.setEnabled(book.hasNextComponent());
        nextButton.setOnClick(() -> {
            book.nextComponent();
            updatePage(root, titleLabel);
        });
        root.add(nextButton, 12, 14, 4, 1);

        root.validate(this);
    }

    private void updatePageImage(WGridPanel root, String imagePath) {
        // Remover a imagem anterior
        for (WSprite icon : icons) {
            root.remove(icon);
        }
        icons.clear();

        // Nova imagem
        if (imagePath != null && !imagePath.isEmpty()) {
            WSprite newIcon = new WSprite(Identifier.of("logic_thinkering", imagePath));
            icons.add(newIcon);
            root.add(newIcon, 1, 9, 14, 3);
        }
    }

    private void addPageText(WGridPanel root, String text, int maxLineLength, int startY) {
        // Limpar os labels anteriores
        for (WLabel label : textLabels) {
            root.remove(label);
        }
        textLabels.clear();

        List<String> wrappedLines = wrapText(text, maxLineLength);

        int y = startY;
        for (String line : wrappedLines) {
            WLabel lineLabel = new WLabel(Text.literal(line));
            textLabels.add(lineLabel);
            root.add(lineLabel, 1, y++);
        }
    }

    private List<String> wrapText(String text, int maxLineLength) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : text.split(" ")) {
            if (word.equals("\n")) {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
                continue;
            }

            if (currentLine.length() + word.length() > maxLineLength) {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
            }
            currentLine.append(word).append(" ");
        }

        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString().trim());
        }

        return lines;
    }

    private void updatePage(WGridPanel root, WLabel titleLabel) {
        BookPage currentPage = (BookPage) book.getCurrentComponent();

        titleLabel.setText(Text.literal(currentPage.getTitle()));
        addPageText(root, currentPage.getText(), 48, 3);
        updatePageImage(root, currentPage.getImagePath());

        previousButton.setEnabled(book.hasPreviousComponent());
        nextButton.setEnabled(book.hasNextComponent());
    }
}