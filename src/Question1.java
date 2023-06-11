// 문제 1

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question1 {

    private static File empFile = new File("C:/Users/abg14/Desktop/emp.txt");

    private static Scanner scanner = new Scanner(System.in);

    private static List<Emp> empList = new ArrayList<>();

    private static String scanf(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (!empFile.exists()) {
            empFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/abg14/Desktop/emp.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        FileInputStream fileInputStream = new FileInputStream("C:/Users/abg14/Desktop/emp.txt");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);


        mainLoop:
        while (true) {
            try{
                empList = (List<Emp>) inputStream.readObject();
            } catch (java.io.EOFException e){
                empList = new ArrayList<>();
            }

            switch (scanf("1.add  2.remove  3.get  4.set  5.프로그램 종료: ")) {
                case "1":
                    add();
                    break;
                case "2":
                    remove();
                    break;
                case "3":
                    get();
                    break;
                case "4":
                    set();
                    break;
                case "5":
                    break mainLoop;
                default:
                    System.out.println("WARNING!! 잘못된 입력입니다.");
            }

            outputStream.writeObject(empList);
        }
    }

    private static void add() {
        Emp newEmp = new Emp();
        newEmp.setName(scanf("새로운 직원의 이름을 입력하세요: "));
        newEmp.setAccount(scanf("직원의 연락처를 입력하세요: "));
        newEmp.setDepartment(scanf("직원의 부서를 입력하세요: "));
        newEmp.setPosition(scanf("직원의 직급을 입력하세요: "));
        newEmp.setPay(Integer.parseInt(scanf("직원의 급여를 입력하세요: ")));

        empList.add(newEmp);

        System.out.println("추가되었습니다.\n");
    }

    private static void remove() {
        System.out.println("번호   이름        연락처 ");

        for (int i = 0; i < empList.size(); i++) {
            Emp emp = empList.get(i);
            System.out.printf("%d    %s       %s", i, emp.getName(), emp.getAccount());
        }

        empList.remove(Integer.parseInt(scanf("제거할 직원의 번호를 입력하세요: ")));
        System.out.println("제거되었습니다.");
    }

    private static void get() {
        System.out.println("번호   이름        연락처 ");

        for (int i = 0; i < empList.size(); i++) {
            Emp emp = empList.get(i);
            System.out.printf("%d    %s       %s\n", i, emp.getName(), emp.getAccount());
        }
    }

    private static void set() {
        System.out.println("번호   이름        연락처 ");

        for (int i = 0; i < empList.size(); i++) {
            Emp emp = empList.get(i);
            System.out.printf("%d    %s       %s\n", i, emp.getName(), emp.getAccount());
        }

        Emp emp = empList.get(Integer.parseInt(scanf("수정할 직원의 번호를 입력하세요: ")));

        System.out.println("""
                1. 이름
                2. 연락처
                3. 부서
                4. 직급
                5. 급여
                """);

        String choice = scanf("수정할 데이터를 선택하세요: ");
        String data = scanf("변경할 내용을 입력하세요: ");


        switch (choice) {
            case "1":
                emp.setName(data);
                break;
            case "2":
                emp.setAccount(data);
                break;
            case "3":
                emp.setDepartment(data);
                break;
            case "4":
                emp.setPosition(data);
                break;
            case "5":
                emp.setPay(Integer.parseInt(data));
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
        System.out.println("수정되었습니다.");
    }

}

class Emp implements Serializable{
    private String name;
    private String account;
    private String department;
    private String position;
    private int pay;

    public Emp() {
    }

    public Emp(String name, String account, String department, String position, int pay) {
        this.name = name;
        this.account = account;
        this.department = department;
        this.position = position;
        this.pay = pay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
