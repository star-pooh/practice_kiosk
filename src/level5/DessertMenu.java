package level5;

import java.util.ArrayList;
import java.util.List;

/** 디저트 메뉴 관리 클래스 */
public class DessertMenu {
  // 디저트 메뉴 리스트
  private final List<MenuItem> dessertMenuItemList;

  public DessertMenu() {
    this.dessertMenuItemList = new ArrayList<>();
  }

  /**
   * 디저트 메뉴 초기 설정
   *
   * @return 초기 값이 설정된 디저트 메뉴 리스트
   */
  public List<MenuItem> initDessertMenuList() {
    this.dessertMenuItemList.add(new MenuItem("Ice Cream", 0.8f, "아이스크림"));
    this.dessertMenuItemList.add(new MenuItem("Waffle", 2.0f, "와플"));
    this.dessertMenuItemList.add(new MenuItem("Apple Pie", 1.5f, "애플 파이"));

    return this.dessertMenuItemList;
  }
}
