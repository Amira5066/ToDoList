public class Item {
    private String description = "";
    private boolean mark = false;

    Item(String string){
        this.description = string;
    }

    public String Display(){
        return description + "." + mark;
    }

    public String getName(){
        return description;
    }

    public boolean getMark(){
        return mark;
    }

    public void Mark(){
        mark = true;
    }

    public void Unmark(){
        mark = false;
    }
}
