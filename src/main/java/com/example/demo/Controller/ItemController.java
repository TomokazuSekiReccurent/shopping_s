package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Repository.CategoriesRepository;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class ItemController {

	@Autowired
	HttpSession session;

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	CategoriesRepository categoriesRepository;

	// 商品一覧を表示する(今はダミーデータ)
	@RequestMapping(value = "/showItem")
	public ModelAndView showItem(ModelAndView mv) {
		// データベースにデータを入れないとエラー出るかも
		// 商品一覧のデータを全てitemListに格納
		List<Items> itemList = itemsRepository.findAll(Sort.by(Direction.ASC, "code"));
		// itemListに格納したitemsrepositoryのデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", itemList);
		// itemsのデータをitems.htmlに偏移させる
		mv.setViewName("showItem");
		return mv;
	}

	// code1-田中 code2-無糖 code3-ストレート
	@RequestMapping(value = "/itemDetail/{code}")
	public ModelAndView itemsByCode(// この下に情報記載する
			@PathVariable(name = "code") int code, ModelAndView mv) {
		// itemコードをカテゴリコードとリンク、そしてカテゴリに入れる
		// mv.addObject("categories", CategoriesRepository.findAll());
		Items itemList = itemsRepository.findById(code).get();
		mv.addObject("items", itemList);
		mv.setViewName("itemDetail");
		return mv;
	}

//データベースに登録した商品を削除
	@RequestMapping("/deleteItem/{code}")
	public ModelAndView deleteItem(@PathVariable(name = "code") int code, // 主キーで特定するからこれは重要
			ModelAndView mv) {
		itemsRepository.deleteById(code);
		itemsRepository.flush();

		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", itemsRepository.findAll());
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("editItemConfirm");
		return mv;
	}

	// 検索機能の処理
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchtem(@RequestParam("name") String name, ModelAndView mv) {

		// データが1件以上取れた場合、登録しない
		if (name == null || name.length() == 0) {
			// 重複チェックはログイン画面に戻る
			mv.addObject("message", "商品がありません");
			mv.setViewName("showItem");
			return mv;
		}
		// アイテムのリストを出力する
		List<Items> itemList = itemsRepository.findByNameContains(name);
		// showItem.htmlに偏移

		// データが1件以上取れた場合、登録しない
		if (itemList.size() == 0) {
			// 重複チェックはログイン画面に戻る
			mv.addObject("message", "商品がありません");
			mv.setViewName("showItem");
			return mv;
		}

		mv.addObject("items", itemList);
		mv.setViewName("showItem");
		return mv;
	}

	// 商品追加の処理
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ModelAndView addItem(ModelAndView mv) {
		Items item = new Items();
		mv.addObject("item",item);
		mv.setViewName("addItem");
		return mv;
	}

	// 商品登録
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ModelAndView addItem(@RequestParam(name = "name") String name, // nameは商品名
			@RequestParam(name = "price", defaultValue = "0") Integer price,
			@RequestParam(name = "delivaryDays", defaultValue = "0") Integer delivaryDays,
			@RequestParam(name = "picture") String picture, ModelAndView mv) {

		// データベースに登録
		Items item = new Items(name, price, delivaryDays, picture);
		mv.addObject("item",item);

		if (name == null || name.length() == 0 && price == null || price <= 0 && delivaryDays == null
				|| delivaryDays <= 0 && picture == null || picture.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "情報を入力してください");
			mv.setViewName("addItem");
			return mv;
		}

		// 名前未入力チェック
		if (name == null || name.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "商品を入力してください");
			mv.setViewName("addItem");
			return mv;
		}
//			// 値段未入力
		if (price == null || price <= 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "値段を入力してください");
			mv.setViewName("addItem");
			return mv;
		}
		// 配達未入力
		if (delivaryDays == null || delivaryDays <= 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "配達日を入力してください");
			mv.setViewName("addItem");
			return mv;
		}
		// 名前未入力チェック
		if (picture == null || picture.length() == 0) {
			// 未入力はログイン画面に戻る
			mv.addObject("message", "画像を入力してください");
			mv.setViewName("addItem");
			return mv;
		}
		itemsRepository.saveAndFlush(item);

		mv.addObject("items", itemsRepository.findAll(Sort.by(Direction.ASC, "code")));

		mv.addObject("message", "商品登録が完了しました");
		mv.setViewName("editItemConfirm");

		return mv;
	}

	@RequestMapping(value = "/addItemConfirm", method = RequestMethod.GET)
	public ModelAndView addItemConfirm(ModelAndView mv) {

		// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
		mv.addObject("items", itemsRepository.findAll(Sort.by(Direction.ASC, "code")));
		// totalのデータをaddCart.htmlに偏移させる
		mv.setViewName("editItemConfirm");
		return mv;
	}

	// ユーザー情報の登録削除の処理
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo(ModelAndView mv) {
		return mv;
	}
	
	//ここを変更する(value = "/editCart/{code}"
		//出品商品の更新
		@RequestMapping(value = "/editItem/{code}")
		public ModelAndView editItem(
				@PathVariable(name = "code") int code, // 主キーで特定するからこれは重要
				ModelAndView mv) {

			Items item = itemsRepository.findById(code).get();

			// cartに格納したデータ(商品一覧)をitemsとあだ名をつける
			mv.addObject("item", item);

//				mv.addObject("code", code);
			mv.setViewName("editItem");
			return mv;
		}
		//ここの下を変更したvalue = "/editCart/{code}",method = RequestMethod.POST
		@RequestMapping(value = "/editItem/{code}",method = RequestMethod.POST)
		public ModelAndView editItem(
				@PathVariable("code") int code,
				@RequestParam(name = "name",defaultValue="") String name, // nameは商品名
				@RequestParam(name = "price",defaultValue="0") Integer price,
				@RequestParam(name = "delivaryDays",defaultValue="0") Integer delivaryDays,
				@RequestParam(name = "picture",defaultValue="") String picture,
				ModelAndView mv) {
			// 名前未入力チェック
			if (name == null || name.length() == 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "商品を入力してください");
				mv.addObject("code", code);
				mv.setViewName("editItem");
				return mv;
			}
//			// 値段未入力
			if (price == null || price <= 0) {
				// 未入力はログイン画面に戻る
				mv.addObject("message", "値段を入力してください");
				mv.setViewName("editItem");
				return mv;
			}
			// 配達未入力
					if (delivaryDays == null || delivaryDays <= 0) {
						// 未入力はログイン画面に戻る
						mv.addObject("message", "配達日を入力してください");
						mv.setViewName("editItem");
						return mv;
					}
					// 名前未入力チェック
					if (picture == null || picture.length() == 0) {
						// 未入力はログイン画面に戻る
						mv.addObject("message", "画像を入力してください");
						mv.setViewName("editItem");
						return mv;
					}		
			
			
			// データベースに登録
			Items item = new Items(name, price, delivaryDays, picture);
			itemsRepository.saveAndFlush(item);

			mv.addObject("items", itemsRepository.findAll(Sort.by(Direction.ASC, "code")));

			mv.addObject("message", "商品更新が完了しました");
			mv.setViewName("editItemConfirm");

			return mv;
		}
					
		

		// 更新画面に飛ばす
		@RequestMapping(value = "/editItemConfirm/{code}", method = RequestMethod.POST)
		public ModelAndView editItemConfirm(@PathVariable("code") int code, @RequestParam("name") String name,
				@RequestParam("price") int price, @RequestParam("delivaryDays") int delivaryDays,
				@RequestParam("picture") String picture, ModelAndView mv) {

			// DB更新処理
			// 現行のアイテム情報を取得
			Items item = itemsRepository.findById(code).get();

			// 変更したい項目をitemにセット
			item.setName(name);
			item.setPrice(price);
			item.setDelivaryDays(delivaryDays);
			item.setPicture(picture);

			// DBに保存する
//			Items items = new Items(name, price, delivaryDays, picture);
			//ここをコメントアウトしたら更新ひとつ
			itemsRepository.saveAndFlush(item);
			// Items itemList=itemsRepository.findById(code).get();
			mv.addObject("items", itemsRepository.findAll(Sort.by(Direction.ASC, "code")));

			// 商品一覧を画面に表示する

//				mv.addObject("item",item);
			mv.setViewName("editItemConfirm");
			return mv;
		}

	}
