package com.atguigu.gmall0422.item;

import com.sun.javafx.logging.PulseLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallItemWebApplicationTests {

    @Test
    public void contextLoads() {
        int[] num = {1,2,2,3,4,5,6,7,8,9};
        for (int i = 0; i <num.length-1 ; i++){
            int sum = num[i] + num[i + 1];
            System.out.println(sum + "=" + num[i] + "+" +num[i+1]);

        }
    }

    /**
     * 冒泡排序
     */
    @Test
    public void test001(){

        int[] num = {6, 3, 8, 2, 9, 1,};
        for (int i = 0; i <num.length-1 ; i++) {
            for (int j = 0; j <num.length-i-1 ; j++) {
                if(num[j] > num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j + 1] = temp;
                }
            }
            for (int j = 0; j <num.length; j++) {
                System.out.print(num[j]);
            }

        }
        for (int i = 0; i <num.length; i++) {
            System.out.print("排序后的数组=");
            System.out.println(num[i]);
        }

    }

    /**
     * 选择排序
     */
    @Test
    public void test02(){
        int[] num = {5, 2, 8, 4, 9, 1,};
        for (int i = 0; i <num.length-1 ; i++) {
            for (int j = i+1; j <num.length; j++) {
                if(num[i] > num[j]){
                    int temp = num[j];
                    num[j] = num[i];
                    num[i] = temp;
                }
                for (int k = 0; k <num.length; k++) {
                    System.out.println(num[k]);
                }

            }

        }
        for (int i = 0; i <num.length; i++) {
            System.out.print("排序后的数组=");
            System.out.println(num[i]);
        }


    }


}
