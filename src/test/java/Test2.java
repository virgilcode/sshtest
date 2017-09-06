import org.junit.Test;

/**
 * Created by Virgil on 2017/8/28.
 */
public class Test2 {
    @Test
    public void test() {
        int[] a = {1, 2, 2, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5};
        int p = 0, nums = 1;
        for (int i = 0; i < a.length; i++) {
            int temp = 1;
            int tempNums = 1;
            int tempP = i;
            while (true) {
                if (i + temp < a.length) {
                    if (a[i + temp] == a[i]) {
                        temp++;
                        tempNums++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (tempNums > nums) {
                nums = tempNums;
                p = tempP;
            }
        }
        System.out.println("p is " + p + " and nums is " + nums);

    }
}
