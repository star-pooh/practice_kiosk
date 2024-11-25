package new_level2;

/** 세부 메뉴 속성 가지는 클래스 */
public class MenuItem {
  // 메뉴 이름
  private final String name;
  // 메뉴 가격
  private final float price;
  // 메뉴 설명
  private final String description;

  /**
   * MenuItem 생성자
   *
   * @param name 메뉴 이름
   * @param price 메뉴 가격
   * @param description 메뉴 설명
   */
  public MenuItem(String name, float price, String description) {
    this.name = name;
    this.price = price;
    this.description = description;
  }

  /**
   * 메뉴 이름 획득
   *
   * @return 메뉴 이름
   */
  public String getName() {
    return name;
  }

  /**
   * 메뉴 가격 획득
   *
   * @return 메뉴 가격
   */
  public float getPrice() {
    return price;
  }

  /**
   * 메뉴 설명 획득
   *
   * @return 메뉴 설명
   */
  public String getDescription() {
    return description;
  }
}
