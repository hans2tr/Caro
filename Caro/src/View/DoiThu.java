package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Game;
import Model.BanCo;

public class DoiThu {
	JFrame frame;
	JButton button, button1;

	public DoiThu() {
		frame = new JFrame();

		button();

//		frame.setLayout(new BorderLayout());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void button() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(2, 1));
		pane.setBorder(BorderFactory.createTitledBorder("Game caro"));
		
//		JPanel pane3 = new JPanel();
//		pane3.setLayout(new FlowLayout());
//		JLabel la = new JLabel("Chao mung den voi game Caro.");
//		pane3.add(la);
		
		JPanel pane1 = new JPanel();
		pane1.setLayout(new FlowLayout());
		button = new JButton("Nguoi voi may");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Game();
				frame.setVisible(false);
				frame.setVisible(false);
			}
		});
		pane1.add(button);
		
		JPanel pane2 = new JPanel();
		pane2.setLayout(new FlowLayout());
		button1 = new JButton("Nguoi voi nguoi");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GiaoDien1(new BanCo(12, 12));
				frame.setVisible(false);
				
			}
		});
		pane2.add(button1);
		
//		pane.add(la,BorderLayout.SOUTH);
		pane.add(pane1, BorderLayout.CENTER);
		pane.add(pane2, BorderLayout.NORTH);

		frame.add(pane);

	}

//	public static void main(String[] args) {
//		new DoiThu();
//	}
}
