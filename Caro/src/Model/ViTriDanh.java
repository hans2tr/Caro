package Model;

public class ViTriDanh {
	int x, y;
	int score;
	ViTriDanh pos;

	public ViTriDanh(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public ViTriDanh(int score, ViTriDanh pos) {
		super();
		this.score = score;
		this.pos = pos;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ViTriDanh getPos() {
		return pos;
	}

	public void setPos(ViTriDanh pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}
