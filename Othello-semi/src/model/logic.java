package model;

public class logic {
	/*配列の中身　空の場合*/
	final int EMPTY = 0;
	/*配列の中身　黒の場合*/
	final int BLACK = 1;
	/*配列の中身　白の場合*/
	final int WHITE = 2;

	/*オセロ盤の配列*/
	private int board[][] = new int[8][8];
	/*手番の石の色*/
	private int stone = BLACK;
	/*次の手番の石の色*/
	private int rev_stone = WHITE;

	/** getBoard
	 *  オセロ盤を渡す
	 *
	 * @戻り値 オセロ盤
	 */
	public int[][] getBoard() {
		return board;
	}

	/** setBoard
	 * オセロ盤を設定する
	 *
	 * @引数 board
	 */
	public void setBoard(int board[][]) {
		this.board = board;
	}

	/** getStone
	 *  手番プレイヤーの値を渡す
	 *
	 * @戻り値 手番プレイヤー
	 */
	public int getStone() {
		return stone;
	}

	/** getRev_stone
	 *  次のプレイヤーの値を渡す
	 *
	 * @戻り値 次のプレイヤー
	 */
	public int getRev_stone() {
		return rev_stone;
	}

	/** getRev_stone
	 *  次のプレイヤーの値を渡す
	 *
	 * @戻り値 次のプレイヤー
	 */
	public void setRev_stone(int rev_stone) {
		this.rev_stone = rev_stone;
	}

	/** getRev_stone
	 *  次のプレイヤーの値を渡す
	 *
	 * @戻り値 次のプレイヤー
	 */
	public void setStone(int stone) {
		this.stone = stone;
	}

	/** コンストラクタ
	 *
	 * 　オセロ盤の中にすべて０を入れる
	 */
	public void Logic() {

		for( int x = 0; x < board.length ; x++ ){
			for ( int y = 0 ; y < board[x].length ; y++){
				board[x][y] = 0;
			}
		}
	}

	/** reset
	 *
	 * 初期配置に設定する
	 *
	 */
	public  void reset() {
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
	}

	/** test
	 *  初期配置が正しいかの確認
	 *
	 * @戻り値 boolean
	 */
	public  boolean test() {

		boolean bool = true;
		// 真ん中４つが正しいのか
		if(board[3][3] == 2 && board[3][4] == 1 && board[4][3] == 1 && board[4][4] == 2){
			for( int x = 0; x < board.length ; x++ ){
				for ( int y = 0 ; y < board[x].length ; y++){
					// 真ん中が来たらとばす
					if ( x == 3 || x== 4){
						if(y == 3 || y ==4){
							continue;
						}
					}
					// 真ん中以外に０以外のものがあれば
					if(board[x][y] != 0){
						bool = false;
					}
				}
			}
		}
		// 真ん中が初期配置と違う場合
		else{
			bool = false;
		}
		return bool;
	}

	/** change
	 *
	 * 手番プレイヤーの交代
	 *
	 */
	public void change() {
		// 使い捨ての箱を用意
		int BOX = stone ;
		// 手番交代
		stone = rev_stone ;
		rev_stone = BOX ;

	}

	/** sync
	 *  オセロ盤を渡す
	 *
	 * @戻り値 配列の　x軸　y軸
	 * @引数 クリックされた位置
	 */
	public  int[] sync( int Arv) {
		int count = 0;
		int [] a = {100,100} ;
		for (int x = 0 ; x < board.length ; x++){
			for (int y = 0 ; y < board[x].length ;y++){
				//  count++;
				if(Arv == count){

					a[0] = y;
					a[1] = x;
				}
				count++;
			}
		}
		return a;
	}

	/** turnStone
	 *
	 * ８方向ひっくり返せるかの確認
	 * @引数 x軸　y軸
	 */
	public  void turnStone(int x, int y) {
        // 8方向の駒の配置を確認し、ひっくり返す
        turnLeftUp(x, y);
        turnUp(x, y);
        turnRightUp(x, y);
        turnLeft(x, y);
        turnRight(x, y);
        turnLeftDown(x, y);
        turnDown(x, y);
        turnRightDown(x, y);
		board[x][y] = stone;
    }

	/** turnLeftUp
	 *
	 * 左上をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnLeftUp(int x, int y) {
		if (1 < x && 1 < y) {

			// となりの駒
			int next = board[x - 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || y - i < 0 || board[x - i] [y - i]== 0) {
						// 駒がない場合終了
						break;
					} else if (board[x - i][y - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x - t][y - t]= stone;

						}
						break;
					}
				}
			}
		}
	}

	/** turnUp
	 *
	 * 上をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnUp(int x, int y) {
		if (1 < y) {

			// となりの駒
			int next = board[x][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (y - i < 0 || board[x] [y - i]== EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x][y - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x] [y - t]= stone;
						}
						break;
					}
				}
			}

		}
	}

	/** turnRightUp
	 *
	 * 右上をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnRightUp(int x, int y) {
		if (x < 6 && 1 < y) {

			// となりの駒
			int next = board[x + 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || y - i < 0 || board[x + i][y - i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x + i][y - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x + t][y - t]= stone;
						}
						break;
					}
				}
			}

		}
	}

	/** turnLeft
	 *
	 * 左をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnLeft(int x, int y) {
		if (1 < x) {

			// となりの駒
			int next = board[x - 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || board[x - i][y]== EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x - i][y] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x - t][y] = stone;
						}
						break;
					}
				}
			}

		}
	}

	/** turnRight
	 *
	 * 右をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnRight(int x, int y) {
		if (x < 6) {

			// となりの駒
			int next = board[x + 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || board[x + i][y] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x + i][y] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x + t][y]= stone;
						}
						break;
					}
				}
			}
		}
	}

	/** turnLeftDown
	 *
	 * 左下をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnLeftDown(int x, int y) {
		if (1 < x && y < 6) {

			// となりの駒
			int next = board[x - 1][y + 1];
			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || 7 < y + i || board[x - i][y + i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x - i][y + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x - t][y + t] = stone;
						}
						break;
					}
				}
			}

		}
	}

	/** turnDown
	 *
	 * 下をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnDown(int x, int y) {
		if (y < 6) {

			// となりの駒
			int next = board[x][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < y + i || board[x][y + 1] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x][y + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x][y + t] = stone;
						}
						break;
					}
				}
			}

		}
	}

	/** turnRightDown
	 *
	 * 右下をひっくり返せるかの確認
	 * ひっくり返せる場合ひっくり返し
	 * @引数 x軸　y軸
	 */
	public void turnRightDown(int x, int y) {
		if (x < 6 && y < 6) {

			// となりの駒
			int next = board[x + 1][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || 7 < y + i || board[x + i][y + i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[x + i][y + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[x + t][y + t] = stone;
						}
						break;
					}
				}
			}

		}
	}
}
