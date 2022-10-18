import java.util.*;

public class ToDoList {
    private List<Item> itemList = new ArrayList<Item>();

    ToDoList(String string) {
        if (!string.equals("")) {
            String[] splitString = string.split("[.]");
            int index = 0;
            for (String i : splitString) {
                if (i.equals("true")) {
                    this.Mark(index);
                    index++;
                } else if (i.equals("false")) {
                    index++;
                } else {
                    this.Add(new Item(i));
                }
            }
        }
    }

    public int GetSize(){
        return itemList.size();
    }

    public void Add(Item item) {
        itemList.add(item);
    }

    public void Display() {
        int counter = 1;
        System.out.println();
        for (Item i : itemList) {
            System.out.println(counter + " " + i.Display());
            counter++;
        }
        System.out.println();
    }

    public void Mark(int index) {
        itemList.get(index).Mark();
    }

    public void Unmark(int index) {
        itemList.get(index).Unmark();
    }

    public void Delete(int index) {
        itemList.remove(index);
        this.Display();
    }

    public String ToString() {
        String wholeList = "";
        for (Item i : itemList) {
            wholeList = wholeList + i.Display() + ".";
        }
        return wholeList;
    }

    public String GetItemName(int index){
        return itemList.get(index).getName();
    }

    public boolean GetItemMark(int index){
        return itemList.get(index).getMark();
    }
}