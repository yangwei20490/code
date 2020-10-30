package com.yw.springbootdemo.threads;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-07-14 11:42
 */


public class test {

    public static class Dto {
        private String id;
        private String pid;
        private int num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
    public static void main(String[] args) {
        List<Dto> list = new ArrayList<>();
        Dto o1 = new Dto();
        o1.setId("1");
        o1.setPid("-1");
        o1.setNum(0);
        list.add(o1);

        Dto o2 = new Dto();
        o2.setId("1-1");
        o2.setPid("1");
        o2.setNum(0);
        list.add(o2);

        Dto o3 = new Dto();
        o3.setId("1-2");
        o3.setPid("1");
        o3.setNum(0);
        list.add(o3);

        Dto o4 = new Dto();
        o4.setId("1-1-1");
        o4.setPid("1-1");
        o4.setNum(4);
        list.add(o4);

        Dto o5 = new Dto();
        o5.setId("1-1-2");
        o5.setPid("1-1");
        o5.setNum(6);
        list.add(o5);

        Dto o6 = new Dto();
        o6.setId("1-1-2-1");
        o6.setPid("1-1-2");
        o6.setNum(1);
        list.add(o6);

        List<Dto> addList = new ArrayList<Dto>();
        addList.add(o1);

        doCount(o1, list, addList);

        for (Dto dto : addList) {
            System.out.println(dto.getPid() + "=========" + dto.getId() + "=============" + dto.getNum());
        }
    }

    public static void  doCount(Dto dto ,List<Dto> orglist,List<Dto> addlist){
        int num = dto.getNum();
        List<Dto> clist= findByQueryString(dto.getId(),orglist);
        if(clist != null && clist.size()>0){
            addlist.addAll(clist);
            for (Dto child : clist) {
                //递归
                doCount(child,orglist,addlist);
                num += child.getNum();
                dto.setNum(num);
            }
        }
    }

    public static List<Dto> findByQueryString(String pid ,List<Dto> orglist){
        List<Dto> list = new ArrayList<>();
        if(orglist != null && orglist.size() > 0 ) {
            for (Dto dto : orglist) {
                if(dto.getPid().equals(pid)){
                    list.add(dto);
                }
            }
        }

        return list;
    }
}
