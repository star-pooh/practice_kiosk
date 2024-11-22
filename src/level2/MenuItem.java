package level2;

/** 세부 메뉴 속성 가지는 클래스 */
public class MenuItem {
  // 메뉴 이름
  String name;
  // 메뉴 가격
  float price;
  // 메뉴 설명
  String description;

  /**
   * MenuItem 생성자
   *
   * @param name 메뉴 이름
   * @param price 메뉴 가격
   * @param description 메뉴 설명
   */
  MenuItem(String name, float price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }
}
