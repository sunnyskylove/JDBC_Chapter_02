package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application01 {

    public static void main(String[] args) {

        /* 수업목표. PreparedStatement 에 대해 이해하고 사용할 수 있다. */

        /* 필기.
         *   PreparedStatement 란?
         *   Statement 를 상속 받은 interface
         *   더욱 효율적인 작업을 진행할 수 있다.
         *   완성된 SQL 문과, 미완성된 SQL 문을 모두 사용할 수 있다.
         *   위치홀더(placeholder) : ?
         * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;   // ctrl+'PreparedStatement'를 보면, Statement로부터 상속받은거 알 수 있다.
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println(rset.getString("EMP_ID")+ "," + rset.getString("EMP_NAME"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);


        } finally {

            close(pstmt);    // 부모의 것을 그대로 사용할 수 있다. (ctrl+클릭 하면 경로 알수 있다)
            close(rset);
            close(con);

        }

    }

}