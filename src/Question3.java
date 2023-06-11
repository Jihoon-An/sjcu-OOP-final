import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question3 {
    private static List<Customer> customerList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static String scanf(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        mainLoop:
        while (true) {
            Integer option = Integer.valueOf(scanf("""
                    1. 추가
                    2. 삭제
                    3. 고개 리스트 출력
                    4. 수정
                    5. 프로그램 종료
                    """));

            switch (option) {
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    get();
                    break;
                case 4:
                    set();
                    break;
                case 5:
                    break mainLoop;
            }
        }
    }

    private static void add() {
        System.out.println("새로운 Customer의 정보를 입력하세요.");
        Customer newCustomer = new Customer();

        newCustomer.setNo(customerList.size() > 0 ? customerList.get(customerList.size() - 1).getNo() + 1 : 1);
        newCustomer.setName(scanf("고객명: "));
        newCustomer.setAddress(scanf("주소: "));
        newCustomer.setPhoneNumber(scanf("연락처: "));

        customerList.add(newCustomer);
        System.out.println("추가되었습니다.\n");
    }

    private static void remove() {
        int removeNum = Integer.parseInt(scanf("제거할 고객의 번호를 입력하세요: "));

        boolean success = false;
        for (Customer customer : customerList) {
            if (customer.getNo() == removeNum) {
                customerList.remove(customer);
                success = true;
                System.out.println("제거되었습니다.");
            }
        }
        if (!success) {
            System.out.println("고객 번호가 유효하지 않습니다.");
        }
    }

    private static void get() {
        System.out.println("고객 번호        고객명           주소            연락처");

        if (customerList.size() == 0) {
            System.out.println("저장된 고객이 없습니다.");
        }

        for (Customer customer : customerList) {
            System.out.println(
                    customer.getNo() + "   "
                            + customer.getName() + "    "
                            + customer.getAddress() + "    "
                            + customer.getPhoneNumber());
        }
    }

    private static void set() {
        int num = Integer.parseInt(scanf("수정할 고객의 번호를 입력하세요: "));

        for (Customer customer : customerList) {
            if (customer.getNo() == num) {
                System.out.println("수정할 Customer의 정보를 입력하세요.");

                customer.setName(scanf("고객명: "));
                customer.setAddress(scanf("주소: "));
                customer.setPhoneNumber(scanf("연락처: "));
                break;
            }
        }
    }
}

class Customer {
    private Integer no;
    private String name;
    private String address;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(Integer no, String name, String address, String phoneNumber) {
        this.no = no;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}