package train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainSimulationApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {
				createAndShowGUI();
			} catch (BadPositionForTrainException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

    private static void createAndShowGUI() throws BadPositionForTrainException {
        JFrame frame = new JFrame("Train Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        Railway railway = initializeRailway();
        Position p = new Position(railway.getElements()[0], Direction.LR);
        int i= railway.getElements().length;
        Position p1 = new Position(railway.getElements()[i-1], Direction.RL);
		
        Train train1 = new Train("Train1", p);
		Train train2 = new Train("Train2", p);
		Train train3 = new Train("Train3", p1);
		Train train4 = new Train("Train4", p1);
		
		System.out.println(train1.toString());
		System.out.println(train2.toString());
		System.out.println(train3.toString());
		System.out.println(train4.toString());
		
		railway.setLRDirectionTrainOnTrack(0);
		railway.setRLDirectionTrainOnTrack(0);

		// Create and start threads for trains
		Thread thread1 = new Thread(train1);
		Thread thread2 = new Thread(train2);
		Thread thread3 = new Thread(train3);
		Thread thread4 = new Thread(train4);

        JPanel railwayPanel = new RailwayPanel(railway, train1, train2, train3, train4);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(railwayPanel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, new ActionListener() {
        	private boolean started = false;
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
                if (!started) {

                    thread1.start();
                    thread2.start();
                    thread3.start();
                    thread4.start();

                    started = true;
                }

                railwayPanel.repaint(); // Redraw the railway
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private static Railway initializeRailway() {
        Station A = new Station("GareA", 4);
        Station B = new Station("GareB", 2);
        Station D = new Station("GareD", 4);
        Section AB = new Section("AB");
        Section BC = new Section("BC");
        Section CD = new Section("CD");
        Railway r = new Railway(new Element[] { A, AB, B, BC, CD, D });
        System.out.println("The railway is:");
		System.out.println("\t" + r);
        return new Railway(new Element[] { A, AB, B, BC, CD, D });
    }
}

