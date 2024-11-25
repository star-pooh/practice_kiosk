package new_level2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** 메뉴 관리 및 사용자 입력 처리 클래스 */
public class Kiosk {
  // 전체 메뉴
  private final Menu menu;
  // 장바구니
  private final List<MenuItem> cartList = new ArrayList<>();
  // 메뉴 및 안내 문구 출력
  private final ConsolePrint cp = new ConsolePrint();

  public Kiosk(Menu menu) {
    this.menu = menu;
  }

  /** 키오스크 프로그램 실행 */
  public void start() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      // 메인 메뉴 출력
      this.cp.printMainMenu(this.menu.getCategoryList());
      // 장바구니에 담긴 메뉴가 있다면 주문 메뉴 출력
      if (!this.cartList.isEmpty()) {
        this.cp.printOrderMenu();
      }

      // 선택한 카테고리의 하위 메뉴 출력 또는 프로그램 종료
      System.out.print("원하시는 메뉴를 선택해주세요. : ");
      try {
        int menuNum = sc.nextInt();
        System.out.println();

        switch (menuNum) {
          case 0 -> System.exit(0);
          case 1 -> selectCategoryMenu(sc, "Burger", this.menu.getMenuItemList("Burger"));
          case 2 -> selectCategoryMenu(sc, "Drink", this.menu.getMenuItemList("Drink"));
          case 3 -> selectCategoryMenu(sc, "Dessert", this.menu.getMenuItemList("Dessert"));
          case 4 -> {
            if (this.cartList.isEmpty()) {
              this.cp.printNoMenuMessage();
            } else {
              // 장바구니에 담긴 메뉴가 있다면 주문 메뉴 표시
              confirmOrders(sc);
            }
          }
          case 5 -> {
            if (this.cartList.isEmpty()) {
              this.cp.printNoMenuMessage();
            } else {
              // 장바구니에 담긴 메뉴가 있다면 주문 취소 메뉴 표시
              cancelOrder(sc);
            }
          }
          default -> this.cp.printNoMenuMessage();
        }
      } catch (InputMismatchException e) {
        // 숫자 이외의 입력에 대한 예외 처리
        this.cp.printWrongInputMessage();
        sc.nextLine();
      }
    }
  }

  /**
   * 선택한 카테고리의 메뉴 출력
   *
   * @param sc 사용자 입력 클래스
   * @param category 카테고리
   * @param menuItemList 선택한 카테고리의 메뉴 리스트
   */
  public void selectCategoryMenu(Scanner sc, String category, List<MenuItem> menuItemList) {
    while (true) {
      // 선택한 카테고리의 메뉴 출력
      this.cp.printCategoryDetailMenu(category, menuItemList);

      int menuNum;
      try {
        menuNum = sc.nextInt();
      } catch (InputMismatchException e) {
        // 숫자 이외의 입력에 대한 예외 처리
        this.cp.printWrongInputMessage();
        sc.nextLine();
        continue;
      }

      // 0을 입력하면 메인 메뉴로 돌아가기
      if (menuNum == 0) {
        System.out.println();
        break;
      } else if (menuNum >= 1 && menuNum <= menuItemList.size()) {
        // 선택한 메뉴 정보 출력
        selectAddCart(sc, menuItemList.get(menuNum - 1));
      } else {
        this.cp.printNoMenuMessage();
      }
    }
  }

  /**
   * 선택한 메뉴 정보 출력 및 장바구니 추가 여부 확인
   *
   * @param sc 사용자 입력 클래스
   * @param menuItem 선택한 메뉴
   */
  private void selectAddCart(Scanner sc, MenuItem menuItem) {
    this.cp.printAddCart(menuItem);

    try {
      int menuNum = sc.nextInt();

      switch (menuNum) {
        case 1 -> {
          this.cartList.add(menuItem);
          System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.\n");
        }
        case 2 -> {}
        default -> {
          this.cp.printNoMenuMessage();
          selectAddCart(sc, menuItem);
        }
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      this.cp.printWrongInputMessage();
      sc.nextLine();
      selectAddCart(sc, menuItem);
    }
  }

  /**
   * 장바구니에 담은 메뉴들에 대한 주문 여부 확인
   *
   * @param sc 사용자 입력 클래스
   */
  private void confirmOrders(Scanner sc) {
    float sum = (float) cartList.stream().mapToDouble(MenuItem::getPrice).sum();
    // 장바구니에 담은 메뉴들의 정보 및 주문 여부 안내 문구 출력
    this.cp.printConfirmOrders(this.cartList, sum);

    try {
      int menuNum = sc.nextInt();

      switch (menuNum) {
        case 1 -> {
          System.out.println("주문이 완료되었습니다. 금액은 W " + discountOrderFee(sc, sum) + " 입니다.\n");
          cartList.clear();
        }
        case 2 -> {}
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      this.cp.printWrongInputMessage();
      sc.nextLine();
      confirmOrders(sc);
    }
  }

  /**
   * 신분에 따른 할인 적용
   *
   * @param sc 사용자 입력 클래스
   * @param sum 할인 적용 전 주문 최종 금액
   * @return 할인 적용 후 주문 최종 금액
   */
  private float discountOrderFee(Scanner sc, float sum) {
    // 할인 정보 출력
    this.cp.printDiscountInfo();

    float discountedOrderFee = 0.0f;
    try {
      int menuNum = sc.nextInt();

      switch (menuNum) {
        case 1, 2, 3, 4 -> {
          // 신분에 따른 할인 적용
          discountedOrderFee = CustomerType.getCustomerType(menuNum).discount(sum);
        }
        default -> {
          this.cp.printNoMenuMessage();
          discountOrderFee(sc, sum);
        }
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      this.cp.printWrongInputMessage();
      sc.nextLine();
      discountOrderFee(sc, sum);
    }
    return discountedOrderFee;
  }

  /**
   * 장바구니에 담은 주문 취소
   *
   * @param sc 사용자 입력 클래스
   */
  private void cancelOrder(Scanner sc) {
    // 주문 취소 안내 문구 출력
    this.cp.printCancelOrder(this.cartList);

    try {
      // 장바구니에 담은 메뉴 중 입력한 메뉴명과 같은 메뉴에 대한 주문 취소
      String menuName = sc.next();
      MenuItem targetMenuItem =
          this.cartList.stream()
              .filter(menuItem -> menuItem.getName().equals(menuName))
              .findFirst()
              .orElseThrow(NoSuchElementException::new);
      this.cartList.remove(targetMenuItem);
      // 주문 취소 및 계속 취소할 것인지에 대한 안내 문구 출력
      this.cp.printCancelContinue();

      int menuNum = sc.nextInt();
      switch (menuNum) {
        case 1 -> cancelOrder(sc);
        case 2 -> {}
        default -> {
          this.cp.printNoMenuMessage();
          cancelOrder(sc);
        }
      }
    } catch (InputMismatchException e) {
      // 숫자 이외의 입력에 대한 예외 처리
      this.cp.printWrongInputMessage();
      sc.nextLine();
      cancelOrder(sc);
    } catch (NoSuchElementException e) {
      // 장바구니에 없는 메뉴명 입력에 대한 예외 처리
      this.cp.printNoMenuMessage();
      sc.nextLine();
      cancelOrder(sc);
    }
  }
}
