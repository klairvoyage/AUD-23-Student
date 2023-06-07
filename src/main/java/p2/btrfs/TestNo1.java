package p2.btrfs;

import p2.storage.EmptyStorageView;
import p2.storage.Interval;
import p2.storage.StorageView;

public class TestNo1 {
    private static int yo = -1;
    private static int oy = -1;
    public static void main(String[] args) {
        BtrfsNode root = new BtrfsNode(2);
        BtrfsNode node1 = new BtrfsNode(2);
        BtrfsNode node2 = new BtrfsNode(2);
        BtrfsNode node3 = new BtrfsNode(2);

        root.size = 2;
        node1.size = 1;
        node2.size = 1;
        node3.size = 3;

        Interval root_key0 = new Interval(8, 3);
        Interval root_key1 = new Interval(18, 2);
        Interval node1_key0 = new Interval(5, 1);
        Interval node2_key0 = new Interval(1, 2);
        Interval node3_key0 = new Interval(15, 1);
        Interval node3_key1 = new Interval(20, 2);
        Interval node3_key2 = new Interval(6, 1);

        root.keys[0] = root_key0;
        root.keys[1] = root_key1;
        node1.keys[0] = node1_key0;
        node2.keys[0] = node2_key0;
        node3.keys[0] = node3_key0;
        node3.keys[1] = node3_key1;
        node3.keys[2] = node3_key2;

        root.children[0] = node1;
        root.children[1] = node2;;
        root.children[2] = node3;

        root.childLengths[0] = 1;
        root.childLengths[1] = 2;
        root.childLengths[2] = 4;
        root.childLengths[3] = 0;
        node1.childLengths[0] = 0;
        node1.childLengths[1] = 0;
        node1.childLengths[2] = 0;
        node1.childLengths[3] = 0;
        node2.childLengths[0] = 0;
        node2.childLengths[1] = 0;
        node2.childLengths[2] = 0;
        node2.childLengths[3] = 0;
        node3.childLengths[0] = 0;
        node3.childLengths[1] = 0;
        node3.childLengths[2] = 0;
        node3.childLengths[3] = 0;

        /*BtrfsNode soloRoot = new BtrfsNode(2);
        soloRoot.size = 1;
        Interval soloRoot_key0 = new Interval(18, 2);
        soloRoot.keys[0] = root_key0;*/


        System.out.println("Eingabe: read(0, 12, root, 0, 0)!");
        System.out.println(read(0,12,root,0,0));
    }

    public static String read(int start, int length, BtrfsNode node, int cumulativeLength, int lengthRead) {
        StringBuilder storageView = new StringBuilder(); //replaced "StorageView storageView = new EmptyStorageView(storage);"

        if (start<0 || length<=0 || cumulativeLength<0 || lengthRead>=length || lengthRead>cumulativeLength)
            return storageView.toString();

        for (int i=0;i<node.size;i++) {
            if (node.children[i]!=null) { //WHAT IF NULL/.ISLEAF
                //recursive call if the interval is somewhat contained in child node
                if ((start+lengthRead)<(node.childLengths[i]+cumulativeLength)) {
                    storageView.append(read(start, length, node.children[i], cumulativeLength, lengthRead));
                    cumulativeLength = yo;
                    lengthRead = oy;
                    if (lengthRead==length) break;
                }
                //increments cumulativeLength by number of blocks in child node, if skipped
                else {
                    cumulativeLength += node.childLengths[i];
                    yo = cumulativeLength;
                }
            }
            //reads interval in node, if somewhat contained in it
            if ((start+lengthRead)<(node.keys[i].length()+cumulativeLength)) {
                Interval key = node.keys[i];
                int shift = (start+lengthRead)-cumulativeLength;
                int potentialInterval = key.length()-((start+lengthRead)-cumulativeLength);
                int leftToBeRead = length - lengthRead;
                int subIntLength = (leftToBeRead < potentialInterval) ? leftToBeRead : potentialInterval;
                String s = "("+key.start()+"+"+shift+",length: "+subIntLength+")\n";
                storageView.append(s);
                cumulativeLength += key.length();
                yo = cumulativeLength;
                lengthRead += subIntLength;
                oy = lengthRead;
                if (lengthRead==length) break;
            } else {
                cumulativeLength += node.keys[i].length();
                yo = cumulativeLength;
            }
        }
        if (node.children[node.size]!=null) {
            storageView.append(read(start, length, node.children[node.size], cumulativeLength, lengthRead));
        }

        return storageView.toString(); //CHECK READCURSION (GIT)

    }
}
