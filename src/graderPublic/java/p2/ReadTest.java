package p2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonClasspathSource;
import org.junitpioneer.jupiter.json.Property;
import p2.btrfs.BtrfsFile;
import p2.storage.StorageView;
import java.util.Arrays;
import java.util.List;
import static p2.TreeUtil.constructTree;

public class ReadTest {

    @ParameterizedTest
    @JsonClasspathSource(value = "ReadTests.json", data = "testMergeRight")
    public void startTest1(@Property("tree") List<Object> tree,
                           @Property("degree") int degree) throws Throwable{

        int startInd = 0;
        int length = 7;
        TreeUtil.FileAndStorage actualFileAndStorage = constructTree(tree, degree);
        BtrfsFile actualTree = actualFileAndStorage.file();
        StorageView sv = actualTree.read(startInd, length); // in ex
        StorageView ss = actualTree.readAll();

        byte[] methodRead = sv.getData();
        byte[] orgRead = ss.getData();
        byte[] rangeArray = Arrays.copyOfRange(orgRead, startInd, Math.min(startInd + length, orgRead.length));

        Assertions.assertTrue(areEqual(rangeArray, methodRead));

        startInd = 8;
        length = 5;

        Assertions.assertTrue(areEqual(rangeArray, methodRead));

    }

    @ParameterizedTest
    @JsonClasspathSource(value = "ReadTests.json", data = "testMergeRight")
    public void startTest2(@Property("tree") List<Object> tree,
                           @Property("degree") int degree) throws Throwable{

        int startInd = 0;
        int length = 7;
        TreeUtil.FileAndStorage actualFileAndStorage = constructTree(tree, degree);
        BtrfsFile actualTree = actualFileAndStorage.file();
        StorageView sv = actualTree.read(startInd, length); // in ex
        StorageView ss = actualTree.readAll();

        byte[] methodRead = sv.getData();
        byte[] orgRead = ss.getData();
        byte[] rangeArray = Arrays.copyOfRange(orgRead, startInd, Math.min(startInd + length, orgRead.length));

        Assertions.assertTrue(areEqual(rangeArray, methodRead));

        startInd = 4;
        length = 5;

        Assertions.assertTrue(areEqual(rangeArray, methodRead));

    }

    public boolean areEqual(byte[] array1, byte[] array2) {

        boolean isEqual = true;

        if (array1.length != array2.length) {
            isEqual = false;
        } else {
            for (int i = 0; i < array1.length; i++) {
                if (array1[i] != array2[i]) {
                    isEqual = false;
                    break;
                }
            }
        }

        System.out.println("Arrays are equal: " + isEqual);
        return isEqual;
    }

}
