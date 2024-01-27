package train;

import javax.swing.*;
import java.awt.*;


class RailwayPanel extends JPanel {
    private Railway railway;
    private Train train1, train2, train3;

    public RailwayPanel(Railway railway, Train train1, Train train2, Train train3) {
        this.railway = railway;
        this.train1 = train1;
        this.train2 = train2;
        this.train3 = train3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Element[] elements = railway.getElements();
        int startX = 50;
        int startY = getHeight() / 2;
        int spacing = 100;

        // Draw stations and sections
        for (int i = 0; i < elements.length; i++) {
            g.drawRect(startX + i * spacing, startY - 10, 80, 20);
            g.drawString(elements[i].toString(), startX + i * spacing + 30, startY + 5);
        }

        // Draw trains
        drawTrain(g, train1, startX, startY, spacing);
        drawTrain(g, train2, startX, startY, spacing);
        drawTrain(g, train3, startX, startY, spacing);
    }

    private void drawTrain(Graphics g, Train train, int startX, int startY, int spacing) {
    	Element[] elements = railway.getElements();
    	int x = 0;
    	for (int i = 0; i < elements.length; i++) {
            if (elements[i]==train.getPos().getElement()) {
            	x=i;
            }
        }
        int trainX = startX + x  * spacing;
        int trainY = startY;

        g.setColor(Color.RED);
        g.fillRect(trainX - 10, trainY - 20, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString(train.getName(), trainX - 5, trainY - 5);
    }
}