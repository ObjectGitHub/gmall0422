package com.atguigu.gmall0422.list;

import com.alibaba.fastjson.JSONObject;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSInput;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallListServiceApplicationTests {

    @Autowired
    JestClient jestClient;

    /**
     * 调用内部类属性
     * Json串
     *
     */
    @Test
   public void contextLoads() {

        A.B<String, String> stringStringB = new A.B<String, String>("13","14");
        String name = stringStringB.name;
        String age = stringStringB.age;
        System.out.println(name);
        int a = 5;
        int b = 5 %4;
        System.out.println(b);
        int hotScore = 20;
        String updateJson="{\n" +
                "   \"doc\":{\n" +
                "     \"hotScore\":"+hotScore+"\n" +
                "   }\n" +
                "}";
        System.out.println(updateJson);
        String zz = "{" + "doc:" + "{" + "\"" + "hotScore" +"\"" +":"+ hotScore + "}" + "}";
        System.out.println(zz);
        Map map = (Map)JSONObject.parse(zz);
        Map doc =(Map) map.get("doc");
        System.out.println(doc);
        System.out.println(zz);

    }

    /**
     * 测试迭代器删除元素
     *
     */
    @Test
    public void IteratorTest(){
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        map.put("k4", "v4");

        Map<String, String> maps = new HashMap<>();
        maps.put("k1", "b1");
        maps.put("s2", "b2");
        maps.put("s3", "b3");
        maps.put("s4", "b4");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        list.add(maps);
        System.out.println(list.size());
        List<String> lists = new ArrayList<>();

        for (Iterator<Map<String, String>> iterator = list.iterator(); iterator.hasNext(); ) {
            Map<String, String> next = iterator.next();
            System.out.println(next);
            if("222".equals("222")){
                iterator.remove();
                System.out.println("-------------------");
                System.out.println(next);
                System.out.println("-------------------");
                String k1 = map.get("k1");
                lists.add(k1);
            }
        }
        System.out.println(list.size());
        System.out.println(lists);

    }

    /**
     * 测试es
     * @throws IOException
     */

    @Test
    public void testEs() throws IOException {
        //es查询功能
        //1.先定义dsl语句
        //2.定义执行动作 GET movie_chn/movie/_search
        //3.准备执行
        //4.获取执行结果
        String query="{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"actorList.name\": \"张译\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(query).addIndex("movie_chn").addType("movie").build();

        SearchResult result = jestClient.execute(search);
        List<SearchResult.Hit<Map, Void>> hits = result.getHits(Map.class);
        for (SearchResult.Hit<Map, Void> hit : hits) {
            Map source = hit.source;
            System.err.println("source = " + source);
        }



    }

    /**
     * mp3格式播放
     * 需要引入依赖
     */
    @Test
    public void Music2(){
                System.out.println("爆炸");
                String filename="C:/Users/msi-pc/Downloads/百花香DJ.mp3";
                try {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
                   /* Player player = new Player(buffer);
                    player.play();*/
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
    static final int hash(Object key){
        int h;
        return (key == null )? 0 : (h = key.hashCode()) ^ (h>>> 16);
    }
    static final int hash1(Object key){
        if(key ==null) return 0; //key为null进行处理
        int h = key.hashCode();   //计算key的hash   32位
        int temp = h >>> 16;   //向右位移16位
        int newHash = h ^ temp; //进行异或运算
        return newHash;   //放回hash值
    }



    @Test
    public void TestHashMap(){
        int n = 16;  //默认map的初始化数组大小
        int hash;
        int index; //数组的索引
        HashMap<String, Object> map = new HashMap<>();
        hash = hash("ABCDEa123abc");
        map.put("ABCDEa123abc", 175);
        //如果key相同则返回被替换的key的value
        Object peter = map.put("ABCDEa123abc", 185);
        System.out.println(peter.toString());
        // h&(n-1)
        //计算key的索引
        index = hash & (n - 1);

        System.out.println("king的hash="+hash);
        System.out.println("king的index="+index);
        hash = hash("ABCDFB123abc");
        int index2 = hash & (n - 1);
        System.out.println("ABCDFB123abc="+hash);
        System.out.println(index2);
    }
}
