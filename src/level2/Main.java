package level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    // MenuItem 생성 및 등록
    List<MenuItem> menuItemList = new ArrayList<>();
    menuItemList.add(new MenuItem("ShackBurger", 6.9f, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
    menuItemList.add(new MenuItem("SmokeShack", 8.9f, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
    menuItemList.add(new MenuItem("Cheeseburger", 6.9f, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
    menuItemList.add(new MenuItem("Hamburger", 5.4f, "비프패티를 기반으로 야채가 들어간 기본버거"));

    Scanner sc = new Scanner(System.in);
    while (true) {
      // 초기 메뉴 화면 출력
      System.out.println("[ BURGERS MENU ]");
      for (int i = 0; i < menuItemList.size(); i++) {
        System.out.printf(
            "%d. %-12s\t|\tW %.1f\t|\t%s\n",
            i + 1,
            menuItemList.get(i).name,
            menuItemList.get(i).price,
            menuItemList.get(i).description);
      }
      System.out.println("0. 종료");

      // 선택한 메뉴 정보 출력 또는 프로그램 종료
      System.out.print("원하시는 메뉴를 선택해주세요. : ");
      int menuNum = sc.nextInt();
      if (menuNum == 0) {
        System.exit(0);
      } else if (menuNum >= 1 && menuNum <= 4) {
        System.out.printf("선택하신 메뉴는 %s 입니다.\n\n", menuItemList.get(menuNum - 1).name);
      } else {
        System.out.println("존재하지 않는 메뉴입니다. 다시 선택해주세요.");
      }
    }
  }
}
