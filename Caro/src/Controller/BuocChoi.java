package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.BanCo;
import Model.ViTriDanh;

public class BuocChoi {
	public BuocChoi() {
		Scanner scan = new Scanner(System.in);
		BanCo b = new BanCo(10, 10);
		Random rand = new Random();
		b.displayBoard();

		System.out.println("Who's gonna move first? (1)Computer (2)User: ");
		int choice = scan.nextInt();
		if (choice == 1) {
			ViTriDanh p = new ViTriDanh(rand.nextInt(3), rand.nextInt(3));
			b.Danh(p, 1);
//			b.setViTri(p);
			b.displayBoard();
		}

		do {
			System.out.println("Your move: x y");
			ViTriDanh userMove = new ViTriDanh(scan.nextInt(), scan.nextInt());
			b.Danh(userMove, 2); // 2 for C and 1 is the user
			b.setViTri(userMove);
			b.displayBoard();
			if (b.ketThuc())
				break;

			// b.alphaBetaMinimax(b.board, Integer.MIN_VALUE, Integer.MAX_VALUE,
			// 0, 1);

			b.minimax(b.getBoard(), 0);
			for (ViTriDanh pas : b.getDsCacLa()) {
				System.out.println("Point: " + pas.getPos() + " Score: " + pas.getScore());
			}
			ViTriDanh node = b.nuocDiTotNhat();
			// System.out.println("Buoc di: " + "[" + node.getX() + "]["
			// +node.getY() + "]");
			b.Danh(node, 1);
			b.setViTri(node);
			b.displayBoard();
			// xóa các node máy không chọn sau khi máy đã đi
			b.getDsCacLa().clear();

		} while (!b.ketThuc());
		if (b.mayThang(b.getViTri())) {
			System.out.println("Unfortunately, you lost!");
		} else if (b.nguoiThang(b.getViTri())) {
			System.out.println("You win!");
		} else {
			System.out.println("It's a draw!");
		}
	}

	public static void main(String[] args) {
		new BuocChoi();
		
	}
}
