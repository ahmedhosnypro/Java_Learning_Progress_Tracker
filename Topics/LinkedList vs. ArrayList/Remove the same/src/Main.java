import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        var linkedListIt = linkedList.iterator();
        var arrayListIt = arrayList.iterator();
        while (linkedListIt.hasNext() && arrayListIt.hasNext()) {
            if (linkedListIt.next().equals(arrayListIt.next())) {
                linkedListIt.remove();
                arrayListIt.remove();
            }
        }
    }
}