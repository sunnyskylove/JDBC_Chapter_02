package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.SimpleTimeZone;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

// * 중요 2! : app2 인용해서, 몇 부분 변경하고 Scanner 생성해서 하는 방법
public class Application03 {

    public static void main(String[] args) {

        // 1. Connection 생성
        Connection con = getConnection();

        // 2. Statement 생성
        Statement stmt = null;

        // 3. ResultSet 생성
        ResultSet rset = null;


        try {
            // 4. Connection 의 creatStatement() 메소드를 이용햇 Statement 인스턴스 생성
            stmt = con.createStatement();

            // ## Scanner 사용해서 응용!!
            Scanner sc = new Scanner(System.in);
            System.out.println("조회하실 직원의 사번을 입력해 주세요 : ");
            String empId = sc.nextLine();

            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            // 5. executeQuery() 로 쿼리문을 실행하고 결과를 ResultSet 으로 반환받음
            rset = stmt.executeQuery(query);

            // 6. ResultSet 에 담긴 결과물을 컬럼 이름(label) 을 이용해 꺼내어 출력
            if (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + "," + rset.getString("EMP_NAME"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        } finally {
            // 7. 마지막으로 사용한 자원 반남 (Connection, Statement, ResultSet)
            close(con);
            close(stmt);
            close(rset);
        }

    }
}
