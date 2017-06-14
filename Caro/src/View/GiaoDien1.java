package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.IconView;

import Model.BanCo;
import Model.ViTriDanh;

public class GiaoDien1 implements ActionListener{
	JFrame frame;
	BanCo banCo;
	int[][] a;
	JButton[][] button;
	JButton but;
	JMenuItem item, item1;
	int dem = 0;

	public GiaoDien1(BanCo banCo1) {
		frame = new JFrame();
		this.banCo = banCo1;
		this.a = banCo.getBoard();
		button = new JButton[a.length][a[0].length];

		menu();
		center();
		inBut();

		frame.setTitle("Caro");
		// frame.setLayout(new BorderLayout());
		// frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		item = new JMenuItem("New game");
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new GiaoDien1(new BanCo(12, 12));
			}
		});
		item1 = new JMenuItem("Exit");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		menu.add(item);
		menu.add(item1);
		JMenu menu1 = new JMenu("About");
		menuBar.add(menu);
		menuBar.add(menu1);
		frame.setJMenuBar(menuBar);
	}

	public void center() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(button.length, button[0].length));
		pane.setBorder(BorderFactory.createTitledBorder("Ban co"));
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button.length; j++) {
				but = button[i][j] = new JButton();
				but.setPreferredSize(new Dimension(50, 50));
				but.setBackground(Color.WHITE);
				but.addActionListener(this);
				pane.add(but);
			}
		}
		frame.add(pane, BorderLayout.CENTER);
	}

	public void inBut() {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i][j] == 1) {
					button[i][j].setText("x");
				} else {
					if (a[i][j] == 2) {
						button[i][j].setText("o");
					} else {
						button[i][j].setText("");
					}

				}
			}
		}
	}

	public void ve() {
		frame.repaint();
	}

	public JButton[][] getButton() {
		return button;
	}

	public void setButton(JButton[][] button) {
		this.button = button;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if(button[i][j]==e.getSource()){
					if(dem%2==0){
						banCo.Danh(new ViTriDanh(i, j), 1);
						banCo.setViTri(new ViTriDanh(i, j));
					}else{
						banCo.Danh(new ViTriDanh(i, j), 2);
						banCo.setViTri(new ViTriDanh(i, j));
					}
				}
			}
		}
		inBut();
		dem++;
		if (banCo.ketThuc()){
			if (banCo.mayThang(banCo.getViTri())) {
				JOptionPane.showMessageDialog(null, "Nguoi choi O thang");
				frame.setVisible(false);
				new ManHinhchinh();
			} else if (banCo.nguoiThang(banCo.getViTri())) {
				JOptionPane.showMessageDialog(null, "Nguoi choi X thang");
				frame.setVisible(false);
				new ManHinhchinh();
			} else {
				JOptionPane.showMessageDialog(null, "Hoa");
				frame.setVisible(false);
				new ManHinhchinh();
			}
		}
	}
	public static void main(String[] args) {
		new GiaoDien1(new BanCo(12, 12));
	}
}
