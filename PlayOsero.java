// オセロをプレイするプログラム
public class PlayOsero {
    public static void main(String[] args) {
	GameOsero game = new GameOsero();
	game.startGame();    // 盤面の初期化
	while(game.putable() == true) {  // 駒が置くことができるか 
	    game.show();                 // 毎ターン盤面を表示する
	    game.turn();                 // 毎ターンの処理
	}
	
	//確認用
	/*
	while(true) {
	    game.show();
	    game.turn();
	}
	//System.out.println();

	Board b = new Board();
	  int x,y,num;
	  int i = 0;
	  for (y = 0; y < 4; y++) {
	  for (x = 0; x <4; x++) {
	  num = y * 4 + x;
	  i = 0;
	  b.setCell(x,y,num);
	  System.out.println("(x:" + x + ",y:" + y + ")=" + b.getCell(x,y));
	  }
	  }
	*/
    }
}


