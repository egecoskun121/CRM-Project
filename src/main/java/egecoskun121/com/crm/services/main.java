package egecoskun121.com.crm.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class main {
    public static String unique(String a){

        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
        {
            int flag = 0;
            for (int j = 0; j < a.length(); j++)
            {
                // checking if two characters are equal
                if (a.charAt(i) == a.charAt(j) && i != j)
                {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0)
                sBuilder.append(a.charAt(i));
        }
        return sBuilder.toString();
    }

    public static String eliminateDups(final String s1) {
        final Set<Character> set = new HashSet<>();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            final char c = s1.charAt(i);
            if (set.contains(c)) {
                // skip
            } else {
                sb.append(c);
            }
            set.add(c);

        }
        return sb.toString();
    }

    public static boolean similar(String a, String b){
        char k[]=a.toCharArray();
        char m[]=b.toCharArray();
        if(a.length()!=b.length()){
            return false;
        }

        Arrays.sort(k);
        Arrays.sort(m);
        if(k.equals(m)){
            return true;
        }
        else{
            return false;
        }

    /*
    int count=0;
    for(int i=0;i<a.length();i++){
        for(int j=0;j<b.length();j++){
            if(a.charAt(i)==b.charAt(j)){
                count++;
            }
        }
    }
    if(count==a.length()){
        return true;
    }
    else{
        return false;
    }*/
    }

    public static void main(String[] args) {

        System.out.println(similar("ab","ba")) ;


    }
}
