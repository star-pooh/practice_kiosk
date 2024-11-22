package level4;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** 메뉴 관리 및 사용자 입력 처리 클래스 */
public class Kiosk {
  // 전체 메뉴
  Menu menu;

  Kiosk(Menu menu) {
    this.menu = menu;
  }

  /** 키오스크 프로그램 실행 */
  public void start() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      // 메인 메뉴 출력
      printMainMenu();

      // 선택한 하위 메뉴 출력 또는 프로그램 종료
      System.out.print("원하시는 메뉴를 선택해주세요. : ");
      try {
        int menuNum = sc.nextInt();

        switch (menuNum) {
          case 0 -> System.exit(0);
          case 1 -> printSubMenu(sc, "Burger");
          case 2 -> printSubMenu(sc, "Drink");
          case 3 -> printSubMenu(sc, "Dessert");
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
        printSelectedMenu(subMenuItemList.get(menuNum - 1));
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
          "%d. %-12s | W %.1f | %s\n", idx++, menuItem.name, menuItem.price, menuItem.description);
    }
  }

  /**
   * 선택한 메뉴 정보 출력
   *
   * @param menuItem 선택한 메뉴
   */
  private void printSelectedMenu(MenuItem menuItem) {
    System.out.printf(
        "선택한 메뉴 : %-12s | W %.1f | %s\n\n", menuItem.name, menuItem.price, menuItem.description);
  }
}
