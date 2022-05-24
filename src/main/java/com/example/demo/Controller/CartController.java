package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Items;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class CartController {

	@Autowired
	HttpSession session;

	@Autowired
	ItemsRepository itemsRepository;

	// カートの中身を表示する(今はダミーデータ)
	// ここにaddCartのメソッドを書く/addCart/1,postにしたらエラー
	@RequestMapping(value = "/addCart/{code}")
	public ModelAndView addCart(@PathVariable(name = "code") int code, // 主キーで特定するからこれは重要
			@RequestParam(name = "stock", defaultValue = "1") int stock, ModelAndView mv) {
		// カートのデータを全てCartに格納
		Cart cart = getCartFromSession();
		// 主きーを取得してカートの情報を取得
		Items items = itemsRepository.findById(code).get();
		// カートに追加する
		cart.addCart(items, stock);

		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", cart.getItems());
		// cartの中身全部をtotalとする
		mv.addObject("total", cart.getTotal());
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("addCart");
		return mv;
	}

	// カートの中の商品を削除
	@RequestMapping("/deleteCart/{code}")
	public ModelAndView deleteCart(@PathVariable(name = "code") int code, // 主キーで特定するからこれは重要
			ModelAndView mv) {
		Cart cart = getCartFromSession();
		cart.deleteCart(code);


		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("item", itemsRepository.findAll(Sort.by(Direction.ASC, "code")));
		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", cart.getItems());
		// cartの中身全部をtotalとする
		mv.addObject("total", cart.getTotal());
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("addCart");
		return mv;
	}

	private Cart getCartFromSession() {
		// ここ分からない
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	// カートの中身を購入する処理
	@RequestMapping(value = "/purchaseCart")
	public ModelAndView purchaseCart(
			ModelAndView mv) {
		//ここの波線？？
		Users user = (Users) session.getAttribute("user");
		
		Cart cart = getCartFromSession();
		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", cart.getItems());
		// cartの中身全部をtotalとする
		mv.addObject("total", cart.getTotal());
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("purchaseCart");
		return mv;
	}

	// 購入表示画面
	@RequestMapping(value = "/ordered")
	public ModelAndView ordered(ModelAndView mv) {
		Cart cart = getCartFromSession();
		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", cart.getItems());
		// cartの中身全部をtotalとする
		mv.addObject("total", cart.getTotal());
		
		//書いて見るカート削除
		session.removeAttribute("cart");
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("ordered");
		return mv;
	}

}
