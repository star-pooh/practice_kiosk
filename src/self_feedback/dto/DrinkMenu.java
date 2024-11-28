package self_feedback.dto;

import java.util.ArrayList;
import java.util.List;

/** 음료 메뉴 관리 클래스 */
public class DrinkMenu {
  private final List<MenuItem> drinkMenuItemList;

  public DrinkMenu() {
    this.drinkMenuItemList = new ArrayList<>();
  }

  /**
   * 음료 메뉴 초기 설정
   *
   * @return 초기 갑싱 설정된 음료 메뉴 리스트
   */
  public List<MenuItem> initDrinkMenuItem() {
    this.drinkMenuItemList.add(new MenuItem("Coke", 1.5f, "코카콜라"));
    this.drinkMenuItemList.add(new MenuItem("Zero Coke", 2.0f, "코카콜라 제로"));
    this.drinkMenuItemList.add(new MenuItem("Sprite", 2.0f, "스프라이트"));
    this.drinkMenuItemList.add(new MenuItem("Pepsi", 1.5f, "펩시"));
    this.drinkMenuItemList.add(new MenuItem("Zero Pepsi", 2.0f, "펩시 제로"));
    this.drinkMenuItemList.add(new MenuItem("Coffee", 1.0f, "커피"));

    return this.drinkMenuItemList;
  }
}
