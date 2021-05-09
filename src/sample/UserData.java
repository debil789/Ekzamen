package sample;

import javafx.beans.property.SimpleStringProperty;

public class UserData {
    public String getID() {
        return ID.get();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getInfotask() {
        return infotask.get();
    }

    public SimpleStringProperty infotaskProperty() {
        return infotask;
    }

    public void setInfotask(String infotask) {
        this.infotask.set(infotask);
    }

    public String getDaytask() {
        return daytask.get();
    }

    public SimpleStringProperty daytaskProperty() {
        return daytask;
    }

    public void setDaytask(String daytask) {
        this.daytask.set(daytask);
    }

    public String getDatetask() {
        return datetask.get();
    }

    public SimpleStringProperty datetaskProperty() {
        return datetask;
    }

    public void setDatetask(String datetask) {
        this.datetask.set(datetask);
    }

    public String getCategory() {
        return Category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category.set(category);
    }

    public String getSolvedornot() {
        return solvedornot.get();
    }

    public SimpleStringProperty solvedornotProperty() {
        return solvedornot;
    }

    public void setSolvedornot(String solvedornot) {
        this.solvedornot.set(solvedornot);
    }

    SimpleStringProperty ID;
    SimpleStringProperty infotask;
    SimpleStringProperty daytask;
    SimpleStringProperty datetask;
    SimpleStringProperty Category;
    SimpleStringProperty solvedornot;

    UserData(String ID, String infotask, String daytask, String datetask, String Category, String solvedornot) {
        this.ID = new SimpleStringProperty(ID);
        this.infotask = new SimpleStringProperty(infotask);
        this.daytask = new SimpleStringProperty(daytask);
        this.datetask = new SimpleStringProperty(datetask);
        this.Category = new SimpleStringProperty(Category);
        this.solvedornot = new SimpleStringProperty(solvedornot);
    }

}
