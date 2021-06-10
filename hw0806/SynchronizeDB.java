package hw0806;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeDB {
    public static void synchronizedDb(List<Student> list) {
        for (Student student : list) {
            GradeManagementPort2000.addStudentToTable(student);
            GradeManagementPort2001.addStudentToTable(student);
        }
    }

    public static List<Student> isDuplicate(List<Student> list, List<Student> other) {
        List<Student> syncList = new ArrayList<>();

        // checking duplicate id
        boolean flag = false;
        for (int i = 0; i < other.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                flag = !(other.get(j).getId() == list.get(i).getId());
            }
                if(flag)
                    syncList.add(other.get(i));
        }

        syncList.addAll(list);

        return syncList;
    }
}