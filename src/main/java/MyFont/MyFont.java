package MyFont;

import Game.Game;

import java.awt.*;
import java.io.InputStream;

public class MyFont {
    public Font titleFont, secondaryFont, creditFont;

    public MyFont(Game game) {
        loadFont();
        game.titleFont = titleFont;
        game.secondaryFont = secondaryFont;
        game.creditFont = creditFont;
    }

    private void loadFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/000webfont Regular.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(100f);
            InputStream is2 = getClass().getResourceAsStream("/Font/000webfont Regular.ttf");
            secondaryFont = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(50f);
            InputStream is3 = getClass().getResourceAsStream("/Font/000webfont Regular.ttf");
            creditFont = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(30f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
