package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.ItemsRepository;
import com.example.demo.Repository.UsersRepository;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ItemsRepository itemsRepository;

	// トップページ表示(top.html)
	@RequestMapping("/")
	public String login() {
		// 削除のやつを入れる
//		session.invalidate();
		// top.htmlに移動する
		return "top";
	}
	
	//menu画面に戻れる処理を書く
	@RequestMapping("/loginAfter")
	public String loginAfter() {
		// 削除のやつを入れる
//		session.invalidate();
		// top.htmlに移動する
		return "loginAfter";
	}
	


	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "top";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv) {
		return mv;
	}

	// ログイン実行
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(name = "name") String name, @RequestParam(name = "pass") String pass,
			ModelAndView mv) {

		// 名前未入力チェック
		if (name == null || name.length() == 0) {
			// 未入力はログイン画面(login.html)に戻る
			mv.addObject("message", "名前を入力してください");
			mv.setViewName("login");
			return mv;
		}
		// パスワード未入力
		if (pass == null || pass.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "パスワードを入力してください");
			// 未入力はログイン画面(login.html)に戻る
			mv.setViewName("login");
			return mv;
		}
		Users users = usersRepository.findByNameAndPass(name, pass);
		if (users == null) {
			mv.addObject("message,", "名前またはパスワードが違います");
			mv.setViewName("login");
		} else {//
			mv.addObject("users", users);

			session.setAttribute("user", users);

			mv.setViewName("loginAfter");
		}

		// ログイン押した後のページに移動する(loginAfter)
//	mv.setViewName("loginAfter");

		return mv;

	}

	// newAcountの情報を記載する
	@RequestMapping(value = "/newAccount", method = RequestMethod.GET)
	public ModelAndView newAcount(ModelAndView mv) {
		return mv;
	}

	// ユーザー登録
	@RequestMapping(value = "/newAccount", method = RequestMethod.POST)
	public ModelAndView newAcount(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email, @RequestParam(name = "pass") String pass, ModelAndView mv) {

		// 名前未入力チェック
		if (name == null || name.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "名前を入力してください");
			mv.setViewName("newAccount");
			return mv;
		}
		// パスワード未入力
		if (address == null || address.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "住所を入力してください");
			mv.setViewName("newAccount");
			return mv;
		}
		// パスワード未入力
		if (tel == null || tel.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "電話番号を入力してください");
			mv.setViewName("newAccount");
			return mv;
		}
		// パスワード未入力
		if (email == null || email.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "メールアドレスを入力してください");
			mv.setViewName("newAccount");
			return mv;
		}
		// パスワード未入力
		if (pass == null || pass.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "パスワードを入力してください");
			mv.setViewName("newAccount");
			return mv;
		}

		// 重複チェック
		List<Users> existedUser = usersRepository.findByName(name);

		// データが1件以上取れた場合、登録しない
		if (existedUser.size() > 0) {
			// 重複チェックはログイン画面に戻る
			mv.addObject("message", "同じ名前のユーザーが既に存在します");
			mv.setViewName("newAccount");
			return mv;
		}

//		mv.addObject("message", "情報を確定しますか?");
//		mv.setViewName("newAccount");
		// データベースに登録
		Users users = new Users(name, address, tel, email, pass);
		// ここで新規登録の情報をデータベースに登録している
		usersRepository.saveAndFlush(users);

		mv.addObject("users", usersRepository.findAll());

		mv.setViewName("login");

		return mv;
	}



// ユーザー情報
	@RequestMapping(value = "/userInfo", method = RequestMethod.POST)
	public ModelAndView userInfo(@RequestParam(name = "name") String name, // nameは商品名
			@RequestParam(name = "address", defaultValue = "0") String address,
			@RequestParam(name = "tel", defaultValue = "0") String tel, @RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass, ModelAndView mv) {

		if (name == null || name.length() == 0 && address == null || address.length() == 0 && tel == null
				|| tel.length() == 0 && email == null || email.length() == 0 && pass == null || pass.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "情報を入力してください");
			mv.setViewName("userInfo");
			return mv;
		}

		// 名前未入力チェック
		if (name == null || name.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "名前を入力してください");
			mv.setViewName("userInfo");
			return mv;
		}
//		// 住所未入力
		if (address == null || address.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "住所を入力してください");
			mv.setViewName("userInfo");
			return mv;
		}
		// 電話番号未入力
		if (tel == null || tel.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "電話番号を入力してください");
			mv.setViewName("userInfo");
			return mv;
		}
		// メールアドレス入力チェック
		if (email == null || email.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "メールアドレスを入力してください");
			mv.setViewName("userInfo");
			return mv;
		}
		// パスワード入力チェック
		if (pass == null || pass.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "パスワードを入力してください");
			mv.setViewName("userInfo");
			return mv;
		}

		// データベースに登録
		Users user = new Users(name, address, tel, email, pass);
//		itemsRepository.saveAndFlush(user);

		mv.addObject("user", usersRepository.findAll());

		mv.addObject("message", "ユーザー登録が完了しました");
		mv.setViewName("userInfo");

		return mv;
	}

	// ユーザー情報の更新画面へ遷移
	@RequestMapping(value = "/userInfoConfirm")
	public ModelAndView userInfoConfirm(ModelAndView mv) {
		mv.setViewName("userInfoConfirm");
		return mv;
	}

	// ユーザー情報の更新
	@RequestMapping(value = "/userInfoConfirm", method = RequestMethod.POST)
	public ModelAndView userInfoConfirm(@RequestParam(name = "name") String name, // nameは商品名
			@RequestParam(name = "address", defaultValue = "0") String address,
			@RequestParam(name = "tel", defaultValue = "0") String tel, @RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass, ModelAndView mv) {
		Users users = (Users) session.getAttribute("user");

		// 変更したい項目をitemにセット
		users.setName(name);
		users.setAddress(address);
		users.setTel(tel);
		users.setEmail(email);
		users.setPass(pass);

		// DBに保存する
//				Items items = new Items(name, price, delivaryDays, picture);
		// ここをコメントアウトしたら更新ひとつ
		usersRepository.saveAndFlush(users);
		// Items itemList=itemsRepository.findById(code).get();
		mv.addObject("users", usersRepository.findAll());

		// 商品一覧を画面に表示する

//					mv.addObject("item",item);
		mv.setViewName("userInfoConfirm");
		return mv;
	}
}
