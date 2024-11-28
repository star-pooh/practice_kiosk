package self_feedback.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import self_feedback.dto.Menu;
import self_feedback.dto.MenuItem;
import self_feedback.util.CustomerType;
import self_feedback.util.DisplayConsole;

public class KioskService {
  // 메뉴
  private final Menu menu;
  // 콘솔 입출력
  private final DisplayConsole dc;
  // 장바구니
  private final CartService cs;

  /**
   * 키오스크 생성자
   *
   * @param menu 모든 메뉴의 정보
   * @param dc 콘솔 입출력
   * @param cs 장바구니
   */
  public KioskService(Menu menu, DisplayConsole dc, CartService cs) {
    this.menu = menu;
    this.dc = dc;
    this.cs = cs;
  }

  /** 프로그램 시작 */
  public void start() {
    while (true) {
      try {
        // 메인 메뉴 선택 처리
        int selectCategoryNum = selectCategory();
        // 잘못된 입력, 장바구니 추가 취소, 주문 취소시 메인 메뉴 출력
        // 0을 입력한 경우 프로그램 종료
        if (selectCategoryNum == -1 || selectCategoryNum >= 4) {
          continue;
        } else if (selectCategoryNum == 0) {
          break;
        }

        // 선택한 메뉴 처리
        MenuItem selectMenuItem = selectMenuItem(selectCategoryNum);
        // 뒤로가기, 잘못된 입력일 경우 메인 메뉴 출력
        if (Objects.isNull(selectMenuItem)) {
          continue;
        }

        // 장바구니 처리
        selectAddCart(selectMenuItem);
      } catch (InputMismatchException e) {
        this.dc.displayNoMenuMessage();
      }
    }
  }

  /**
   * 메인 메뉴 선택 처리
   *
   * @return 선택한 메뉴의 값
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private int selectCategory() throws InputMismatchException {
    this.dc.displayMainMenu(this.menu.getCategoryList(), this.cs.hasCartItem());
    int menuNum = this.dc.selectMenuNum();
    System.out.println();

    switch (menuNum) {
      // 종료, 올바른 카테고리 선택시 입력한 메뉴의 값을 그대로 사용
      case 0, 1, 2, 3 -> {}
      case 4 -> {
        if (this.cs.hasCartItem()) {
          // 장바구니에 담긴 메뉴가 있다면 주문 처리 실행
          confirmOrders();
        } else {
          // 장바구니에 담긴 메뉴가 없다면 메인 메뉴를 출력하기 위해 메뉴의 값을 -1로 설정
          this.dc.displayNoMenuMessage();
          menuNum = -1;
        }
      }
      case 5 -> {
        if (this.cs.hasCartItem()) {
          // 장바구니에 담긴 메뉴가 있다면 주문 취소 처리 실행
          cancelOrder();
        } else {
          // 장바구니에 담긴 메뉴가 없다면 메인 메뉴를 출력하기 위해 메뉴의 값을 -1로 설정
          this.dc.displayNoMenuMessage();
          menuNum = -1;
        }
      }
      default -> {
        // 메뉴에 없는 값 입력시 메인 메뉴를 출력하기 위해 메뉴의 값을 -1로 설정
        this.dc.displayNoMenuMessage();
        menuNum = -1;
      }
    }
    return menuNum;
  }

  /**
   * 선택한 메뉴 처리
   *
   * @param categoryNum 선택한 카테고리의 값
   * @return 선택한 메뉴 아이템
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private MenuItem selectMenuItem(int categoryNum) throws InputMismatchException {
    // 선택한 카테고리 및 카테고리의 메뉴 리스트 획득
    String category = this.menu.getCategoryList().get(categoryNum - 1);
    List<MenuItem> menuItemList = this.menu.getMenuItemList(category);
    this.dc.displayMenuItemList(category, menuItemList);

    MenuItem menuItem = null;
    int menuNum = this.dc.selectMenuNum();
    System.out.println();

    if (menuNum == 0) {
      // 0을 입력한 경우, 뒤로 가기를 처리하기 위해 null 값을 그대로 사용
    } else if (menuNum > 0 && menuNum <= menuItemList.size()) {
      // 선택한 메뉴 아이템을 획득
      menuItem = menuItemList.get(menuNum - 1);
    } else {
      // 메인 메뉴로 이동하기 위해 null 값을 그대로 사용
      this.dc.displayNoMenuMessage();
    }
    return menuItem;
  }

  /**
   * 선택한 메뉴 아이템을 장바구니에 추가하기 위한 처리
   *
   * @param menuItem 선택한 메뉴 아이템
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private void selectAddCart(MenuItem menuItem) throws InputMismatchException {
    this.dc.displayAddCart(menuItem);
    int menuNum = this.dc.selectMenuNum();
    System.out.println();

    switch (menuNum) {
      case 1 -> {
        // 선택한 메뉴 아이템을 장바구니에 추가하고 안내 문구 출력
        this.cs.addCartItem(menuItem);
        this.dc.displayCompleteAddCart(menuItem.getName());
      }
      case 2 -> {
        // 메인 메뉴 출력을 위해 스킵
      }
      default -> throw new InputMismatchException();
    }
  }

  /**
   * 주문 진행을 위한 처리
   *
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private void confirmOrders() throws InputMismatchException {
    // 장바구니에 담긴 모든 메뉴의 가격의 총합
    float cartSum = this.cs.sumCartItemPrice();
    this.dc.displayConfirmOrders(this.cs.getCartList(), cartSum);
    int menuNum = this.dc.selectMenuNum();
    System.out.println();

    switch (menuNum) {
      case 1 -> {
        // 주문 완료 안내 문구 출력 및 장바구니 초기화
        this.dc.displayCompleteOrder(discountOrderFee(cartSum));
        this.cs.clearCartList();
      }
      case 2 -> {
        // 메인 메뉴 출력을 위해 스킵
      }
      default -> throw new InputMismatchException();
    }
  }

  /**
   * 주문 요금 할인 적용 처리
   *
   * @param sum 장바구니에 담긴 모든 메뉴의 가격의 총합
   * @return 할인 적용된 요금
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private float discountOrderFee(float sum) throws InputMismatchException {
    // 할인 정보 출력
    this.dc.displayDiscountInfo();
    int menuNum = this.dc.selectMenuNum();
    System.out.println();

    float discountedOrderFee;
    switch (menuNum) {
      case 1, 2, 3, 4 -> {
        // 신분에 맞는 요금 할인 적용
        discountedOrderFee = CustomerType.getCustomerType(menuNum).discount(sum);
      }
      default -> throw new InputMismatchException();
    }
    return discountedOrderFee;
  }

  /**
   * 주문 취소 처리
   *
   * @throws InputMismatchException 잘못된 입력에 대한 예외 처리
   */
  private void cancelOrder() throws InputMismatchException {
    // 선택한 메뉴에 대한 주문 취소 처리
    this.dc.displayCancelOrder(this.cs.getCartList());
    String menuName = this.dc.selectMenuName();
    this.cs.removeCartItem(menuName);
  }
}
