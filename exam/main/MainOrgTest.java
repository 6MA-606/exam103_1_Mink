package exam.main;

import exam.base.Group;
import exam.base.Loggable;
import exam.base.OrgUnit;
import exam.unit.Department;
import exam.unit.School;
import exam.unit.Staff;

public class MainOrgTest {
    public static void main(String[] args) {
        testSchool();
        testDepartment();
        testSchoolAsOrgUnit();
        testDepartmentAsLoggable();
        testStaff();
        testDepartmentGroup();
    }

    static void testSchool() {
        System.out.format("\nTest School...");
        School s1 = new School(1, "IT");
        System.out.format("\nSchool: code[%d] name[%s] balance[%.2f] log[%s]", s1.getCode(), s1.getName(), s1.getBalance(), s1.getLog());
        s1.setName("CS");
        s1.earn(5_000);
        s1.spend(2_000);
        System.out.format("\nSchool: code[%d] name[%s] balance[%.2f] log[%s]", s1.getCode(), s1.getName(), s1.getBalance(), s1.getLog());
    }

    static void testDepartment() {
        System.out.format("\n\nTest Department...");
        Department d1 = new Department(1, "SIT", new School(12, "IT"));
        System.out.format("\nDepartment: code[%d] name[%s] log[%s] school[%s]", d1.getCode(), d1.getName(), d1.getLog(), d1.getSchool().getName());
        d1.setName("School of IT");
        d1.setSchool(new School(13, "CS"));
        System.out.format("\nDepartment: code[%d] name[%s] log[%s] school[%s]", d1.getCode(), d1.getName(), d1.getLog(), d1.getSchool().getName());
    }

    static void testSchoolAsOrgUnit() {
        System.out.format("\n\nTest School as OrgUnit...");
        OrgUnit o = new School(2, "IT");
        System.out.format("\nOrgUnit: code[%d] name[%s] balance[%.2f] log[%s]", o.getCode(), o.getName(), ((School) o).getBalance(), o.getLog());
        o.setName("CS");
        ((School) o).earn(5_000);
        ((School) o).spend(2_000);
        System.out.format("\nOrgUnit: code[%d] name[%s] balance[%.2f] log[%s]", o.getCode(), o.getName(), ((School) o).getBalance(), o.getLog());
    }

    static void testDepartmentAsLoggable() {
        System.out.format("Test Department as Loggable...");
        Loggable lg = new Department(3, "SIT", new School(23, "IT"));
        System.out.format("\nLoggable: code[%d] name[%s] log[%s] school[%s]", ((Department) lg).getCode(), ((Department) lg).getName(), lg.getLog(), ((Department) lg).getSchool().getName());
        ((Department) lg).setSchool(new School(5, "Meow"));
        System.out.format("\nLoggable: code[%d] name[%s] log[%s] school[%s]", ((Department) lg).getCode(), ((Department) lg).getName(), lg.getLog(), ((Department) lg).getSchool().getName());
        System.out.format("\nTest clearLog()...");
        lg.clearLog();
        System.out.format("\nLoggable: code[%d] name[%s] log[%s] school[%s]", ((Department) lg).getCode(), ((Department) lg).getName(), lg.getLog(), ((Department) lg).getSchool().getName());
    }

    static void testStaff() {
        System.out.printf("\n\nTest Staff...");
        School school = new School(15, "School od Meow Meow");
        Staff s = new Staff(2000, "Sittha", "Manittayakul", school);
        System.out.printf("\nStaff: id[%d] firstname[%s] lastname[%s] affiliation[%s]", s.getId(), s.getFirstname(), s.getLastname(), s.getAffiliation().getName());
        s.setId(3000);
        s.setFirstname("Wanassanan");
        s.setLastname("Tri-apibanwongsa");
        s.setAffiliation(new Department(651, "The MEOW", school));
        System.out.printf("\nStaff: id[%d] firstname[%s] lastname[%s] affiliation[%s]", s.getId(), s.getFirstname(), s.getLastname(), s.getAffiliation().getName());
    }

    static void testDepartmentGroup() {
        System.out.printf("\n\nTest Department Group...\n");
        Group<School> sg = new Group<>(5);
        School s1 = new School(1, "School 1");
        School s2 = new School(2, "School 2");
        School s3 = new School(3, "School 3");
        School s4 = new School(4, "School 4");
        School s5 = new School(5, "School 5");
        School s6 = new School(6, "School 6");

        System.out.printf("Append %s : %s\n", s1.getName(), sg.append(s1) ? "Success" : "Failure");
        System.out.printf("Append %s : %s\n", s2.getName(), sg.append(s2) ? "Success" : "Failure");
        System.out.printf("Append %s : %s\n", s3.getName(), sg.append(s3) ? "Success" : "Failure");
        System.out.printf("Append %s : %s\n", s4.getName(), sg.append(s4) ? "Success" : "Failure");
        System.out.printf("Append %s : %s\n", s5.getName(), sg.append(s5) ? "Success" : "Failure");

        int x = sg.find(s2);
        System.out.printf("Find %s : %s\n", s2.getName(), x < 0 ? "Not found" : "At " + x + " -> " + sg.get(x).getName());
        x = sg.find(s6);
        System.out.printf("Find %s : %s\n", s6.getName(), x < 0 ? "Not found" : "At " + x + " -> " + sg.get(x).getName());
        boolean rm = sg.remove(s3);
        System.out.printf("Remove %s : %s\n", s3.getName(), rm ? "Success" : "Failure");
        rm = sg.remove(s6);
        System.out.printf("Remove %s : %s\n", s6.getName(), rm ? "Success" : "Failure");
        for (int i = 0; i < sg.getSize(); i++) {
            School sc = sg.get(i);
            System.out.format("[%d] School: code[%d] name[%s]\n",i , sc.getCode(), sc.getName());
        }
    }
}
