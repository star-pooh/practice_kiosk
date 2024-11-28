package self_feedback.dto;

import java.util.ArrayList;
import java.util.List;

/** 버거 메뉴 관리 클래스 */
public class BurgerMenu {
  // 버거 메뉴 리스트
  private final List<MenuItem> burgerMenuItemList;

  public BurgerMenu() {
    this.burgerMenuItemList = new ArrayList<>();
  }

  /**
   * 버거 메뉴 초기 설정
   *
   * @return 초기 값이 설정된 버거 메뉴 리스트
   */
  public List<MenuItem> initBurgerMenuList() {
    this.burgerMenuItemList.add(new MenuItem("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
    this.burgerMenuItemList.add(new MenuItem("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
    this.burgerMenuItemList.add(new MenuItem("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
    this.burgerMenuItemList.add(new MenuItem("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));

    return this.burgerMenuItemList;
  }
}
