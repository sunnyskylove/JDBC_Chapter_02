package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application04 {

    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        // employeeDTO 객체에 받아 온 데이터베이스 정보들을 담기 위함.
        EmployeeDTO selectedEmp = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회하실 사번을 입력해 주세요 : ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = " + empId + "'";


        try {
            stmt = con.createStatement();

            rset = stmt.executeQuery(query);

            if(rset.next()){

                selectedEmp = new EmployeeDTO();         // 자료형별 기본값이 연결되어있으것임~~
                selectedEmp.setEmpId(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
                selectedEmp.setEmail(rset.getString("EMAIL"));
                selectedEmp.setPhone(rset.getString("PHONE"));
                selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
                selectedEmp.setJobCode(rset.getString("JOB_CODE"));
                selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
                selectedEmp.setSalary(rset.getInt("SALARY"));
                selectedEmp.setBonus(rset.getDouble("BONUS"));
                selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
                selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
                selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
                selectedEmp.setEntYn(rset.getString("ENT_YN"));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        finally {
            close(con);
            close(stmt);
            close(rset);
        }

        System.out.println("selectedEmP : " + selectedEmp);     // 한 명의 회원을 만들었다.



    }
}
