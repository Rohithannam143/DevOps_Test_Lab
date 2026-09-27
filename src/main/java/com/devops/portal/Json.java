package com.devops.portal;
import java.util.*;
public final class Json{private Json(){} public static String esc(String s){return s==null?"":s.replace("\\","\\\\").replace("\"","\\\"").replace("\n"," ").replace("\r"," ").replace("\t"," ");}
 public static String list(List<Map<String,Object>> a){StringBuilder b=new StringBuilder("[");for(int i=0;i<a.size();i++){if(i>0)b.append(',');b.append(map(a.get(i)));}return b.append(']').toString();}
 public static String map(Map<String,Object> m){StringBuilder b=new StringBuilder("{");int i=0;for(var e:m.entrySet()){if(i++>0)b.append(',');b.append('\"').append(e.getKey()).append("\":");Object v=e.getValue();if(v==null)b.append("null");else if(v instanceof Number||v instanceof Boolean)b.append(v);else b.append('\"').append(esc(String.valueOf(v))).append('\"');}return b.append('}').toString();}
}
