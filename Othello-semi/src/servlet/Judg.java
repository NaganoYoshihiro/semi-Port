package servlet;

public class Judg {

	//オセロ版に対応した多次元配列

	protected int[][] check_board = new int[8][8];

	final int EMPTY = 0;
	final int BLACK = 1;
	final int WHITE = 2;

	private int stone = BLACK;
	private int rev_stone = WHITE;

	public void setCheck_board(int[][] check_board) {
		this.check_board = check_board;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public void setRev_stone(int rev_stone) {
		this.rev_stone = rev_stone;
	}

	public int[][] getCheck_board() {
		return check_board;
	}

	public int[] end(int[][] board) {
		//まだ空いている座標があるか
		boolean existempty = false;
		int cnt_black = 0;
		int cnt_white = 0;

		int[] stone = new int [2];

		for (int i = 0; i < board.length; i++ ) {
			for (int j = 0; j < board.length; j++) {
				//空いている座標があるか、各駒数の集計
				if (board[i][j] == EMPTY) {
					existempty = true;
				} else if (board[i][j] == BLACK) {
					cnt_black++;
				} else if (board[i][j] == WHITE) {
					cnt_white++;
				}
			}
		}
		stone[0] = cnt_black;
		stone[1] = cnt_white;
		System.out.println(stone[0] + ", " + stone[1] );
		return stone;
	}

	public void check() {
		for (int i = 0; i < check_board.length; i++) {
			for (int j = 0; j < check_board[i].length; j++) {
				if (check_board[j][i] == 3) {
					check_board[j][i] = EMPTY;
				}
			}
		}
		// WHITE判定
		for (int i = 0; i < check_board.length; i++) {
			for (int j = 0; j < check_board[i].length; j++) {
				if (check_board[j][i] == 0) {
					checkStone(j, i);
				}
			}
		}
	}

	public void checkStone(int x, int y) {
		// 8方向の駒の配置を確認する
		checkLeftUp(x, y);
		checkUp(x, y);
		checkRightUp(x, y);
		checkLeft(x, y);
		checkRight(x, y);
		checkLeftDown(x, y);
		checkDown(x, y);
		checkRightDown(x, y);

	}

	public void checkLeftUp(int x, int y) {
		if (1 < x && 1 < y) {

			// となりの駒
			int next = check_board[x - 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || y - i < 0 || check_board[x - i][y - i] == 0) {
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

	public void checkUp(int x, int y) {
		if (1 < y) {

			// となりの駒
			int next = check_board[x][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (y - i < 0 || check_board[x][y - i] == EMPTY) {
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

	public void checkRightUp(int x, int y) {
		if (x < 6 && 1 < y) {

			// となりの駒
			int next = check_board[x + 1][y - 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || y - i < 0 || check_board[x + i][y - i] == EMPTY) {
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

	public void checkLeft(int x, int y) {
		if (1 < x) {

			// となりの駒
			int next = check_board[x - 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || check_board[x - i][y] == EMPTY) {
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

	public void checkRight(int x, int y) {
		if (x < 6) {

			// となりの駒
			int next = check_board[x + 1][y];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {
				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {
					if (7 < x + i || check_board[x + i][y] == EMPTY) {
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

	public void checkLeftDown(int x, int y) {
		if (1 < x && y < 6) {

			// となりの駒
			int next = check_board[x - 1][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (x - i < 0 || 7 < y + i || check_board[x - i][y + i] == EMPTY) {
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

	public void checkDown(int x, int y) {
		if (y < 6) {

			// となりの駒
			int next = check_board[x][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < y + i || check_board[x][y + i] == EMPTY) {
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

	public void checkRightDown(int x, int y) {
		if (x < 6 && y < 6) {

			// となりの駒
			int next = check_board[x + 1][y + 1];

			// となりの駒が裏駒の場合
			if (next == rev_stone) {

				// さらにその一つとなりから順に確認
				for (int i = 2; true; i++) {

					if (7 < x + i || 7 < y + i || check_board[x + i][y + i] == EMPTY) {
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

}
