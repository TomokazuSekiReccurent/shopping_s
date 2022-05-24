package com.example.demo.Entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	// これは分からない
	private Map<Integer, Items> items = new HashMap<>();
	private int total;

	public Map<Integer, Items> getItems() {
		return items;
	}

	public int getTotal() {
		return total;
	}

	public Cart() {

	}

	/**
	 * アイテム情報をカートに追加
	 * 
	 * @param item
	 * @param quantity
	 */
	public void addCart(Items item, int stock) {
		Items existedItem = items.get(item.getCode());

		// アイテムが存在しない場合は追加
		if (existedItem == null) {
			// 数量を設定
			item.setStock(stock);
			// リストに追加
			items.put(item.getCode(), item);
		}
		// アイテムが存在する場合は追加しないけど、数量は追加
		else {
			existedItem.setStock(existedItem.getStock() + stock);
		}
		recalctotal();
	}
	/**
	 * カートの削除処理
	 * 
	 * @param itemCode
	 */
	public void deleteCart(int itemCode) {
		// item.codeを使用してカート配列から削除
		items.remove(itemCode);
		
		recalctotal();
	}
///**
// * カートの中身の総金額を算出する処理
// * 
// */
	public void recalctotal() {
		total=0;
		//th:each="変数:配列"
		for(Items items:items.values()) {
			//総金額=金額*数量
			total+=items.getPrice()*items.getStock();
		}
	}
}		

