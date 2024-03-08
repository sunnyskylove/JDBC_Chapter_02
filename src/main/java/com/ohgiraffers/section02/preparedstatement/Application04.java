package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application04 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO row = null;      // 1명의 회원정보를 담을 것

        List<EmployeeDTO> empList = null;     //1명의 회원정보를 담고, empList라는 곳에 계속 추가를 할 것이다.

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 이름의 성을 입력하세요 : ");
        String empName = sc.nextLine();

//        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')";
        Properties prop = new Properties();

        try {

            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml")
            );

            String query = prop.getProperty("selectEmptyFamilyName");
            // 파일에서 key 가져온 것!
            // xml 따로 작성하고, key 만 끌어와서 쓸 수 있다.

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

        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
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