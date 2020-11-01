package org.ucvts.ema.placeholders;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;

import org.ucvts.ema.EMA;

@SuppressWarnings("serial")
public class PlaceholderJPasswordField extends JPasswordField {

    private String ph;
    private EMA ema;

    public PlaceholderJPasswordField(final int col) {
        super(col);
    }

    public String getPlaceholder() {
        return ph;
    }

    public void setPlaceholder(final String s) {
        ph = s;
    }
    
    @Override
    protected void paintComponent(final Graphics pG) {
    	ema = EMA.getInstance();
        super.paintComponent(pG);

        if (ph == null || ph.length() == 0 || new String(getPassword()).length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_OFF);
        Font f = new Font(ema.TEXT_FONT.getName(), Font.PLAIN, 13);
        g.setFont(f);
        g.setColor(ema.FOREGROUND_COLOR);
        g.drawString(ph, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top+6);
    }

}