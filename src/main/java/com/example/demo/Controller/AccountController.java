package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Users;
import com.example.demo.Entity.UsersRepository;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;
	
	@Autowired
	UsersRepository usersRepository;

	// トップページ表示(top.html)
	@RequestMapping("/")
	public String login() {
		//削除のやつを入れる
		session.invalidate();
		return "top";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv) {
		return mv;
	}
	
	// ログイン実行	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(name = "name") String name, 
			@RequestParam(name = "pass") String pass,
			ModelAndView mv) {

		//名前かパスワードが違う場合
		
		 
		
		// 名前未入力チェック
		if (name == null || name.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "名前を入力してください");
			mv.setViewName("login");
			return mv;
		}
		//パスワード未入力
		if (pass == null || pass.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "パスワードを入力してください");
			mv.setViewName("login");
			return mv;
		}
		Users users = usersRepository.findByNameAndPass(name, pass);
		if (users == null) {
			mv.addObject("message,", "名前またはパスワードが違います");
			mv.setViewName("login");
		} else {
			mv.addObject("users", users);
			
			mv.setViewName("loginAfter");
		}
//	session.setAttribute("name", name);

	//ログイン押した後のページに移動する(loginAfter)
//	mv.setViewName("loginAfter");

	return mv;

	}
	
		//newAcountの情報を記載する
		@RequestMapping(value = "/newAccount", method = RequestMethod.GET)
		public ModelAndView newAcount(ModelAndView mv) {
			return mv;
		}
		
		// ログイン実行	
		@RequestMapping(value = "/newAccount", method = RequestMethod.POST)
		public ModelAndView newAcount(
				@RequestParam(name = "name") String name, 
				@RequestParam(name = "address") String address,
				@RequestParam(name = "tel") String tel, 
				@RequestParam(name = "email") String email,
				@RequestParam(name = "pass") String pass, 
				ModelAndView mv) {

			// 名前未入力チェック
			if (name == null || name.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "名前を入力してください");
				mv.setViewName("newAccount");
				return mv;
			}
			//パスワード未入力
			if (address == null || address.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "住所を入力してください");
				mv.setViewName("newAccount");
				return mv;
			}
			//パスワード未入力
			if (tel == null || tel.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "電話番号を入力してください");
				mv.setViewName("newAccount");
				return mv;
			}
			//パスワード未入力
			if (email == null || email.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "メールアドレスを入力してください");
				mv.setViewName("newAccount");
				return mv;
			}
			//パスワード未入力
			if (pass == null || pass.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "パスワードを入力してください");
				mv.setViewName("newAccount");
				return mv;
			}
			
			//データベースに登録
			Users users=new Users();
			usersRepository.saveAndFlush(users);
			
			mv.addObject("users",usersRepository.findAll());
//			mv.addObject("items",itemRepository.findAll());
			
		
		// 入力されている場合はメインの場面に移動する
		// カテゴリ一覧と名前の設定
//		mv.addObject("user", urserRepository.findAll());

		// 名前をセッションに入れる@Autowired HttpSession session;これでデータを取得.
		// セッションはログインしてから値を保持すること。
		// ログイン中の名前はデーターベースに入れないので、誰がグインしていますっていうためにセッションに一時的に入れる
//		session.setAttribute("name", name);

		//ログイン押した後のページに移動する
		mv.setViewName("loginAfter");

		return mv;
	}

}
