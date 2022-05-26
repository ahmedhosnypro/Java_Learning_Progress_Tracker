import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        String tmpStr = linkedList.getFirst();
        linkedList.removeFirst();
        linkedList.addFirst(arrayList.get(0));
        arrayList.set(0, tmpStr);

        tmpStr = linkedList.getLast();
        linkedList.removeLast();
        linkedList.addLast(arrayList.get(arrayList.size() - 1));
        arrayList.set(arrayList.size() - 1, tmpStr);
    }
}