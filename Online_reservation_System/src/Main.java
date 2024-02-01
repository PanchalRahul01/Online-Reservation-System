import java.sql.*;
import java.util.*;

class pnrRecord{

    int pnr;
    int trnNo;
    String pnrName;
//  String trainName;
    String tclass;
    String jdate;
    String to;
    String from;
    Scanner sc=new Scanner(System.in);
    int pnrNo(){
        Random rnd= new Random();
         pnr= rnd.nextInt(9999)+1000;
         return pnr;
    }
    int trnNo(){
        Random rnd= new Random();
        trnNo= rnd.nextInt(99999)+10000;
        return pnr;
    }
    String pnrName(){
        System.out.println("Enter Passenger Name=");
        pnrName=sc.nextLine();
        return pnrName;
    }
/*
    String trainName(){
        if(trnNo%2==0){
            trainName="Rajdhani Express";
        }
        else{
            trainName="Shatabdhi Experess";
        }
        return trainName;
    }
 */
    String typclass(){
        System.out.println("class=");
        tclass=sc.nextLine();
        return tclass;
    }

    String journeydate(){
        System.out.println("Enter the date=");
        jdate=sc.nextLine();
        return jdate;
    }
    String from(){
        System.out.println("Enter the Starting place=");
        from=sc.nextLine();
        return from;
    }
    String to(){
        System.out.println("Enter the Destination place=");
        to=sc.nextLine();
        return to;
    }

}
public class Main {

    String uname;
    String pass;
    Scanner sc = new Scanner(System.in);

    String username() {
        System.out.print("Enter the Username=");
        uname = sc.nextLine();
        return uname;
    }

    String password() {
        System.out.print("Enter the password=");
        pass = sc.nextLine();
        return pass;
    }


    void reserveTicket() {

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Main m1 = new Main();

        System.out.println("Online Reservation System..");
        System.out.println("___________________________");

        String uname = m1.username();
        String pass = m1.password();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Error1: ");
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rahul_db", "root", "rahul");
            Statement statement = con.createStatement();


            //String show= "select * from stud";

            do {
                if (uname.equals("Rahul") && pass.equals("panchal12")) {
                    System.out.println("Welcome " + uname);

                    System.out.println("1.Reserve Tickets");
                    System.out.println("2.Show All Records.");
                    System.out.println("3.Cancel Tickets");

                    System.out.print("Enter your choice=");
                    int ch = sc.nextInt();

                    switch (ch) {
                        case 1:
                            pnrRecord p1 = new pnrRecord();

                            int pnr = p1.pnrNo();
                            String pname = p1.pnrName();
                            int trnno = p1.trnNo();

                            String date = p1.journeydate();
                            String clas = p1.typclass();
                            String from = p1.from();
                            String to = p1.to();
                            System.out.println("Passenger No= " + pnr);
                            System.out.println("Train no:" + trnno);
                            //String from= p1.from();
                            String insert = String.format("insert into reverse(pid,pname,tno,date,f,t) values(%o,'%s',%o,'%s','%s','%s')", pnr, pname, trnno, date, from, to);
                            int rowsAffected = statement.executeUpdate(insert);

                            if (rowsAffected > 0) {
                                System.out.println("Reservation Success...");
                            } else {
                                System.out.println("data not inserted..");
                            }
                            break;
                        case 2:
                            String show = "Select * from reverse ";
                            ResultSet resultSet = statement.executeQuery(show);

                            while (resultSet.next()) {

                                int pid = resultSet.getInt("pid");
                                String name = resultSet.getString("pname");
                                int trainno = resultSet.getInt("tno");
                                String da = resultSet.getString("date");
                                String f = resultSet.getString("f");
                                String t = resultSet.getString("t");

                                System.out.print(pid + " ");
                                System.out.print(name + " ");
                                System.out.print(trainno + " ");
                                System.out.print(da + " ");
                                System.out.print(f + " ");
                                System.out.println(t);
                            }

                    }
                    while (ch < 4) ;

                }

            }
        }
    }
}
}











