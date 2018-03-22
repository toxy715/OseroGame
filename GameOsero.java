import java.util.Scanner;
import java.awt.*;
/* プログラムの仕様
   ルールの実装
   黒で挟んだところが白に変わる
   白で挟んだところが黒に変わる
   挟むことができないところには駒が置けない。また、置く場所がない場合はパスする
   どちらかの駒が置くところがなくなり盤面がすべて埋まっていたらゲームを終了する
    
   オプションルール
   ゲーム進行中、黒の駒の数、白の駒の数を表示する
   ゲーム終了時、お互いの駒の最終数表示してどちらが勝ったのか表示する
*/
public class GameOsero {
    // --- フィールド
    //public static final String SENTE = "●";
    //public static final String KOTE = "○";
    public static final int CELL = 8;
    public static final int CELL_CHECK = 6;
    Board board;
    String[][] new_put; //boardで新しく表示されたマスの位置を記憶させる変数
    String koma;
    int turn_count;
    int sente,kote;  // sente：黒, kote：白

    // --- コンストラクタ
    // Boardを初期化する
    public GameOsero() {
        this.board = new Board();
	this.koma = "□";
	this.new_put = new String[CELL][CELL];  //new_putの初期化
	this.turn_count = 1;
	this.sente = 2;
	this.kote = 2;
    }

    // 初期盤面を作る
    public void startGame() {
	for (int y = 0; y < CELL; y++) {
	    for (int x = 0; x < CELL; x++) {
		board.setCell(x, y, koma);
	    }
	}
	board.setCell(CELL/2-1,CELL/2-1,"○");   // 左上
	board.setCell(CELL/2,CELL/2-1,"●");   // 右上
	board.setCell(CELL/2-1,CELL/2,"●");   // 左下
	board.setCell(CELL/2,CELL/2,"○");   // 右下
	System.out.println("黒(●)が先手です。");
	System.out.println("●:" + sente + ",○:" + kote);
    }
    
    // 毎ターン呼ばれ,キーボードから(x,y)を入力したらその座標に駒を置けるようにする
    public void turn() {
	Scanner scan = new Scanner(System.in);
	System.out.print("x:");
	int x = scan.nextInt();      // 1文字目を格納
	System.out.print("y:");
	int y = scan.nextInt();      // 2文字目を格納
	
	// 駒が黒か白かの判定
	if(turn_count%2 != 0) { // 黒(先手)のターン(カウントは奇数である)
	    koma = "●";
	} else {                // 白(後手)のターン(カウントは偶数である)
	    koma = "○";
	} 
	put(x,y,koma);   // 駒を置く
	turnover(x,y,koma);// 駒をひっくり返す

	// 駒を置けない場合はパスをする処理?

	turn_count++; // ターンのカウント

	// 黒と白の駒を数える処理
	koma_count();
	System.out.println("●:" + sente + ",○:" + kote);
    }
    
    // 駒を置く処理
    public void put(int x, int y, String koma) {
	//Board copy = new Board();
	//koma = SENTE;                 // 盤面に●を置く(この処理がないとなにも置けない)
	board.setCell(x,y,koma);
	System.out.println();
    }

    // 駒が置くことができるかの判定
    public boolean putable() {
	for(int y = 0; y < CELL; y++) {
	    for(int x =0; x < CELL; x++) {
		if(board.getCell(x,y) == "□") { // すべて埋まっていない(まだ駒が置ける)
		    return true;
		}
	    }
	}
	// 駒が置けないとき(パスさせるとき)
	
	/* 駒が置けないすべてのマス目が埋まっている場合の処理をする
	   ・最終盤面の表示
	   ・黒と白の駒の数を数えてどちらが勝っているか判定する
	   ・駒の数の表示
	*/
	endGame();
	return false;
    }
    // 駒をひっくり返す処理
    public void turnover(int x, int y, String koma) {
	int temp_x = x;
	int temp_y = y;
	// ひっくり返す駒が白の場合
	for(int i = temp_x; temp_x < 0; i--) {   // 置いた駒から左方向の直線上
	    if(board.getCell(temp_x-i,temp_y) == "○") {
		temp_x = i;
	    }
	}
	for(int i = temp_x; temp_x < 8; i++) {   // 置いた駒から右方向の直線上
	    if(board.getCell(temp_x+i,temp_y) == "○") {
		temp_x = i;
	    }
	}
	for(int j = temp_y; temp_y < 0; j--) {   // 置いた駒から上方向の直線上
	    if(board.getCell(temp_x,temp_y-j) == "○") {
		temp_y = j;
	    }
	}
	for(int j = temp_y; temp_y < 8; j--) {   // 置いた駒から下方向の直線上
	    if(board.getCell(temp_x,temp_y+j) == "○") {
		temp_y = j;
	    }
	    System.out.println(temp_x + " " + temp_y);
	}
	//if((tem_x,tem)

	// ひっくり返す駒が黒の場合
    }
    // ゲームの終わり勝敗の判定を行う
    public void endGame() {
	show(); // 最終盤面の表示
	// 黒と白の駒を数
	koma_count();
	System.out.println("●:" + sente + ",○:" + kote);
	if(sente > kote) {
	    System.out.println("先手の勝利です。");
	} else if(kote > sente) {
	    System.out.println("後手の勝利です。");
	}
    }
    
    // 盤面の駒の数を数える(オプション)
    public void koma_count() {
	int sente_count = 0;
	int kote_count = 0;
	for (int y = 0; y < CELL; y++) {
	    for (int x = 0; x < CELL; x++) {
		if(board.getCell(x,y) == "●") {
		    sente_count++;
		} else if(board.getCell(x,y) == "○") {
		    kote_count++;
		}
	    }
	}
	sente = sente_count;
	kote = kote_count;
	
    }
    // 盤面の表示
    public void show() {
	board.print();
    }

    // 確認用
    public void startGame_check() {
	for (int y = 0; y < CELL_CHECK; y++) {
	    for (int x = 0; x < CELL_CHECK; x++) {
		board.setCell(x, y, koma);
	    }
	}
	// 盤面の真ん中 (x,y)
	board.setCell(2,2,"○");   // 左上
	board.setCell(3,2,"●");   // 右上
	board.setCell(2,3,"●");   // 左下
	board.setCell(3,3,"○");   // 右下
	/*
	  board.setCell(0,0,"●");
	  board.setCell(0,1,"●");
	  board.setCell(0,2,"●");
	  board.setCell(0,3,"●");
	  board.setCell(0,4,"●");
	  board.setCell(0,5,"●");

	  board.setCell(1,0,"●");
	  board.setCell(1,1,"●");
	  board.setCell(1,2,"●");
	  board.setCell(1,3,"●");
	  board.setCell(1,4,"●");
	  board.setCell(1,5,"●");

	  board.setCell(2,0,"●");
	  board.setCell(2,1,"●");
	  //board.setCell(2,2,"●");
	  //board.setCell(2,3,"●");
	  board.setCell(2,4,"●");
	  board.setCell(2,5,"●");

	  board.setCell(3,0,"●");
	  board.setCell(3,1,"●");
	  //board.setCell(3,2,"●");
	  //board.setCell(3,3,"●");
	  board.setCell(3,4,"●");
	  board.setCell(3,5,"●");

	  board.setCell(4,0,"●");
	  board.setCell(4,1,"●");
	  board.setCell(4,2,"●");
	  board.setCell(4,3,"●");
	  board.setCell(4,4,"●");
	  board.setCell(4,5,"●");

	  board.setCell(5,0,"●");
	  board.setCell(5,1,"●");
	  board.setCell(5,2,"●");
	  board.setCell(5,3,"●");
	  //board.setCell(5,4,"●");
	  //board.setCell(5,5,"●");
	  */
	System.out.println("黒(●)が先手です。");
    }
    public void turn_check() {
	Scanner scan = new Scanner(System.in);
	System.out.print("x:");
	int x = scan.nextInt();      // 1文字目を格納
	System.out.print("y:");
	int y = scan.nextInt();      // 2文字目を格納

	System.out.println();
	// 駒が黒か白かの判定
	// 先手か後手か表示する
	if(turn_count%2 != 0) { // 黒(先手)のターン(カウントは奇数である)
	    koma = "●";
	    System.out.print("次は白(○)です");
	} else {                // 白(後手)のターン(カウントは偶数である)
	    koma = "○";
	    System.out.print("次は黒(●)です");
	} 
	put_check(x,y,koma);
	turnover_check(x,y,koma);
	// 駒を置けない場合はパスをする処理?

	turn_count++; // ターンのカウント

	// 黒と白の駒を数える処理
	koma_count_check();
	System.out.println("●:" + sente + ",○:" + kote);
    }
    
    // 駒を置く処理
    public void put_check(int x, int y, String koma) {
	//Board copy = new Board();
	//koma = SENTE;                 // 盤面に●を置く(この処理がないとなにも置けない)
	board.setCell(x,y,koma);
	System.out.println();
    }

    // 駒が置くことができるかの判定
    public boolean putable_check() {
	int temp_y = 0;
	int temp_x = 0;
	int count = 0;
	for(int y = 0; y < CELL_CHECK; y++) {
	    for(int x =0; x < CELL_CHECK; x++) {
		// すべて埋まっていない(まだ駒が置ける)が１色の駒だけで他が置けない場合
		if(board.getCell(x,y) == "□") {
		    return true;
		}
		    
	    }
	}
	// 駒が置けないとき(パスさせるとき)
	
	/* 駒が置けないすべてのマス目が埋まっている場合の処理をする
	   ・最終盤面の表示
	   ・黒と白の駒の数を数えてどちらが勝っているか判定する
	   ・駒の数の表示
	*/
	endGame_check();
	System.out.println("NG");
	return false;
    }

    // 駒をひっくり返す処理
    public void turnover_check(int x, int y, String koma) {
	int temp_x = x;
	int temp_y = y;
	// ひっくり返す駒が白の場合
	if(turn_count%2 != 0){
	    for(int i = temp_x; temp_x < 0; i--) {   // 置いた駒から左方向の直線上
		if(board.getCell(i,temp_y) == "○") {
		    temp_x = i;
		    board.setCell(temp_x,temp_y,koma);
		}
		//System.out.println("hidari:" + i);
	    }
	    for(int i = temp_x; i < 5; i++) {   // 置いた駒から右方向の直線上
		if(board.getCell(i,temp_y) == "○") {
		    temp_x = i;
		    board.setCell(temp_x,temp_y,koma);
		}
		//System.out.println("migi:" + i);
	    } 
	    for(int j = temp_y; temp_y < 0; j--) {   // 置いた駒から上方向の直線上
		if(board.getCell(temp_x,j) == "○") {
		    temp_y = j;
		    board.setCell(temp_x,temp_y,koma);
		}
	    }
	    for(int j = temp_y; j < 5; j++) {   // 置いた駒から下方向の直線上
		if(board.getCell(temp_x,j) == "○") {
		    temp_y = j;
		    board.setCell(temp_x,temp_y,koma);
		    //System.out.println("OK" + temp_x + " " + temp_y);
		}
		//System.out.println("NG");
	    
	    }
	    //if((tem_x,tem)
	} else if(turn_count%2 == 0) {
	    // ひっくり返す駒が黒の場合
	    for(int i = temp_x; temp_x < 0; i--) {   // 置いた駒から左方向の直線上
		if(board.getCell(i,temp_y) == "●") {
		    temp_x = i;
		    board.setCell(temp_x,temp_y,koma);
		}
		//System.out.println("hidari:" + i);
	    }
	    for(int i = temp_x; i < 5; i++) {   // 置いた駒から右方向の直線上
		if(board.getCell(i,temp_y) == "●") {
		    temp_x = i;
		    board.setCell(temp_x,temp_y,koma);
		}
		//System.out.println("migi:" + i);
	    } 
	    for(int j = temp_y; temp_y < 0; j--) {   // 置いた駒から上方向の直線上
		if(board.getCell(temp_x,j) == "●") {
		    temp_y = j;
		    board.setCell(temp_x,temp_y,koma);
		}
	    }
	    for(int j = temp_y; j < 5; j++) {   // 置いた駒から下方向の直線上
		if(board.getCell(temp_x,j) == "●") {
		    temp_y = j;
		    board.setCell(temp_x,temp_y,koma);
		    //System.out.println("OK" + temp_x + " " + temp_y);
		}
		//System.out.println("NG");
	    
	    }
	}
    }
    
    // ゲームの終わり勝敗の判定を行う
    public void endGame_check() {
	show_check(); // 最終盤面の表示
	// 黒と白の駒を数
	koma_count_check();
	System.out.println("●:" + sente + ",○:" + kote);
	if(sente > kote) {
	    System.out.println("先手の勝利です。");
	} else if(kote > sente) {
	    System.out.println("後手の勝利です。");
	}
    }
    
    // 盤面の駒の数を数える(オプション)
    public void koma_count_check() {
	int sente_count = 0;
	int kote_count = 0;
	for (int y = 0; y < CELL_CHECK; y++) {
	    for (int x = 0; x < CELL_CHECK; x++) {
		if(board.getCell(x,y) == "●") {
		    sente_count++;
		} else if(board.getCell(x,y) == "○") {
		    kote_count++;
		}
	    }
	}
	sente = sente_count;
	kote = kote_count;
	
    }

    public void show_check() {
	board.print_check();
    }
    public static void main(String[] args) {
	GameOsero game = new GameOsero();
	game.startGame_check();
	while(game.putable_check() == true) {  // 駒が置くことができるか 
	    game.show_check();           // 毎ターン盤面を表示する
	    game.turn_check();                 // 毎ターンの処理
	}
	
    }
}
