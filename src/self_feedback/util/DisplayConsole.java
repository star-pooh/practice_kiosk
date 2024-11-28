package self_feedback.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import self_feedback.dto.MenuItem;

/** 콘솔에서의 입출력을 관리하는 클래스 */
public class DisplayConsole {
  private final Scanner sc = new Scanner(System.in);

  /**
   * 사용자가 원하는 메뉴를 입력
   *
   * @return 선택하고 싶은 메뉴의 값
   */
  public int selectMenuNum() {
    System.out.print("원하시는 메뉴를 입력해주세요 : ");
    return Integer.parseInt(this.sc.nextLine());
  }

  /**
   * 사용자가 취소하고 싶은 메뉴명을 입력
   *
   * @return 취소하고 싶은 메뉴명
   */
  public String selectMenuName() {
    System.out.print("취소할 메뉴 이름을 입력해주세요 : ");
    return this.sc.nextLine();
  }

  /**
   * 메인 메뉴 출력
   *
   * @param categoryList 카테고리 리스트
   * @param hasCartItem 장바구니에 메뉴가 들었는지에 대한 여부
   */
  public void displayMainMenu(List<String> categoryList, boolean hasCartItem) {
    int idx = 1;
    System.out.println("[ MAIN MENU ]");
    for (String category : categoryList) {
      System.out.println(idx++ + ". " + category);
    }

    // 장바구니에 메뉴가 들었을 때만 출력
    if (hasCartItem) {
      System.out.println("[ ORDER MENU ]");
      System.out.println("4. Orders");
      System.out.println("5. Cancel");
    }
    System.out.println("0. 종료");
  }

  /**
   * 선택한 카테고리의 메뉴 리스트 출력
   *
   * @param category 카테고리
   * @param menuItemList 카테고리의 메뉴 리스트
   */
  public void displayMenuItemList(String category, List<MenuItem> menuItemList) {
    System.out.println("[ " + category.toUpperCase() + " MENU ]");

    int idx = 1;
    for (MenuItem menuItem : menuItemList) {
      System.out.printf(
          "%d. %-12s | W %.1f | %s\n",
          idx++, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }
    System.out.println("0. 뒤로 가기");
  }

  /**
   * 선택한 메뉴의 장바구니 추가 여부 안내 문구 출력
   *
   * @param menuItem 선택한 메뉴
   */
  public void displayAddCart(MenuItem menuItem) {
    System.out.printf(
        "선택한 메뉴 : %-12s | W %.1f | %s\n\n",
        menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 네");
    System.out.println("2. 아니요");
  }

  /**
   * 장바구니에 메뉴 추가 완료 안내 문구
   *
   * @param menuName 장바구니에 추가한 메뉴명
   */
  public void displayCompleteAddCart(String menuName) {
    System.out.println(menuName + " 이 장바구니에 추가되었습니다.\n");
  }

  /**
   * 주문 완료 안내 문구
   *
   * @param totalPrice 장바구니에 담겨 있던 모든 메뉴의 가격의 총합
   */
  public void displayCompleteOrder(float totalPrice) {
    System.out.println("주문이 완료되었습니다. 금액은 W " + totalPrice + " 입니다.\n");
  }

  /**
   * 장바구니에 담은 메뉴들의 정보 및 주문 여부 안내 문구 출력
   *
   * @param cartList 장바구니에 담은 메뉴 리스트
   * @param sum 장바구니에 담은 메뉴의 총 가격
   */
  public void displayConfirmOrders(List<MenuItem> cartList, float sum) {
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
  public void displayDiscountInfo() {
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
  public void displayCancelOrder(List<MenuItem> cartList) {
    System.out.println("주문 취소하고 싶은 메뉴명을 입력하세요.");
    System.out.println("[ Orders ]");
    cartList.forEach(
        menuItem ->
            System.out.printf(
                "%-12s | W %.1f | %s\n",
                menuItem.getName(), menuItem.getPrice(), menuItem.getDescription()));
  }

  /** 존재하지 않는 메뉴 입력에 대한 안내 문구 */
  public void displayNoMenuMessage() {
    System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
  }
}
