package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application03 {

    public static void main(String[] args) {

        /* 필기.
         *   Employee 테이블에서 조회할 사원의 성씨를 입력 받아
         *   해당 성씨를 가진 사원의 정보들을 모두 출력
         * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO row = null;      // 1명의 회원정보를 담을 것

        List<EmployeeDTO> empList = null;     //1명의 회원정보를 담고, empList라는 곳에 계속 추가를 할 것이다.

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 이름의 성을 입력하세요 : ");
        String empName = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')";   // 커리문으로 쓰면?

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empName);

            rset = pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()) {

                row = new EmployeeDTO();            // Sec01 > App05 에 있던 것 복,붙
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            close(con);
            close(rset);
            close(pstmt);
        }

        for (EmployeeDTO emp : empList) {

            System.out.println("emp = " + emp);
        }
    }
}
