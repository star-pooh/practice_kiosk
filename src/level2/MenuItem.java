package level2;

/** 세부 메뉴 속성 가지는 클래스 */
public class MenuItem {
  String name;
  float price;
  String description;

  /**
   * MenuItem 생성자
   *
   * @param name 이름
   * @param price 가격
   * @param description 설명
   */
  MenuItem(String name, float price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }
}
