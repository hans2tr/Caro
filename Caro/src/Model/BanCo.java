package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BanCo {
	List<ViTriDanh> dsVTRong;// danh sach cac vi tri chua duoc di
	private int[][] board;// ban co
	private List<ViTriDanh> dsCacLa;// danh sach cac vi tri con
	ViTriDanh viTri;
	private int viTriX;

	public List<ViTriDanh> getDsCacLa() {
		return dsCacLa;
	}

	public void setDsCacLa(List<ViTriDanh> dsCacLa) {
		this.dsCacLa = dsCacLa;
	}

	public BanCo(int x, int y) {
		board = new int[x][y];
		dsCacLa = new ArrayList<>();
	}

	public ViTriDanh getViTri() {
		return viTri;
	}

	public void setViTri(ViTriDanh viTri) {
		this.viTri = viTri;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	// bat dau danh
	public void Danh(ViTriDanh vitri, int quan) {
		int[][] boardNew = board;
		boardNew[vitri.x][vitri.y] = quan; // quan = 1 for X, 2 for O
		setBoard(boardNew);
	}

	// Nhap vao vi tri tu ban phim
	public void nhapViTriDanh() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Your move: ");
		int x = scan.nextInt();
		int y = scan.nextInt();
		ViTriDanh point = new ViTriDanh(x, y);
		Danh(point, 2);
	}

	// Nuoc di tot nhat cho may
	public ViTriDanh nuocDiTotNhat() {
		int MAX = Integer.MIN_VALUE;
		int best = Integer.MIN_VALUE;
		for (int i = 0; i < dsCacLa.size(); ++i) {
			if (MAX < dsCacLa.get(i).score) {
				MAX = dsCacLa.get(i).score;
				best = i;
			}
		}

		return dsCacLa.get(best).pos;
	}

	// danh sach cac vi tri co the di duoc
	public List<ViTriDanh> dSViTriChuaDanh() {
		List<ViTriDanh> dsVTDaDi = new ArrayList<>();
		dsVTRong = new ArrayList<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0) {
					dsVTDaDi.add(new ViTriDanh(i, j));
				}
			}
		}
		for (int i = 0; i <= dsVTDaDi.size() - 1; i++) {
			ViTriDanh vitri = dsVTDaDi.get(i);
			if (vitri.getX() - 1 >= 0 && vitri.getY() - 1 >= 0) {
				if (board[vitri.getX() - 1][vitri.getY() - 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() - 1, vitri.getY() - 1));
				}
			}
			if (vitri.getX() - 1 >= 0) {
				if (board[vitri.getX() - 1][vitri.getY()] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() - 1, vitri.getY()));
				}
			}
			if (vitri.getX() - 1 >= 0 && vitri.getY() + 1 < board[0].length) {
				if (board[vitri.getX() - 1][vitri.getY() + 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() - 1, vitri.getY() + 1));
				}
			}
			if (vitri.getY() - 1 >= 0) {
				if (board[vitri.getX()][vitri.getY() - 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX(), vitri.getY() - 1));
				}
			}
			if (vitri.getY() + 1 < board[0].length) {
				if (board[vitri.getX()][vitri.getY() + 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX(), vitri.getY() + 1));
				}
			}
			if (vitri.getX() + 1 < board.length && vitri.getY() - 1 >= 0) {
				if (board[vitri.getX() + 1][vitri.getY() - 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() + 1, vitri.getY() - 1));
				}
			}
			if (vitri.getX() + 1 < board.length) {
				if (board[vitri.getX() + 1][vitri.getY()] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() + 1, vitri.getY()));
				}
			}
			if (vitri.getX() + 1 < board.length && vitri.getY() + 1 < board[0].length) {
				if (board[vitri.getX() + 1][vitri.getY() + 1] == 0) {
					dsVTRong.add(new ViTriDanh(vitri.getX() + 1, vitri.getY() + 1));
				}
			}
		}
		for (int i = 0; i < dsVTRong.size(); i++) {
			for (int k = i + 1; k < dsVTRong.size(); k++) {
				if (dsVTRong.get(i).getX() == dsVTRong.get(k).getX()
						&& dsVTRong.get(i).getY() == dsVTRong.get(k).getY()) {
					dsVTRong.remove(k);
				}
			}
		}
		return dsVTRong;
	}

	// kiem tra win cho may
	public boolean mayThang(ViTriDanh viTri) {
		int ngang = 0;
		int doc = 0;
		int cheo1 = 0;
		int cheo2 = 0;
		// Xet hang ngang
		for (int i = 0; i < 5; i++) {
//			System.out.println(viTri.getX() + " " + viTri.getY());
			if (viTri.getY() + i < board[0].length) {
				if (board[viTri.getX()][viTri.getY() + i] == 2) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getY() - i >= 0) {
				if (board[viTri.getX()][viTri.getY() - i] == 2) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (ngang == 5) {
			return true;
		}
		// Xet hang doc
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY()] == 2) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY()] == 2) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (doc == 5) {
			return true;
		}
		// xet hang cheo1
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY() + i] == 2) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY() - i] == 2) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (cheo1 == 5) {
			return true;
		}
		// xet duong cheo 2
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() + i < board.length) {
				if (board[viTri.getX() - i][viTri.getY() + i] == 2) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() - i >= 0) {
				if (board[viTri.getX() + i][viTri.getY() - i] == 2) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (cheo2 == 5) {
			return true;
		}
		return false;
	}

	// kiem tra win cho nguoi
	public boolean nguoiThang(ViTriDanh viTri) {
		int ngang = 0;
		int doc = 0;
		int cheo1 = 0;
		int cheo2 = 0;
		// Xet hang ngang
		for (int i = 0; i < 5; i++) {
			if (viTri.getY() + i < board[0].length) {
				if (board[viTri.getX()][viTri.getY() + i] == 1) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getY() - i >= 0) {
				if (board[viTri.getX()][viTri.getY() - i] == 1) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (ngang == 5) {
			return true;
		}
		// Xet hang doc
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY()] == 1) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY()] == 1) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (doc == 5) {
			return true;
		}
		// xet hang cheo1
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY() + i] == 1) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY() - i] == 1) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (cheo1 == 5) {
			return true;
		}
		// xet duong cheo 2
		for (int i = 0; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() + i < board.length) {
				if (board[viTri.getX() - i][viTri.getY() + i] == 1) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() - i >= 0) {
				if (board[viTri.getX() + i][viTri.getY() - i] == 1) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		if (cheo2 == 5) {
			return true;
		}
		return false;
	}

	// dem X(O)
	public List<Integer> dem(ViTriDanh viTri, int a) {
		List<Integer> diem = new ArrayList<>();
		int ngang = 0;
		int doc = 0;
		int cheo1 = 0;
		int cheo2 = 0;
		// Xet hang ngang
		for (int i = 1; i < 5; i++) {
			if (viTri.getY() + i < board[0].length) {
				if (board[viTri.getX()][viTri.getY() + i] == a) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getY() - i >= 0) {
				if (board[viTri.getX()][viTri.getY() - i] == a) {
					ngang++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		// Xet hang doc
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY()] == a) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY()] == a) {
					doc++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		// xet hang cheo1
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() + i < board.length) {
				if (board[viTri.getX() + i][viTri.getY() + i] == a) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() - i >= 0) {
				if (board[viTri.getX() - i][viTri.getY() - i] == a) {
					cheo1++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		// xet duong cheo 2
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() - i >= 0 && viTri.getY() + i < board.length) {
				if (board[viTri.getX() - i][viTri.getY() + i] == a) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < 5; i++) {
			if (viTri.getX() + i < board.length && viTri.getY() - i >= 0) {
				if (board[viTri.getX() + i][viTri.getY() - i] == a) {
					cheo2++;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		diem.add(ngang);
		diem.add(doc);
		diem.add(cheo1);
		diem.add(cheo2);
		return diem;
	}

	// diem tot nhat
	public int bestCount(List<Integer> list) {
		int dem = 0;
		for (int i = 0; i < list.size(); i++) {
			if (dem < list.get(i)) {
				dem = list.get(i);
			}
		}
		return dem;
	}

	// hoa
	public boolean hoa() {
		if (dSViTriChuaDanh().isEmpty()) {
			return true;
		}
		return false;
	}

	// function MIN-VALUE(state)
	public int MIN_VALUE(List<ViTriDanh> list) {// 2 ng

		int min = Integer.MAX_VALUE;
		int index = Integer.MIN_VALUE;

		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getScore() < min) {
				min = list.get(i).getScore();
				index = i;
			}
		}
		return list.get(index).getScore();
	}

	// function MAX-VALUE(state)
	public int MAX_VALUE(List<ViTriDanh> list) {// 1 may
		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getScore() > max) {
				max = list.get(i).getScore();
				index = i;
			}
		}

		return list.get(index).getScore();
	}

	// Cach tinh diem de danh gia
	private int changeInScore(int X, int O) {
		int change;
		if (X == 5) {// X is Max
			change = 100;
		} else if (X == 4) {
			change = 13;
		} else if (X == 3) {
			change = 4;
		} else if (X == 2) {
			change = 1;
		}

		else if (O == 5) {// O is Min
			change = 100;
		} else if (O == 4) {
			change = 12;
		} else if (O == 3) {
			change = 3;
		} else {
			change = 0;
		}
		return change;
	}

	public void displayBoard() {
		System.out.print("  ");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void minimax(int[][] board, int depth) {
		List<ViTriDanh> vitriRong = minimaxX(board, 1, depth++);
//		System.out.println(vitriRong.size());
		for (int i = 0; i < vitriRong.size(); i++) {
			dsCacLa.add(vitriRong.get(i));
		}
	}

	int uptoDepth = 1;

	public List<ViTriDanh> minimaxO(int[][] start, int turn, int depth) {
		List<ViTriDanh> dsNode = dSViTriChuaDanh();// lấy ra các node có thể
		List<ViTriDanh> dsNodeCource = new ArrayList<>();
		for (int i = 0; i < dsNode.size(); i++) {// đi từng node
			ViTriDanh viTri = dsNode.get(i);
			start[viTri.getX()][viTri.getY()] = turn;
			depth++;
			if (depth < uptoDepth) {
				viTri.setScore(MAX_VALUE(minimaxX(start, 1, depth)));
			} else {
				viTri.setScore(demXO(viTri));
			}
			dsNodeCource.add(new ViTriDanh(viTri.getScore(), viTri));
			start[viTri.getX()][viTri.getY()] = 0;

		}
		return dsNodeCource;
	}

	public List<ViTriDanh> minimaxX(int[][] start, int turn, int depth) {
		List<ViTriDanh> dsNode = dSViTriChuaDanh();
		List<ViTriDanh> dsNodeCource = new ArrayList<>();
		for (int i = 0; i < dsNode.size(); i++) {
			ViTriDanh viTri = dsNode.get(i);
			start[viTri.getX()][viTri.getY()] = turn;
			depth++;
			if (depth < uptoDepth) {
				viTri.setScore(MIN_VALUE(minimaxO(start, 2, depth)));
			} else {
				viTri.setScore(demXO(viTri));
			}
			dsNodeCource.add(new ViTriDanh(viTri.getScore(), viTri));
			start[viTri.getX()][viTri.getY()] = 0;
		}
		return dsNodeCource;
	}

	public int demXO(ViTriDanh viTri) {
		int a = bestCount(dem(viTri, 1));
//		System.out.println("X = " + a);
		int b = bestCount(dem(viTri, 2));
//		System.out.println("Y = " + b);
		return changeInScore(a, b);
	}

	public boolean ketThuc() {
		if (nguoiThang(viTri) || mayThang(viTri) || hoa()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BanCo a = new BanCo(5, 5);
		// a.board[3][7] = 1;
		// a.board[4][6] = 1;
		// a.board[5][5] = 1;
		// a.board[6][4] = 1;
		// a.board[7][3] = 1;
		// a.setViTri(new ViTriDanh(7, 3));
		// a.mayThang();

	}
}
