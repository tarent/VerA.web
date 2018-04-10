package de.tarent.commons.ui;

/*-
 * VerA.web-Middleware, newly MIT licenced, is comprised of:
 * tarent-commons, a set of common components and solutions
 * tarent-contact, platform-independent webservice-based contact management
 * tarent-database, jdbc database library
 * tarent-doctor, Document Generation Platform
 * tarent-octopus, Webservice Data Integrator and Application Server
 *  © 2005, 2006, 2007 asteban <s.mancke@tarent.de>
 *  © 2007 David Goemans <d.goemans@tarent.de>
 *  © 2006, 2007, 2010 Hendrik Helwich <h.helwich@tarent.de>
 *  © 2005, 2006, 2007 Christoph Jerolimov <c.jerolimov@tarent.de>
 *  © 2006 Philipp Kirchner <p.kirchner@tarent.de>
 *  © 2010 Carsten Klein <c.klein@tarent.de>
 *  © 2006 Michael Kleinhenz <m.kleinhenz@tarent.de>
 *  © 2006 Michael Klink <m.klink@tarent.de>
 *  © 2007 Fabian Köster <f.koester@tarent.de>
 *  © 2006 Martin Ley <m.ley@tarent.de>
 *  © 2007 Alex Maier <a.maier@tarent.de>
 *  © 2007, 2015, 2017, 2018 mirabilos <t.glaser@tarent.de>
 *  © 2006, 2007 Jens Neumaier <j.neumaier@tarent.de>
 *  © 2006 Nils Neumaier <n.neumaier@tarent.de>
 *  © 2007, 2008 Martin Pelzer <m.pelzer@tarent.de>
 *  © 2008, 2009 Christian Preilowski <c.thiel@tarent.de>
 *  © 2006, 2008, 2009 Thomas Schmitz <t.schmitz@tarent.de>
 *  © 2007 Robert Schuster <r.schuster@tarent.de>
 * and older code, Copyright © 2001–2007 ⮡ tarent GmbH and contributors.
 * Licensor is tarent solutions GmbH, http://www.tarent.de/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.color.ColorSpace;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.SwingConstants;

/**
 * This class provides you a simple way to show user information as an
 * "nice looking on-screen-display (OSD) dialog".
 *
 * @author Christoph Jerolimov, tarent GmbH
 */
public class OSD implements Serializable, Cloneable, SwingConstants {
    /**
     * serialVersionUID
     */ // just another cvs spam test
    private static final long serialVersionUID = 6078940636791325195L;

    public final static String THEME_NONE = "none";

    public final static String THEME_SUCCESS = "success";

    public final static String THEME_DENIED = "denied";

    public final static String THEME_TOOLTIP = "tooltip";

    public final static String THEME_INFO = "info";

    public final static String THEME_WARNING = "warning";

    public final static String THEME_ERROR = "error";

    private String text;

    private Font font;
    private Color fontColor;
    private Color fontBorderColor;
    private Color borderColor;
    private Color backgroundColor;

    private long timeout = 10000L;

    private int position = SwingConstants.NORTH_WEST;
    /**
     * space outside the box
     */
    private int paddingX = 50;
    /**
     * space outside the box
     */
    private int paddingY = 50;
    /**
     * space inside the box
     */
    private int marginX = 20;
    /**
     * space inside the box
     */
    private int marginY = 8;

    public OSD() {
        this(THEME_INFO);
    }

    public OSD(String theme) {
        setTheme(theme);
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setPosition(int compass) {
        position = compass;
    }

    public void setPosition(int boxx, int boxy) {
        position = PositionHelper.getCompass(boxx, boxy);
    }

    public void setMargin(int x, int y) {
        marginX = x;
        marginY = y;
    }

    public void setMarginX(int x) {
        marginX = x;
    }

    public void setMarginY(int y) {
        marginY = y;
    }

    public void setPadding(int x, int y) {
        paddingX = x;
        paddingY = y;
    }

    public void setPaddingX(int x) {
        paddingX = x;
    }

    public void setPaddingY(int y) {
        paddingY = y;
    }

    public void setTheme(String theme) {
        if (theme == null || theme.equals(THEME_NONE)) {
            setColors(Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK);
        } else if (theme.equals(THEME_TOOLTIP)) {
            setColors(Color.WHITE, Color.BLACK, SystemColor.info, Color.BLACK);
        } else if (theme.equals(THEME_SUCCESS)) {
            setColors(Color.WHITE, Color.BLACK, Color.GREEN, Color.BLACK);
        } else if (theme.equals(THEME_DENIED)) {
            setColors(Color.WHITE, Color.BLACK, Color.RED, Color.BLACK);
        } else if (theme.equals(THEME_INFO)) {
            setColors(Color.WHITE, Color.BLACK, Color.GREEN, Color.BLACK);
        } else if (theme.equals(THEME_WARNING)) {
            setColors(Color.WHITE, Color.BLACK, Color.YELLOW, Color.BLACK);
        } else if (theme.equals(THEME_ERROR)) {
            setColors(Color.WHITE, Color.BLACK, Color.RED, Color.BLACK);
            //} else {
            //	assert false;
        }
        setFont(new Font("Sanf", Font.BOLD, 16));
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    private void setColors(Color fontColor, Color fontBorderColor, Color backgroundColor, Color borderColor) {
        setFont(fontColor);
        setFontBorder(fontBorderColor);
        setBackground(backgroundColor);
        setBorder(borderColor);
    }

    public void setFont(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontBorder(Color fontBorderColor) {
        this.fontBorderColor = fontBorderColor;
    }

    public void setBorder(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Window showText(String text) {
        setText(text);
        OSD osd = null;
        try {
            osd = (OSD) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Inner inner = new Inner(osd);
        inner.setVisible(true);
        return inner;
    }

    private static class Inner extends Window {
        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -1960685390198037194L;

        /**
         * Cloned information of parent OSD
         */
        private OSD osd;

        private Rectangle position;
        private Image capturedImage;
        private Image mergedOsdImage;
        private int top, left; // for text

        public Inner(OSD osd) {
            super(new Frame());
            this.osd = osd;
            init();

            // FIXME			setAlwaysOnTop(true);

            addMouseListener(new DisposeListener(this) {
                public void mouseClicked(MouseEvent e) {
                    dispose();
                }
            });

            final long timeout = osd.timeout;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(timeout);
                        setVisible(false);
                        dispose();
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }

        public void init() {
            calculateSize();
            capturedImage = getCaptureImage();
            mergedOsdImage = createImage();
        }

        public void paint(Graphics g) {
            g.drawImage(mergedOsdImage, 0, 0, this);
        }

        private Image getCaptureImage() {
            try {
                return new Robot().createScreenCapture(position);
            } catch (AWTException e) {
                return null;
            }
        }

        private void calculateSize() {
            BufferedImage bi = new BufferedImage(1, 1, ColorSpace.TYPE_RGB);
            FontMetrics metrics = bi.getGraphics().getFontMetrics(osd.font);

            int width = metrics.stringWidth(osd.text);
            int height = metrics.getHeight();

            position = PositionHelper.getRectangle(
                    osd.position,
                    osd.marginX * 2 + width,
                    osd.marginY * 2 + height,
                    osd.paddingX,
                    osd.paddingY);

            setBounds(position);
        }

        private Image createImage() {
            int w = position.width;
            int h = position.height;
            int arc = w < h ? w / 3 : h / 3;
            top = osd.marginY + osd.font.getSize();
            left = osd.marginX;

            BufferedImage bi = new BufferedImage(w, h, ColorSpace.TYPE_RGB);
            Graphics2D g2d = bi.createGraphics();

            g2d.setFont(osd.font);
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(capturedImage, 0, 0, new Color(0, 0, 0, 127), this);

            g2d.setPaint(ColorHelper.getMetallic(osd.backgroundColor, h));
            g2d.fillRoundRect(0, 0, w - 1, h - 1, arc, arc);
            g2d.setPaint(ColorHelper.getMetallic(osd.borderColor, h));
            g2d.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            //			createText(g2d, Color.WHITE, 20, 1);
            //			createText(g2d, osd.fontBorderColor, 1, 255);
            //			createText(g2d, osd.fontBorderColor, 2, 0);
            createText(g2d, osd.fontColor, 0, 255);

            return bi;
        }

        private void createText(Graphics2D g2d, Color color, int offset, int alpha) {
            g2d.setColor(ColorHelper.getAlpha(color, alpha));
            for (int ox = -offset; ox <= offset; ox++) {
                for (int oy = -offset; oy <= offset; oy++) {
                    g2d.drawString(osd.text, left + ox, top + oy);
                }
            }
        }

        public void dispose() {
            if (capturedImage != null) {
                capturedImage = null;
            }
            if (mergedOsdImage != null) {
                mergedOsdImage = null;
            }
            super.dispose();
        }
    }
}
