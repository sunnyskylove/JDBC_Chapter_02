package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application02 {

    public static void main(String[] args) {

        /* 필기. 위치홀더(?) 에 대해 알 수 있다. */

        /* 필기.
         *   java 쿼리문은 파싱(번역)을 통해 컴파일 되어 DataBase 에서 쿼리를 수행해 결과 값을
         *   가지고 오는 구조.
         *   Statement 는 SQL 문 실행시, 쿼리문을 전달하므로, 매번 새로운 쿼리로 인식하기
         *   때문에 조건값에 따라 컴파일을 매번 새로 해야된다.
         *   PreparedStatement 는 조건값을 '?' 로 두고, 쿼리를 준비 시킨 뒤
         *   쿼리는 변경하지 않고, 바인딩 되는 변수만 바꿔서 조회해준다.
         * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String empId = "200";           // 전달할 값 미리 만들어준다.(ID만)

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");
            // 따라서 ID만 위치홀더(?)

            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();       // 해당하는 정보가 한행으로 나올것~~

            if (rset.next()) {

                System.out.println(rset.getString("EMP_ID") + "," + rset.getString("EMP_NAME"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);


        } finally {

            close(pstmt);
            close(con);
            close(rset);
        }

    }

}
