//package com.example.demo.Controller;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpSession;
//
//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.demo.OrderDetail;
//import com.example.demo.Entity.Cart;
//import com.example.demo.Entity.Users;
//import com.example.demo.Repository.OrderdetailRepository;
//import com.example.demo.Repository.OrderedRepository;
//
//@Controller
//public class OrderController {
//	
//	@Autowired
//	HttpSession session;
//	
//	
//	@Autowired
//	OrderedRepository orderedRepository;
//	
//	@Autowired
//	OrderdetailRepository orderdetailRepository;
//
//	//お客様情報入力画面と注文内容確認
//	@RequestMapping("/order")
//	public ModelAndView getCart(ModelAndView mv) {
//		Cart cart = getCart();
//
//		// 表示に必要な情報を設定
//		mv.addObject("items", cart.getItems());
//		mv.addObject("total", cart.getTotal());
//		
//
//		// お客様情報入力画面
//		mv.setViewName("customerInfo");
//
//		return mv;
//	}
//
//	
//	@RequestMapping(value = "/order/confirm", method = RequestMethod.POST)
//	public ModelAndView orderConfirm(
//			@RequestParam(name = "name") String name,
//			@RequestParam(name = "adress") String adress, 
//			@RequestParam(name = "tel") String tel,
//			@RequestParam(name = "email") String email, 
//			ModelAndView mv) {
//
//		// 未入力チェックバリデーションチェック
//		if (isNull(name) || isNull(adress) || isNull(tel) || isNull(email)) {
//			// チェックエラーのばいいのめっせいじ
//			mv.addObject("message", "未入力の項目あります");
//
//			// エラーの場合は画面そのまま
//			mv.setViewName("customerInfo");
//		} else {
//			// セッションにお客様情報を登録
//			// 1該当のクラス変数にする
//			Users users = new Users(name, adress, tel, email);
//			// セッションに登録
//			session.setAttribute("usersInfo", users);
//
//			Cart cart =getCartFromSession();
//			mv.addObject("items", cart.getItems());
//			// 確認画面に移動
//			mv.addObject("total",cart.getTotal());
//			mv.setViewName("confirm");
//			//mv.addObject("categories", categoryRepository.findAll());
//			return mv;
//		}
//	}
///*
// * //注文の処理、 // <form action="/order/doOrder" method="post">
// * 
// * @RequestMapping(value = "/order/doOrder", method = RequestMethod.POST) public
// * ModelAndView doOrder(ModelAndView mv) {
// * 
// * //カートの情報を取得 //Cart cart=getCart();
// * 
// * // お客様情報の登録:customerへの登録 Users users = (Users)
// * session.getAttribute("UsersInfo"); //saveAndFlushはデータベースに登録更新「 int Users_code
// * = UsersRepository.saveAndFlush(users).getCode();
// * 
// * // オーダー情報の登録:orderへの登録 Order order=new Order(Users_code,new
// * Date(),cart.getTotal()); int
// * order_code=orderedRepository.saveAndFlush(order).getCode();
// * 
// * // オーダー詳細情報への登録:OrderDetailへの登録 for(Item item:cart.getItem().values()) {
// * OrderDetail orderdetail=new
// * OrderDetail(order_code,item.getCode(),item.getQuantity());
// * orderDeOrderedDetailRepository.saveAndFlush(orderdetail);
// * 
// * } //表示したい画面を設定 mv.setViewName("ordered"); return mv; }
// * 
// * public boolean isNull(String text) { // text==null,text.length()==0のときtrue
// * return (text == null || text.length() == 0); }
// * 
// * private Cart getCartFromSession() { Cart
// * cart=(Cart)session.getAttribute("cart"); if(cart==null) { cart=new Cart();
// * session.setAttribute("cart", cart); } return cart; }
// * 
// * 
// * public Cart getCart() { // セッションのカート情報を取得 Cart cart = (Cart)
// * session.getAttribute("cart");
// * 
// * // カート情報がない時、カート情報の初期処理 if (cart == null) { cart = new Cart();
// * session.setAttribute("cart", cart); } return cart; } }
// */
//	
//
