package self_feedback.util;

import java.util.Arrays;

/** 신분 관리 Enum */
public enum CustomerType {
  NATIONAL_MERIT("국가유공자", 1, 10) {
    @Override
    public float discount(float sum) {
      return sum * 0.9f;
    }
  },
  SOLDIER("군인", 2, 5) {
    @Override
    public float discount(float sum) {
      return sum * 0.95f;
    }
  },
  STUDENT("학생", 3, 3) {
    @Override
    public float discount(float sum) {
      return sum * 0.97f;
    }
  },
  ORDINARY("일반", 4, 0) {
    @Override
    public float discount(float sum) {
      return sum;
    }
  };

  // 신분
  private final String type;
  // 신분을 구분하기 위한 값
  private final int value;
  // 할인율
  private final int percent;

  CustomerType(String type, int value, int percent) {
    this.type = type;
    this.value = value;
    this.percent = percent;
  }

  /**
   * value에 해당하는 CustomerType 반환
   *
   * @param value value
   * @return value에 해당하는 CustomerType
   */
  public static CustomerType getCustomerType(int value) {
    return Arrays.stream(CustomerType.values()).filter(i -> i.value == value).findFirst().get();
  }

  public String getType() {
    return this.type;
  }

  public int getValue() {
    return this.value;
  }

  public int getPercent() {
    return this.percent;
  }

  public abstract float discount(float sum);
}
