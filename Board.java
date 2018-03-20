import java.awt.*;

public class Board {
    // フィールド
    private String[][] board;
    
    // コンストラクタ
    public Board() {
	this.board = new String[8][];
	for (int i = 0; i < 8; i++) {
	    this.board[i] = new String[8];
	}
    }

    // メソッド
    public String getCell(int x, int y) {
	return this.board[x][y];
    }

    public void setCell(int x, int y, String koma) {
	this.board[x][y] = koma;
    }

    public void print() {
	// 盤面の表示
	for (int y = 0; y < 8; y++) {
	    for (int x = 0; x < 8; x++) {
		System.out.print(board[x][y]);
	    }
	    System.out.println();
	}
	System.out.println();
    }

    //確認用
    /*
    public static void main(String[] args) {
	Board b = new Board();
	for (int y = 0; y < 8; y++) {
	    for (int x = 0; x < 8; x++) {
		String koma = "□";
		//int i = 0;
		b.setCell(x, y, koma);
	    }
	}
	b.print();
    }
    */
}
