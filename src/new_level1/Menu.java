package new_level1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 전체 메뉴 관리 클래스 */
public class Menu {
  // 메뉴 정보 맵
  private final Map<String, List<MenuItem>> menuMap;

  public Menu() {
    this.menuMap = new HashMap<>();
    initMenuMap();
  }

  /** 전체 메뉴 초기 설정 */
  public void initMenuMap() {
    this.menuMap.put("Burger", new BurgerMenu().initBurgerMenuList());
    this.menuMap.put("Drink", new DrinkMenu().initDrinkMenuItem());
    this.menuMap.put("Dessert", new DessertMenu().initDessertMenuList());
  }

  /**
   * 메뉴 카테고리 획득
   *
   * @return 등록된 메뉴 카테고리 리스트
   */
  public List<String> getCategoryList() {
    return this.menuMap.keySet().stream().toList();
  }

  /**
   * 특정 카테고리의 메뉴 리스트 획득
   *
   * @param category 카테고리
   * @return 특정 카테고리의 메뉴 리스트
   */
  public List<MenuItem> getMenuItemList(String category) {
    return this.menuMap.get(category);
  }
}
