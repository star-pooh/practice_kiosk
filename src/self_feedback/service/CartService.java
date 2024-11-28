package self_feedback.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import self_feedback.dto.MenuItem;

/** 장바구니 관리 클래스 */
public class CartService {
  private final List<MenuItem> cartList = new ArrayList<>();

  /**
   * 장바구니에 메뉴가 들었는지 확인
   *
   * @return 메뉴가 들었으면 true / 빈 장바구니면 false
   */
  public boolean hasCartItem() {
    return !this.cartList.isEmpty();
  }

  /**
   * 장바구니에 선택한 메뉴 추가
   *
   * @param menuItem 선택한 메뉴
   */
  public void addCartItem(MenuItem menuItem) {
    this.cartList.add(menuItem);
  }

  /**
   * 장바구니에 담겨 있는 모든 메뉴의 가격의 합
   *
   * @return 장바구니에 담겨 있는 모든 메뉴의 가격의 합
   */
  public float sumCartItemPrice() {
    return (float) this.cartList.stream().mapToDouble(MenuItem::getPrice).sum();
  }

  /**
   * 장바구니에 담겨 있는 모든 메뉴 획득
   *
   * @return 장바구니에 담겨 있는 모든 메뉴 획득
   */
  public List<MenuItem> getCartList() {
    return this.cartList;
  }

  /** 장바구니 비우기 */
  public void clearCartList() {
    this.cartList.clear();
  }

  /**
   * 장바구니에 담긴 메뉴 중 선택한 메뉴명과 일치하는 메뉴 삭제
   *
   * @param menuName 선택한 메뉴명
   */
  public void removeCartItem(String menuName) {
    MenuItem targetMenuItem =
        this.cartList.stream()
            .filter(menuItem -> menuItem.getName().equals(menuName))
            .findFirst()
            .orElseThrow(InputMismatchException::new);
    this.cartList.remove(targetMenuItem);
  }
}
