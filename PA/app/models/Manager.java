package models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

@Entity
public class Manager extends Person {

    public Manager(int id, String password, String name, Date birthDate){
        super(id, password, name, birthDate

        );
    }

    public static void initializeSemester(String sourceFilePath){
        Map<String, String[][]> sheets =Parser.xlsxParse(sourceFilePath);

        for (Map.Entry<String, String[][]> entry : sheets.entrySet())
        {
            System.out.println("====================");
            System.out.println(entry.getKey());
            for (int i = 0 ; i < entry.getValue().length ; i++)
            {
                String[] row = entry.getValue()[i];
                for (int j = 0 ; j < row.length ; j++){
                    System.out.println(entry.getValue()[i][j]);
                }
            }

        }
        //TODO:to check wether we want to do it here or we will do it in another class

        Parser.objectsCreation(sheets);

    }

    public static Finder<String,Manager> find = new Finder<String,Manager>(
            String.class, Manager.class
    );

    public static Manager authenticate(int id, String password) {
        return find.where().eq("id", id)
                .eq("password", password).findUnique();

    }

}
