### practice_kiosk

#### 사용법

각 패키지 내부의 Main.java 파일 실행

#### 각 패키지별 요구사항 및 파일 구조

#### level1

사용자 입력 값과 일치하는 햄버거 메뉴 출력하기

- 입력 처리와 간단한 흐림 제어 복습

> Main.java : 사용자 입력 값과 일치하는 햄버거 메뉴 출력

#### level2

사용자 입력 값과 일치하는 햄버거 메뉴 출력하기

- \+ level1
- 객체 지향 개념 및 프로그램 설계 방법 학습
- 데이터를 구조적으로 관리
- 햄버거 메뉴를 별도의 클래스로 관리

> Main.java : 사용자 입력 값과 일치하는 햄버거 메뉴 출력
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스

#### level3

사용자 입력 값과 일치하는 햄버거 메뉴 출력하기

- \+ level2
- 전체 순서 제어를 별도의 클래스로 관리

> Main.java : 햄버거 메뉴 및 키오스크 객체 생성, 프로그램 시작
>
> Kiosk.java : 사용자 입력 값과 일치하는 햄버거 메뉴 출력
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스

#### level4

사용자 입력 값과 일치하는 메뉴 출력하기(햄버거, 음료, 디저트)

- \+ level3
- 햄버거 메뉴만이 아닌 모든 메뉴를 별도의 클래스로 관리

> Main.java : 키오스크 객체 생성 및 프로그램 시작
>
> Kiosk.java : 사용자 입력 값과 일치하는 메뉴 출력
>
> Menu.java : 전체 메뉴를 관리하기 위한 클래스
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스
>
> BurgerMenu.java : 햄버거 메뉴 관리 클래스
>
> DrinkMenu.java : 음료 메뉴 관리 클래스
>
> DessertMenu.java : 디저트 메뉴 관리 클래스

#### level5

사용자 입력 값과 일치하는 메뉴 출력하기(햄버거, 음료, 디저트)

- \+ level4
- 메뉴와 순서를 제어하는 클래스들의 필드에 캡슐화 적용하기

> Main.java : 키오스크 객체 생성 및 프로그램 시작
>
> Kiosk.java : 사용자 입력 값과 일치하는 메뉴 출력
>
> Menu.java : 전체 메뉴를 관리하기 위한 클래스
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스
>
> BurgerMenu.java : 햄버거 메뉴 관리 클래스
>
> DrinkMenu.java : 음료 메뉴 관리 클래스
>
> DessertMenu.java : 디저트 메뉴 관리 클래스

#### new_level1

사용자 입력 값과 일치하는 메뉴 출력하기(햄버거, 음료, 디저트)
장바구니 및 결제 기능 추가

- \+ level5
- 클래스 간 연계를 통한 객체 지향 프로그래밍의 기본적인 설계 학습
- 사용자 입력에 따른 프로그램 흐름 제어와 상태 관리 학습

> Main.java : 키오스크 객체 생성 및 프로그램 시작
>
> Kiosk.java : 사용자 입력 값과 일치하는 메뉴 출력, 장바구니 및 결제 기능 담당
>
> Menu.java : 전체 메뉴를 관리하기 위한 클래스
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스
>
> BurgerMenu.java : 햄버거 메뉴 관리 클래스
>
> DrinkMenu.java : 음료 메뉴 관리 클래스
>
> DessertMenu.java : 디저트 메뉴 관리 클래스

#### new_level2

사용자 입력 값과 일치하는 메뉴 출력하기(햄버거, 음료, 디저트)
장바구니 및 결제 기능 추가
구매자 신분에 따른 할인 기능 추가

- \+ new_level1
- 고급 자바 기능(Enum, 람다 & 스트림)을 활용하여 프로그램의 효율성과 코드의 가독성 개선이 목표

> Main.java : 키오스크 객체 생성 및 프로그램 시작
>
> Kiosk.java : 사용자 입력 값과 일치하는 메뉴 출력, 장바구니 및 결제 기능 담당
>
> CustomerType.java : 구매자 신분 관리 및 결제 금액 할인 적용
>
> ConsolePrint.java : 콘솔 창에 출력되는 안내 문구 관리 클래스
>
> Menu.java : 전체 메뉴를 관리하기 위한 클래스
>
> MenuItem.java : 개별 메뉴를 관리하기 위한 클래스
>
> BurgerMenu.java : 햄버거 메뉴 관리 클래스
>
> DrinkMenu.java : 음료 메뉴 관리 클래스
>
> DessertMenu.java : 디저트 메뉴 관리 클래스

#### self_feedback

- new_level2와 같은 기능
- 적절한 리팩토링이 목표(기능에 맞는 코드 분리 및 패키지, 클래스 관리)

> > Main.java : 메뉴 / 콘솔 입출력 / 장바구니 객체 생성, 키오스크 객체 생성 및 객체 주입, 프로그램 시작
>
> service 패키지
> > KioskService.java : 사용자 입력에 따른 전체 흐름 관리
>>
> >CartService.java : 장바구니 관련 기능 처리 (추가, 삭제, 주문)
>
> util 패키지
> > DisplayConsole.java : 콘솔 입출력 관리 클래스
>>
>> CustomerType.java : 구매자 신분 관리 및 주문 요금 할인 관리
>
> dto 패키지
> > MenuItem.java : 개별 메뉴를 관리하기 위한 클래스
>>
> > BurgerMenu.java : 햄버거 메뉴 관리 클래스
>>
> > DrinkMenu.java : 음료 메뉴 관리 클래스
>>
> > DessertMenu.java : 디저트 메뉴 관리 클래스
>>
> > Menu.java : 전체 메뉴를 관리하기 위한 클래스

<br>

#### 만들면서 신경썼던 점

- 중복 코드 최대한 줄이기
- 하나의 파일에서 여러가지 기능을 수행하지 않도록 적절히 분리하기
- 기능 가이드에 있는 내용 최대한 구현하기
- 주석 빼먹지 않기