package new_level2;

import java.util.Arrays;
import java.util.List;

public class ConsolePrint {

  /**
   * 메인 메뉴 출력
   *
   * @param categoryList 메뉴 카테고리 리스트
   */
  public void printMainMenu(List<String> categoryList) {
    int idx = 1;
    System.out.println("[ MAIN MENU ]");
    for (String category : categoryList) {
      System.out.println(idx++ + ". " + category);
    }
    System.out.println("0. 종료");
  }

  /** 주문 메뉴 출력 */
  public void printOrderMenu() {
    System.out.println("[ ORDER MENU ]");
    System.out.println("4. Orders");
    System.out.println("5. Cancel");
  }

  /**
   * 선택한 카테고리의 메뉴 출력
   *
   * @param category 카테고리
   * @param menuItemList 선택한 카테고리의 메뉴 리스트
   */
  public void printCategoryDetailMenu(String category, List<MenuItem> menuItemList) {
    System.out.println("[ " + category.toUpperCase() + " MENU ]");

    // 선택한 카테고리의 메뉴 리스트 출력
    int idx = 1;
    for (MenuItem menuItem : menuItemList) {
      System.out.printf(
          "%d. %-12s | W %.1f | %s\n",
          idx++, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }

    System.out.println("0. 뒤로 가기");
    System.out.print("원하시는 메뉴를 선택해주세요. : ");
  }

  /**
   * 선택한 메뉴의 장바구니 추가 여부 안내 문구 출력
   *
   * @param menuItem 선택한 메뉴
   */
  public void printAddCart(MenuItem menuItem) {
    System.out.printf(
        "선택한 메뉴 : %-12s | W %.1f | %s\n\n",
        menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 네");
    System.out.println("2. 아니요");
  }

  /**
   * 장바구니에 담은 메뉴들의 정보 및 주문 여부 안내 문구 출력
   *
   * @param cartList 장바구니에 담은 메뉴 리스트
   * @param sum 장바구니에 담은 메뉴의 총 가격
   */
  public void printConfirmOrders(List<MenuItem> cartList, float sum) {
    System.out.println("아래와 같이 주문하시겠습니까?");
    System.out.println("[ Orders ]");
    cartList.forEach(
        menuItem ->
            System.out.printf(
                "%-12s | W %.1f | %s\n",
                menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));

    System.out.println();
    System.out.println("[ Total ]");
    System.out.printf("W %.1f\n\n", sum);

    System.out.println("1. 주문");
    System.out.println("2. 메뉴판");
  }

  /** 할인 정보 출력 */
  public void printDiscountInfo() {
    System.out.println("할인 정보를 입력해주세요.");
    Arrays.stream(CustomerType.values())
        .forEach(
            customerType ->
                System.out.printf(
                    "%d. %-6s : %d%%\n",
                    customerType.getValue(), customerType.getType(), customerType.getPercent()));
  }

  /**
   * 주문 취소 안내 문구 출력
   *
   * @param cartList 장바구니에 담은 메뉴 리스트
   */
  public void printCancelOrder(List<MenuItem> cartList) {
    System.out.println("주문 취소하고 싶은 메뉴명을 입력하세요.");
    System.out.println("[ Orders ]");
    cartList.forEach(
        menuItem ->
            System.out.printf(
                "%-12s | W %.1f | %s\n",
                menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
  }

  /** 주문 취소 안내 문구 출력 */
  public void printCancelContinue() {
    System.out.println("주문 취소가 완료되었습니다. 계속 취소하시겠습니까?");
    System.out.println("1. 네");
    System.out.println("2. 아니요");
  }

  /** 존재하지 않는 메뉴 입력에 대한 안내 문구 */
  public void printNoMenuMessage() {
    System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
  }

  /** 잘못된 입력에 대한 안내 문구 */
  public void printWrongInputMessage() {
    System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.\n");
  }
}
