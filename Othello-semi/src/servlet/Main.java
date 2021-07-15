package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Judg;
import model.logic;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Main() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html; charset=utf-8" );

		ServletContext application = request.getServletContext();
		String nobox = request.getParameter("no");
		int req = Integer.parseInt(nobox);
		logic othello = new logic();
		Judg judg = new Judg();


		// 対戦ボタンが押されたとき
		if(req == 1111) {

			boolean bool = false;
			//テストが成功した場合抜ける
				while(bool == false) {
						// 初期化
					othello.reset();
						// テスト
					bool = othello.test();
				}
			application.setAttribute("othello",othello);
		}
		else if(req == 500) {
			judg = (Judg) application.getAttribute("judg");
			judg.setPass(3);
	//		System.out.println("rep = 500");
		}
		else {
			// 代入されたインスタンスをもらう
			othello = (logic) application.getAttribute("othello");

			int a[] = new int[2];
			int x;
			int y;

			// syncで配列を同期
			a = othello.sync(req);
			// 縦軸
			y = a[0];
			// 横軸
			x = a[1];

			// ひっくり返しメソッド
			othello.turnStone(x, y);
			// 手番交代メソッド
			othello.change();

			application.setAttribute("othello",othello);
		}
		othello = (logic) application.getAttribute("othello");
		// ひっくり返した後の配列を代入

		int[][] yyy = othello.getBoard();
		int[][] xxx = new int[8][8];

		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				xxx[i][j] = yyy[i][j];
			}
		}
		judg.setCheck_board(xxx);

		// judgに石渡し
		judg.setStone(othello.getStone());
		judg.setRev_stone(othello.getRev_stone());

		// ひっくり返せるのか判定
		if(judg.getPass() != 3) {
			judg.check();
			judg.Procedure();
		}
	//	System.out.println(judg.getPass());
		if(judg.getPass() == 1) {

			int box = judg.getStone();
			judg.setStone(judg.getRev_stone());
			othello.setStone(judg.getRev_stone());
			judg.setRev_stone(box);
			othello.setRev_stone(judg.getRev_stone());

			judg.check();
			judg.Procedure();
		}

		// 配列をアプリケーションスコープに入れる
		application.setAttribute("judg",judg);

		RequestDispatcher dispatcher = request.getRequestDispatcher("Disp.jsp");
	    dispatcher.forward(request, response);
	}
}
