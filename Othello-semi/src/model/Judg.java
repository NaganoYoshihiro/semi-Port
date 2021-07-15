package model;

public class Judg{

    /*配列の中身　空の場合*/
	final int EMPTY = 0;
	/*配列の中身　黒の場合*/
    final int BLACK = 1;
    /*配列の中身　白の場合*/
    final int WHITE = 2;

	/*オセロ版に対応した多次元配列*/
    private int[][] check_board = new int[8][8];
	/*パス判定*/
    private int pass = 0;
    /*手番プレイヤー*/
	private int stone = BLACK;
	/*次のプレイヤー*/
	private int rev_stone = WHITE;

	/**
	 *
	 *
	 * @param pass
	 */
	public void setPass(int pass) {
		this.pass = pass;
	}

	/** getPass
	 *
	 * @戻り値 パス判定
	 */
    public int getPass() {
		return pass;
	}
    /** setCheck_board
     *
     * @param check_board
     */
	public void setCheck_board(int[][] check_board) {
		this.check_board = check_board;
	}

	/** setStone
	 *
	 * @param stone
	 */
	public void setStone(int stone) {
		this.stone = stone;
	}

	/** getStone
	 *
	 * @return
	 */
	public int getStone() {
		return stone;
	}

	/** setRev_stone
	 *
	 * @param rev_stone
	 */
	public void setRev_stone(int rev_stone) {
		this.rev_stone = rev_stone;
	}

	/** getRev_stone
	 *
	 * @return
	 */
	public int getRev_stone() {
		return rev_stone;
	}

	/** getCheck_board
	 *
	 * @return
	 */
	public int[][] getCheck_board() {
		return check_board;
	}

	/** check
	 *
	 * 配列の中身が０を探す
	 *
	 */
	public void check() {
		// WHITE判定
		for (int i = 0; i < check_board.length; i++) {
			for (int j = 0; j < check_board[i].length; j++) {
				if (check_board[j][i] == 0) {
					checkStone(j, i);
					//System.out.println(j+ "と" +i);
				}
			}
		}
	}

	/** checkStone
	 *
	 *  8方向の駒の配置を確認する
	 * @param x
	 * @param y
	 */
	public void checkStone(int x, int y) {

		checkLeftUp(x, y);
		checkUp(x, y);
		checkRightUp(x, y);
		checkLeft(x, y);
		checkRight(x, y);
		checkLeftDown(x, y);
		checkDown(x, y);
		checkRightDown(x, y);
	}

	/** checkLeftUp
	 *
	 * 左上のひっくり返しが可能なのか確認
	 *
	 * @param x
	 * @param y
	 */
	public void checkLeftUp(int x, int y) {
		if (1 < x && 1 < y) {
			// となりの駒
			int next = check_board[x - 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (x - i < 0 || y - i < 0 || check_board[x - i][y - i] == 0  || check_board[x - i][y - i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x - i][y - i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkUp
	 *
	 *上のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkUp(int x, int y) {
		if (1 < y) {
			// となりの駒
			int next = check_board[x][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (y - i < 0 || check_board[x][y - i] == EMPTY || check_board[x][y - i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x][y - i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkRightUp
	 *
	 *右上のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkRightUp(int x, int y) {
		if (x < 6 && 1 < y) {
			// となりの駒
			int next = check_board[x + 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || y - i < 0 || check_board[x + i][y - i] == EMPTY ||check_board[x + i][y - i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x + i][y - i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkLeft
	 *
	 * 左のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkLeft(int x, int y) {
		if (1 < x) {
			// となりの駒
			int next = check_board[x - 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (x - i < 0 || check_board[x - i][y] == EMPTY || check_board[x - i][y] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x - i][y] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkRight
	 *
	 * 右のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkRight(int x, int y) {
		if (x < 6) {
			// となりの駒
			int next = check_board[x + 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || check_board[x + i][y] == EMPTY || check_board[x + i][y] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x + i][y] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkLeftDown
	 *
	 * 左下のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkLeftDown(int x, int y) {
		if (1 < x && y < 6) {
			// となりの駒
			int next = check_board[x - 1][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (x - i < 0 || 7 < y + i || check_board[x - i][y + i] == EMPTY || check_board[x - i][y + i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x - i][y + i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkDown
	 *
	 * 下のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkDown(int x, int y) {
		if (y < 6) {
			// となりの駒
			int next = check_board[x][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < y + i || check_board[x][y + i] == EMPTY || check_board[x][y + i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x][y + i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	/** checkRightDown
	 *
	 * 右下のひっくり返しが可能なのか確認
	 * @param x
	 * @param y
	 */
	public void checkRightDown(int x, int y) {
		if (x < 6 && y < 6) {
			// となりの駒
			int next = check_board[x + 1][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || 7 < y + i || check_board[x + i][y + i] == EMPTY || check_board[x + i][y + i] == 3) {
						// 駒がない場合終了
						break;
					} else if (check_board[x + i][y + i] == stone) {
						// 自駒の場合
						// 配列の要素を上書き
						check_board[x][y] = 3;
						break;
					}
				}
			}
		}
	}

	public  void Procedure() {

		int count = 0 ;

		for(int i = 0; i < check_board.length;i++){
			for(int j = 0; j < check_board[i].length; j++){
				if(check_board[i][j] ==0){
					count++ ;
				}
			}
		}
		if(count >=1){
			Procedure2();
		}
		else {
			pass = 2 ;
		}
	}

	public  void Procedure2() {

		int count = 0 ;
		for(int i = 0; i < check_board.length;i++){
			for(int j = 0; j < check_board[i].length; j++){
				if(check_board[i][j] == 3){
					count++ ;
					// System.out.println(i+"と"+j+"と"+count);
				}else {
				}
			}
		}
		if(count == 0){
			pass++ ;

		}else {
			pass = 0;
		}
	}

	public int CHblack() {
		int count = 0;
		for(int i = 0; i < check_board.length;i++){
			for(int j = 0; j < check_board[i].length; j++){
				// check_board[i][j] ;
				if( check_board[i][j] == 1){
					count++;
				}else{
					// 空白
				}
			}
		}
		return count;
	}
	public int CHwhite() {
		int count = 0;
		for(int i = 0; i < check_board.length;i++){
			for(int j = 0; j < check_board[i].length; j++){
				// check_board[i][j] ;
				if( check_board[i][j] == 2){
					count++;
				}else{
					// 空白
				}
			}
		}
		return count;

	}
}
