// 繧ｪ繧ｻ繝ｭ
public class OseroMain {
    public static void main(String[] args) {
	//System.out.print("Hello");
	GameOsero game = new GameOsero();
	/* 確認用
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
	game.start();    // 初期盤面をつくる
	game.show();
	//System.out.println();
    }
}


