package com.andx.micro.user.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andongxu on 17-4-15.
 */
public class RegexTest {

    @Test
    public void get() {
        String s = "/service/users/10000070/profiles";
        String regex = "\\w+/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
//        System.out.println(matcher.groupCount());
//        System.out.println(matcher.find());
//        System.out.println(matcher.start());
//        System.out.println(matcher.end());
//        System.out.println(matcher.group());
//        System.out.println(matcher.find());
        System.out.println(matcher.find(14));
        System.out.println(matcher.group());
//        int i = 0;
//        while (matcher.find(i)) {
//            System.out.println(matcher.groupCount());
//            System.out.println("===>" + matcher.group());
//            i++;
//        }

        String uri = "/owners/10000070/resources/users";
        String rex = "^/service/owners/\\w+/resources/\\w+";
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(uri);
        System.out.println(m.find());

//        System.out.println(m.group());
    }
}
