package hw0806;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeDB {
    public static void synchronizedDb(List<Student> list) {
        for(int i = 0; i < list.size();i++) {
            GradeManagementServer1.addStudentToTable(list.get(i));
            GradeManagementServer2.addStudentToTable(list.get(i));
        }
    }

    public static List<Student> isDuplicate(List<Student> list, List<Student> other) {
        List<Student> syncList = new ArrayList<>(list);

        // checking duplicate id
        boolean flag = false;
        for (int i = 0; i < other.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(!(other.get(j).getId() == list.get(i).getId()))
                    flag = true;
                else
                    flag = false;
            }
                if(flag)
                    syncList.add(other.get(i));
        }

        return syncList;
    }
}