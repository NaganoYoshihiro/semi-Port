package servlet;

public class logic {

	private int board[][] = new int[8][8];

    final int EMPTY = 0;
    final int BLACK = 1;
    final int WHITE = 2;

	private int stone = BLACK;
	private int rev_stone = WHITE;

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int board[][]) {
		this.board = board;
	}

	public int getStone() {
		return stone;
	}

	public int getRev_stone() {
		return rev_stone;
	}

	public void Logic() {

		for( int x = 0; x < board.length ; x++ ){
			for ( int y = 0 ; y < board[x].length ; y++){
				board[x][y] = 0;
			}
		}
	}

	public  void reset() {
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
	}

	public  boolean test() {

		if(board[3][3] == 2 && board[3][4] == 1 && board[4][3] == 1 && board[4][4] == 2){
			for( int x = 0; x < board.length ; x++ ){
				for ( int y = 0 ; y < board[x].length ; y++){
					board[x][y] = 0;

					if ( x == 3 || x== 4){
						if(y == 3 || y ==4){
							continue;
						}
					}
					if(board[x][y] != 0){
						return false;
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}

	public void change() {
		int BOX = stone ;
		stone = rev_stone ;
		rev_stone = BOX ;

	}

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
		board[y][x] = stone;
    }

	public void turnLeftUp(int x, int y) {
		if (1 < x && 1 < y) {

			// となりの駒
			int next = board[y - 1][x - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || y - i < 0 || board[y - i][x - i] == 0) {
						// 駒がない場合終了
						break;
					} else if (board[y - i][x - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y - t][x - t] = stone;

						}
						break;
					}
				}
			}
		}
	}

	public void turnUp(int x, int y) {
		if (1 < y) {

			// となりの駒
			int next = board[y - 1][x];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (y - i < 0 || board[y - i][x] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y - i][x] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y - t][x] = stone;
						}
						break;
					}
				}
			}

		}
	}

	public void turnRightUp(int x, int y) {
		if (x < 6 && 1 < y) {

			// となりの駒
			int next = board[y - 1][x + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || y - i < 0 || board[y - i][x + i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y - i][x + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y - t][x + t] = stone;
						}
						break;
					}
				}
			}

		}
	}

	public void turnLeft(int x, int y) {
		if (1 < x) {

			// となりの駒
			int next = board[y][x - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || board[y][x - i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y][x - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y][x - t] = stone;
						}
						break;
					}
				}
			}

		}
	}

	public void turnRight(int x, int y) {
		if (x < 6) {

			// となりの駒
			int next = board[y][x + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || board[y][x + i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y][x + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y][x + t] = stone;
						}
						break;
					}
				}
			}
		}
	}

	public void turnLeftDown(int x, int y) {
		if (1 < x && y < 6) {

			// となりの駒
			int next = board[y + 1][x - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || 7 < y + i || board[y + i][x - i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y + i][x - i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y + t][x - t] = stone;
						}
						break;
					}
				}
			}

		}
	}

	public void turnDown(int x, int y) {
		if (y < 6) {

			// となりの駒
			int next = board[y + 1][x];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < y + i || board[y + i][x] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y + i][x] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y + t][x] = stone;
						}
						break;
					}
				}
			}

		}
	}

	public void turnRightDown(int x, int y) {
		if (x < 6 && y < 6) {

			// となりの駒
			int next = board[y + 1][x + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || 7 < y + i || board[y + i][x + i] == EMPTY) {
						// 駒がない場合終了
						break;
					} else if (board[y + i][x + i] == stone) {
						// 自駒の場合

						// あいだの駒をすべて自駒にひっくりかえす
						for (int t = 1; t < i; t++) {
							// 配列の要素を上書き
							board[y + t][x + t] = stone;
						}
						break;
					}
				}
			}

		}
	}
}
