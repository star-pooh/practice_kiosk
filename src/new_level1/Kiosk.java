package new_level1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** 메뉴 관리 및 사용자 입력 처리 클래스 */
public class Kiosk {
  // 전체 메뉴
  private final Menu menu;
  // 장바구니
  private final List<MenuItem> cartList = new ArrayList<>();

  public Kiosk(Menu menu) {
    this.menu = menu;
  }

  /** 키오스크 프로그램 실행 */
  public void start() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      // 메인 메뉴 출력
      printMainMenu();

      if (!cartList.isEmpty()) {
        printOrderMenu();
      }

      // 선택한 하위 메뉴 출력 또는 프로그램 종료
      System.out.print("원하시는 메뉴를 선택해주세요. : ");
      try {
        int menuNum = sc.nextInt();
        System.out.println();

        switch (menuNum) {
          case 0 -> System.exit(0);
          case 1 -> printSubMenu(sc, "Burger");
          case 2 -> printSubMenu(sc, "Drink");
          case 3 -> printSubMenu(sc, "Dessert");
          case 4 -> {
            if (cartList.isEmpty()) {
              System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
            } else {
              confirmOrders(sc);
            }
          }
          case 5 -> {
            if (cartList.isEmpty()) {
              System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
            } else {
              cartList.clear();
            }
          }
          default -> System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
        }
      } catch (InputMismatchException e) {
        // 숫자 이외의 입력에 대한 예외 처리
        System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.\n");
        sc.nextLine();
      }
    }
  }

  /** 메인 메뉴 출력 */
  private void printMainMenu() {
    int idx = 1;
    System.out.println("[ MAIN MENU ]");
    for (String category : this.menu.getCategoryList()) {
      System.out.println(idx++ + ". " + category);
    }
    System.out.println("0. 종료");
  }

  private void printOrderMenu() {
    System.out.println("[ ORDER MENU ]");
    System.out.println("4. Orders");
    System.out.println("5. Cancel");
  }

  /**
   * 선택한 카테고리의 하위 메뉴 출력
   *
   * @param sc 사용자 입력 클래스
   * @param category 카테고리
   */
  private void printSubMenu(Scanner sc, String category) {
    // 선택한 카테고리의 메뉴 리스트 획득
    List<MenuItem> subMenuItemList = this.menu.getMenuItemList(category);

    while (true) {
      System.out.println("[ " + category.toUpperCase() + " MENU ]");
      // 선택한 카테고리의 메뉴 리스트 출력
      printDetailMenu(subMenuItemList);
      System.out.println("0. 뒤로 가기");

      System.out.print("원하시는 메뉴를 선택해주세요. : ");
      int menuNum;
      try {
        menuNum = sc.nextInt();
      } catch (InputMismatchException e) {
        // 숫자 이외의 입력에 대한 예외 처리
        System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.\n");
        sc.nextLine();
        continue;
      }

      // 0을 입력하면 메인 메뉴로 돌아가기
      if (menuNum == 0) {
        System.out.println();
        break;
      } else if (menuNum >= 1 && menuNum <= subMenuItemList.size()) {
        // 선택한 메뉴 정보 출력
        selectAddCart(sc, subMenuItemList.get(menuNum - 1));

      } else {
        System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
      }
    }
  }

  /**
   * 선택한 카테고리의 메뉴 리스트 출력
   *
   * @param menuItemList 선택한 카테고리의 메뉴 리스트
   */
  private void printDetailMenu(List<MenuItem> menuItemList) {
    int idx = 1;

    for (MenuItem menuItem : menuItemList) {
      System.out.printf(
          "%d. %-12s | W %.1f | %s\n",
          idx++, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }
  }

  /**
   * 선택한 메뉴 정보 출력 및 장바구니 추가 여부 확인
   *
   * @param sc 사용자 입력 클래스
   * @param menuItem 선택한 메뉴 정보
   */
  private void selectAddCart(Scanner sc, MenuItem menuItem) {
    System.out.printf(
        "선택한 메뉴 : %-12s | W %.1f | %s\n\n",
        menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.println("1. 확인");
    System.out.println("2. 취소");

    int menuNum;
    try {
      menuNum = sc.nextInt();

      switch (menuNum) {
        case 1 -> {
          this.cartList.add(menuItem);
          System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.\n");
        }
        case 2 -> {}
        default -> {
          System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.\n");
          selectAddCart(sc, menuItem);
        }
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.\n");
      sc.nextLine();
      selectAddCart(sc, menuItem);
    }
  }

  private void confirmOrders(Scanner sc) {
    System.out.println("아래와 같이 주문하시겠습니까?");

    float sum = 0f;
    System.out.println("[ Orders ]");
    for (MenuItem menuItem : cartList) {
      sum += menuItem.getPrice();
      System.out.printf(
          "%-12s | W %.1f | %s\n",
          menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
    }

    System.out.println();
    System.out.println("[ Total ]");
    System.out.printf("W %.1f\n\n", sum);

    System.out.println("1. 주문");
    System.out.println("2. 메뉴판");

    int menuNum;
    try {
      menuNum = sc.nextInt();

      switch (menuNum) {
        case 1 -> {
          System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.\n\n", sum);
          cartList.clear();
        }
        case 2 -> {}
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.\n");
      sc.nextLine();
      confirmOrders(sc);
    }
  }
}
