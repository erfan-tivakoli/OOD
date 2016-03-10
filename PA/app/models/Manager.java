package models;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.Map;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends Person {

    public Manager(int id, String password, String name, Date birthDate){
        super(id, password, name, birthDate

        );
    }

    public static void initializeSemester(File source){
        Map<String, String[][]> sheets =Parser.xlsxParse(source);

        for (Map.Entry<String, String[][]> entry : sheets.entrySet())
        {
            System.err.println("====================");
            System.err.println(entry.getKey());
            for (int i = 0 ; i < entry.getValue().length ; i++)
            {
                String[] row = entry.getValue()[i];
                for (int j = 0 ; j < row.length ; j++){
                    System.err.println(entry.getValue()[i][j]);
                }
            }

        }
        //TODO:to check wether we want to do it here or we will do it in another class

        Parser.objectsCreation(sheets);

    }

//    public static Finder<Integer,Manager> find = new Finder<Integer, Manager>(
//            Integer.class, Manager.class
//    );
//
//    public static Manager authenticate(int id, String password) {
//        return find.where().eq("id", id)
//                .eq("password", password).findUnique();
//
//    }

}
