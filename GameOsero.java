import java.awt.*;

public class GameOsero {
    // --- フィールド
    Board board;
    String[][] new_put; //boardで新しく表示されたマスの位置を記憶させる変数

    // --- コンストラクタ
    // Boardを初期化する
    public GameOsero() {
        this.board = new Board();
	this.new_put = new String[8][8];  //new_putの初期化
    }

    // 初期盤面を作る
    public void start() {
	for (int y = 0; y < 8; y++) {
	    for (int x = 0; x < 8; x++) {
		String koma = "□";
		//int i = 0;
		board.setCell(x, y, koma);
	    }
	}
	board.setCell(3,3,"○");   // 左上
	board.setCell(4,3,"●");   // 右上
	board.setCell(3,4,"●");   // 左下
	board.setCell(4,4,"○");   // 右下
    }
    
    public void show() {
	board.print();
    }
}
