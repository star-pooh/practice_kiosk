package level3;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** 키오스크 프로그램의 메뉴를 관리하고 사용자 입력 처리 */
public class Kiosk {
  // 메뉴 리스트
  List<MenuItem> menuItemList;

  /**
   * 메뉴 리스트에 초기값 설정
   *
   * @param menuItemList 메뉴 리스트 초기값
   */
  Kiosk(List<MenuItem> menuItemList) {
    this.menuItemList = menuItemList;
  }

  /** 키오스크 프로그램 실행 */
  public void start() {
    Scanner sc = new Scanner(System.in);

    while (true) {
      // 메뉴 화면 출력
      printMenu();

      // 선택한 메뉴 정보 출력 또는 프로그램 종료
      System.out.print("원하시는 메뉴를 선택해주세요. : ");

      try {
        int menuNum = sc.nextInt();
        selectMenu(menuNum);
      } catch (InputMismatchException e) {
        System.out.println("메뉴와 일치하는 숫자만 입력해주십시오.");
        sc.nextLine();
      }
    }
  }

  /** 메뉴 출력 */
  private void printMenu() {
    System.out.println("[ BURGERS MENU ]");
    for (int i = 0; i < this.menuItemList.size(); i++) {
      System.out.printf(
          "%d. %-12s | W %.1f | %s\n",
          i + 1,
          this.menuItemList.get(i).name,
          this.menuItemList.get(i).price,
          this.menuItemList.get(i).description);
    }
    System.out.println("0. 종료");
  }

  /**
   * 선택한 메뉴 정보 출력 또는 프로그램 종료
   *
   * @param menuNum 선택한 메뉴 번호
   */
  private void selectMenu(int menuNum) {
    if (menuNum == 0) {
      System.exit(0);
    } else if (menuNum >= 1 && menuNum <= 4) {
      System.out.printf(
          "선택한 메뉴 : %-12s | W %.1f | %s\n\n",
          menuItemList.get(menuNum - 1).name,
          menuItemList.get(menuNum - 1).price,
          menuItemList.get(menuNum - 1).description);
    } else {
      System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.");
    }
  }
}
