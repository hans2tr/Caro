package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Model.BanCo;
import View.GiaoDien;
import View.ManHinhchinh;

public class Game {
	BanCo banCo;
	GiaoDien giaodien;
	JButton but;

	public Game() {
		banCo = new BanCo(12, 12);
		giaodien = new GiaoDien(banCo);
		// choiVoiMay();
		
	}

	public void choiVoiMay() {
		ManHinhchinh thongBao = new ManHinhchinh();
	}

	public static void main(String[] args) {
		new ManHinhchinh();
	}
}
