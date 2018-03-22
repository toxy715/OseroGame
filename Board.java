import java.awt.*;
// 定数
public class Board {
    // フィールド
    private String[][] board;
    public static final int CELL = 8;
    public static final int CELL_CHECK = 6;
    
    // コンストラクタ
    public Board() {
	this.board = new String[6][];
	for (int i = 0; i < 6; i++) {
	    this.board[i] = new String[6];
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
	int i = 0;
	int j = 0;
	System.out.print("  " + i);
	for(i = 1; i < CELL; i++){
	    System.out.print(" " + i);
	}
	System.out.print(" (X)");
	System.out.println();
	for (int y = 0; y < CELL; y++) {
	    System.out.print(" " + j);
	    j++;
	    for (int x = 0; x < CELL; x++) {
		System.out.print(board[x][y]);
	    }
	    System.out.println();
	}
	System.out.println("(Y)");
    }
    
    //確認用
    public void print_check() {
	// 盤面の表示
	int i = 0;
	int j = 0; 
	System.out.print(i);
	for(i = 1; i < CELL_CHECK; i++){
	    System.out.print(" " + i);
	}
	System.out.print(" (X)");
	System.out.println();
	for(int y = 0; y < CELL_CHECK; y++) {
	    for(int x = 0; x < CELL_CHECK; x++) {
		System.out.print(board[x][y]);
	    }
	    System.out.print(" " + j);
	    j++;
	    System.out.println();
	}
	System.out.println("　　　　　　　　(Y)");
    }
    
    public static void main(String[] args) {
	Board b = new Board();
	for (int y = 0; y < CELL_CHECK; y++) {
	    for (int x = 0; x < CELL_CHECK; x++) {
		String koma = "□";
		//int i = 0;
		b.setCell(x, y, koma);
	    }
	}
	b.print_check();
    }
    
}
